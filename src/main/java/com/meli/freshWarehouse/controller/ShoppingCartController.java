package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.ShoppingCartDTO;
import com.meli.freshWarehouse.dto.ShoppingCartProductDto;
import com.meli.freshWarehouse.model.ShoppingCart;
import com.meli.freshWarehouse.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService purchaseOrderService;

    public ShoppingCartController(ShoppingCartService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping("/{buyerId}")
    public ResponseEntity<ShoppingCartDTO> addProductToShoppingCart(@PathVariable Long buyerId, @RequestBody ShoppingCartProductDto shoppingCartProductDto) {
        return new ResponseEntity<>(purchaseOrderService.addProductToShoppingCart(buyerId, shoppingCartProductDto), HttpStatus.OK);
    }

    @DeleteMapping("/{buyerId}")
    public ResponseEntity<Void> removeProductToShoppingCart(@PathVariable Long buyerId, @RequestBody ShoppingCartProductDto shoppingCartProductDto) {
        purchaseOrderService.removeProductToShoppingCart(buyerId, shoppingCartProductDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{purchaseOrderId}")
    public ResponseEntity<ShoppingCart> getPurchaseOrderById(@PathVariable Long purchaseOrderId) {
        return ResponseEntity.ok().body(purchaseOrderService.getById(purchaseOrderId));
    }

    @PutMapping("/{purchaseOrderId}")
    public ResponseEntity<ShoppingCartDTO> finalizePurchaseOrder(@PathVariable Long purchaseOrderId) {
        return ResponseEntity.ok().body(purchaseOrderService.finalizePurchaseOrder(purchaseOrderId));
    }
}
