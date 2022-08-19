package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.PurchaseOrderDto;
import com.meli.freshWarehouse.dto.ShoppingCartDTO;
import com.meli.freshWarehouse.dto.ShoppingCartProductDto;
import com.meli.freshWarehouse.model.PurchaseOrder;
import com.meli.freshWarehouse.model.ShoppingCart;

public interface IShoppingCartService {
    ShoppingCartDTO addProductToShoppingCart(Long buyerId, ShoppingCartProductDto shoppingCartProductDto);
    ShoppingCart getShoppingCartById(Long shoppingCartId);
    PurchaseOrder finalizeShoppingCart(Long shoppingCartId);
    void removeProductFromShoppingCart(Long buyerId, Long productId);
    ShoppingCartDTO updateProductQuantityInShoppingCart(Long buyerId, ShoppingCartProductDto shoppingCartProductDto);
}
