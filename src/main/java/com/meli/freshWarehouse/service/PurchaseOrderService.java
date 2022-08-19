package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.ShoppingCartDTO;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.PurchaseOrder;
import com.meli.freshWarehouse.model.ShoppingCart;
import com.meli.freshWarehouse.repository.PurchaseOrderRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseOrderService implements IPurchaseOrderService{

    private final PurchaseOrderRepo purchaseOrderRepo;

    public PurchaseOrderService(PurchaseOrderRepo purchaseOrderRepo) {
        this.purchaseOrderRepo = purchaseOrderRepo;
    }

    @Override
    public PurchaseOrder save(ShoppingCart shoppingCart) {
        return purchaseOrderRepo.save(PurchaseOrder.builder()
                .buyer(shoppingCart.getBuyer())
                .date(LocalDate.now())
                .shoppingCart(shoppingCart)
                .orderStatus("Aguardando Pagamento")
                .totalPrice(shoppingCart.getShoppingCartProducts().stream()
                        .reduce(0D,
                                (partialTotalPrice, shoppingCartProduct) ->
                                        partialTotalPrice + shoppingCartProduct.getProduct().getPrice(), Double::sum)
                )
                .build());
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long purchaseOrderId) {
        return purchaseOrderRepo.findById(purchaseOrderId).orElseThrow(() -> new NotFoundException("Can't find purchase order with the informed id"));
    }

    @Override
    public List<PurchaseOrder> findAllByBuyerId(Long buyerId) {
        List<PurchaseOrder> purchaseOrderList = purchaseOrderRepo.findAllByBuyer_Id(buyerId);
        if(purchaseOrderList.isEmpty()) {
            throw new NotFoundException("There is no purchase order associated with this buyer.");
        }
        return purchaseOrderList;
    }

    @Override
    public PurchaseOrder updateStatusOrder(Long purchaseOrderId, String statusOrder) {
        PurchaseOrder purchaseOrder = this.getPurchaseOrderById(purchaseOrderId);
        purchaseOrder.setOrderStatus(statusOrder);
        return purchaseOrder;
    }
}
