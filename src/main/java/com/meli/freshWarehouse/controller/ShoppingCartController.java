package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.ShoppingCartDTO;
import com.meli.freshWarehouse.dto.ShoppingCartProductDto;
import com.meli.freshWarehouse.model.PurchaseOrder;
import com.meli.freshWarehouse.model.ShoppingCart;
import com.meli.freshWarehouse.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/{buyerId}")
    public ResponseEntity<ShoppingCartDTO> addProductToShoppingCart(@PathVariable Long buyerId, @RequestBody @Valid ShoppingCartProductDto shoppingCartProductDto) {
        return new ResponseEntity<>(shoppingCartService.addProductToShoppingCart(buyerId, shoppingCartProductDto), HttpStatus.CREATED);
    }

    @PutMapping("/{buyerId}")
    public ResponseEntity<ShoppingCartDTO> updateProductQuantityInShoppingCart(@PathVariable Long buyerId, @RequestBody @Valid ShoppingCartProductDto shoppingCartProductDto) {
        return new ResponseEntity<>(shoppingCartService.updateProductQuantityInShoppingCart(buyerId, shoppingCartProductDto), HttpStatus.OK);
    }

    @DeleteMapping("/{buyerId}/{productId}")
    public ResponseEntity<Void> removeProductFromShoppingCart(@PathVariable Long buyerId, @PathVariable Long productId) {
        shoppingCartService.removeProductFromShoppingCart(buyerId, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{shoppingCartId}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable Long shoppingCartId) {
        return ResponseEntity.ok().body(shoppingCartService.getShoppingCartById(shoppingCartId));
    }

    @PutMapping("/finalizePurchaseOrder/{shoppingCartId}")
    public ResponseEntity<PurchaseOrder> finalizePurchaseOrder(@PathVariable Long shoppingCartId) {
        return ResponseEntity.ok().body(shoppingCartService.finalizeShoppingCart(shoppingCartId));
    }
}
