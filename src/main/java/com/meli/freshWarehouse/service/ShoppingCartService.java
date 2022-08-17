package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.ShoppingCartDTO;
import com.meli.freshWarehouse.dto.ShoppingCartProductDto;
import com.meli.freshWarehouse.exception.ExceededStock;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.ShoppingCart;
import com.meli.freshWarehouse.model.ShoppingCartProduct;
import com.meli.freshWarehouse.repository.BatchRepo;
import com.meli.freshWarehouse.repository.ShoppingCartRepo;
import com.meli.freshWarehouse.repository.ShoppingCartProductRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ShoppingCartService implements IShoppingCartService {

    private final BuyerService buyerService;
    private final WarehouseService warehouseService;
    private final ProductService productService;
    private final ShoppingCartRepo shoppingCartRepo;
    private final ShoppingCartProductRepo shoppingCartProductRepo;
    private final BatchRepo batchRepo;

    public ShoppingCartService(BuyerService buyerService, WarehouseService warehouseService, ProductService productService, ShoppingCartRepo shoppingCartRepo, ShoppingCartProductRepo shoppingCartProductRepo, BatchRepo batchRepo) {
        this.buyerService = buyerService;
        this.warehouseService = warehouseService;
        this.productService = productService;
        this.shoppingCartRepo = shoppingCartRepo;
        this.shoppingCartProductRepo = shoppingCartProductRepo;
        this.batchRepo = batchRepo;
    }

    @Override
    public ShoppingCartDTO addProductToShoppingCart(Long buyerId, ShoppingCartProductDto shoppingCartProductDto) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepo.findShoppingCartByBuyer_IdAndAndOrderStatusEquals(buyerId, "OPEN");

        if(shoppingCart.isPresent() && shoppingCartProductRepo.existsShoppingCartProductByShoppingCart_IdAndProduct_Id(shoppingCart.get().getId(), shoppingCartProductDto.getProductId())) {
            ShoppingCartProduct shoppingCartProduct = shoppingCartProductRepo.findShoppingCartProductByShoppingCartAndProduct_Id(shoppingCart.get(), shoppingCartProductDto.getProductId());
            shoppingCartProduct.setQuantity(shoppingCartProduct.getQuantity() + 1);
            shoppingCartProductRepo.save(shoppingCartProduct);

            return returnShoppingCartDTO(shoppingCart.get());

        } else if(shoppingCart.isPresent()) {
            ShoppingCartProduct shoppingCartProduct = ShoppingCartProduct.builder()
                    .product(productService.getProductById(shoppingCartProductDto.getProductId()))
                    .quantity(shoppingCartProductDto.getQuantity())
                    .shoppingCart(shoppingCart.get())
                    .build();
            shoppingCart.get().getShoppingCartProducts().add(shoppingCartProduct);
            shoppingCartRepo.save(shoppingCart.get());
            shoppingCartProductRepo.save(shoppingCartProduct);

            return returnShoppingCartDTO(shoppingCart.get());

        }
        ShoppingCart newShoppingCart = shoppingCartRepo.save(ShoppingCart.builder()
                .buyer(buyerService.getBuyerById(buyerId))
                .orderStatus("OPEN")
                .build());

        Set<ShoppingCartProduct> shoppingCartProducts = new HashSet<>();
        shoppingCartProducts.add(ShoppingCartProduct.builder()
                .product(productService.getProductById(shoppingCartProductDto.getProductId()))
                .quantity(shoppingCartProductDto.getQuantity())
                .shoppingCart(newShoppingCart)
                .build());

        newShoppingCart.setShoppingCartProducts(shoppingCartProducts);

        shoppingCartProductRepo.saveAll(shoppingCartProducts);

        return returnShoppingCartDTO(newShoppingCart);
    }

    @Override
    public ShoppingCart getById(Long purchaseOrderId) {
        return shoppingCartRepo.findById(purchaseOrderId).orElseThrow(() -> new NotFoundException("Can't find purchase order with the informed id"));
    }

    @Override
    public ShoppingCartDTO finalizePurchaseOrder(Long purchaseOrderId) {
        ShoppingCart shoppingCart = this.getById(purchaseOrderId);
        if(shoppingCart.getOrderStatus().equals("CLOSE")) {
            throw new NotFoundException("Not permitted");
        }

        validStockQuantityAndDueDate(shoppingCart);

        shoppingCart.setOrderStatus("CLOSE");

        shoppingCartRepo.save(shoppingCart);

        batchRepo.saveAll(updateStockAfterPurchaseOrder(shoppingCart));

        return ShoppingCartDTO.builder()
                .id(shoppingCart.getId())
                .totalPrice(shoppingCart.getShoppingCartProducts().stream()
                        .reduce(0D,
                                (partialTotalPrice, shoppingCartProduct) ->
                                        partialTotalPrice + shoppingCartProduct.getProduct().getPrice(), Double::sum)
                )
                .build();
    }

    @Override
    public void removeProductToShoppingCart(Long buyerId, ShoppingCartProductDto shoppingCartProductDto) {

    }

    private void validStockQuantityAndDueDate(ShoppingCart shoppingCart) {
        shoppingCart.getShoppingCartProducts().stream().forEach(p -> {
            Long totalOfQuantityInStock = warehouseService.getStockOfProductById(p.getProduct().getId()).getWarehouses().stream()
                    .reduce(0L,
                            (partialQuantity, warehouse) -> partialQuantity + warehouse.getTotalQuantity(), Long::sum);
            if(totalOfQuantityInStock < p.getQuantity() ) {
                throw new ExceededStock("Quantity of " + p.getProduct().getName() + " in purchase order exceeds current stock value. Total quantity in stock: "
                        + totalOfQuantityInStock);
            }
            int totalOfQuantityInStockWithDueDateAvailable = 0;
            for(Batch b : p.getProduct().getListBatch()) {
                if(b.getDueDate().isAfter(LocalDate.now().plusWeeks(3))) {
                    totalOfQuantityInStockWithDueDateAvailable += b.getCurrentQuantity();
                }
            }
            if(totalOfQuantityInStockWithDueDateAvailable < p.getQuantity() ) {
                throw new NotFoundException("Can't add " + p.getProduct().getName() + " to shopping cart. " + p.getProduct().getName() + " expiring date already expirated.");
            }
        });
    }

    private List<Batch> updateStockAfterPurchaseOrder(ShoppingCart shoppingCart) {
        List<Batch> batchList = new ArrayList<>();
        shoppingCart.getShoppingCartProducts().stream().forEach(p -> {
            Integer quantity = p.getQuantity();
            for(Batch b : p.getProduct().getListBatch()) {
                if(b.getDueDate().isAfter(LocalDate.now().plusWeeks(3))) {
                    if (quantity == 0) {
                        break;
                    } else if(quantity > b.getCurrentQuantity()) {
                        quantity = quantity - b.getCurrentQuantity();
                        b.setCurrentQuantity(0);
                    } else if(quantity < b.getCurrentQuantity()) {
                        b.setCurrentQuantity(b.getCurrentQuantity() - quantity);
                        quantity = 0;
                    } else {
                        b.setCurrentQuantity(0);
                        quantity = 0;
                    }
                    batchList.add(b);
                }
            }
        });
        return batchList;
    }

    private ShoppingCartDTO returnShoppingCartDTO(ShoppingCart shoppingCart) {
        return ShoppingCartDTO.builder()
                .id(shoppingCart.getId())
                .totalPrice(shoppingCart.getShoppingCartProducts().stream()
                        .reduce(0D,
                                (partialTotalPrice, shoppingCartProduct) ->
                                        partialTotalPrice + (shoppingCartProduct.getProduct().getPrice() * shoppingCartProduct.getQuantity()), Double::sum)
                )
                .build();
    }
}
