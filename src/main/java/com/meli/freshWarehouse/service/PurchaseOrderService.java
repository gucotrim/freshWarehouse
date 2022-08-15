package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.PurchaseOrderDto;
import com.meli.freshWarehouse.dto.PurchaseOrderTotalPriceDTO;
import com.meli.freshWarehouse.exception.ExceededStock;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.PurchaseOrder;
import com.meli.freshWarehouse.repository.PurchaseRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PurchaseOrderService implements IPurchaseOrderService{

    private final BuyerService buyerService;
    private final WarehouseService warehouseService;
    private final PurchaseRepo purchaseRepo;

    public PurchaseOrderService(BuyerService buyerService, WarehouseService warehouseService, PurchaseRepo purchaseRepo) {
        this.buyerService = buyerService;
        this.warehouseService = warehouseService;
        this.purchaseRepo = purchaseRepo;
    }

    @Override
    public PurchaseOrderTotalPriceDTO save(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = purchaseRepo.save(PurchaseOrder.builder()
                .buyer(buyerService.getBuyerById(purchaseOrderDto.getBuyerId()))
                .orderStatus("OPEN")
                .date(LocalDate.parse(purchaseOrderDto.getDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .shoppingCartProducts(purchaseOrderDto.getShoppingCartProducts())
                .build());
        return PurchaseOrderTotalPriceDTO.builder()
                .id(purchaseOrder.getId())
                .totalPrice(purchaseOrder.getShoppingCartProducts().stream()
                        .reduce(0D,
                                (partialTotalPrice, shoppingCartProduct) ->
                                        partialTotalPrice + shoppingCartProduct.getProduct().getPrice(), Double::sum)
                )
                .build();
    }

    @Override
    public PurchaseOrder getById(Long purchaseOrderId) {
        return purchaseRepo.findById(purchaseOrderId).orElseThrow(() -> new NotFoundException("Can't find purchase order with the informed id"));
    }

    @Override
    public PurchaseOrderTotalPriceDTO finalizePurchaseOrder(Long purchaseOrderId) {
        PurchaseOrder purchaseOrder = this.getById(purchaseOrderId);
        if(purchaseOrder.getOrderStatus().equals("CLOSE")) {
            throw new NotFoundException("Not permitted");
        }


        purchaseOrder.getShoppingCartProducts().stream().forEach(p -> {
            Long totalOfQuantityInStock = warehouseService.getStockOfProductById(p.getId()).getWarehouses().stream()
                    .reduce(0L,
                            (partialQuantity, warehouse) -> partialQuantity + warehouse.getTotalQuantity(), Long::sum);
            if(totalOfQuantityInStock < p.getQuantity() ) {
                throw new ExceededStock("Quantity of " + p.getProduct().getName() + " in purchase order exceeds current stock value. Total quantity in stock: "
                        + totalOfQuantityInStock);
            }
            p.getProduct().getListBatch().forEach(batch -> {
                if(batch.getDueDate().isBefore(LocalDate.now().plusWeeks(3))) {
                    throw new NotFoundException("Can't add " + p.getProduct().getName() + " to shopping cart. " + p.getProduct().getName() + " expiring date already expirated.");
                }
            });
        });


        purchaseOrder.setOrderStatus("CLOSE");

        purchaseRepo.save(purchaseOrder);

        //TODO atualizar o stock

        return PurchaseOrderTotalPriceDTO.builder()
                .id(purchaseOrder.getId())
                .totalPrice(purchaseOrder.getShoppingCartProducts().stream()
                        .reduce(0D,
                                (partialTotalPrice, shoppingCartProduct) ->
                                        partialTotalPrice + shoppingCartProduct.getProduct().getPrice(), Double::sum)
                )
                .build();
    }
}
