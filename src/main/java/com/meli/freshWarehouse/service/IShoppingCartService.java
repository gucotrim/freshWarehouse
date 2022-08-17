package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.PurchaseOrderDto;
import com.meli.freshWarehouse.dto.ShoppingCartDTO;
import com.meli.freshWarehouse.dto.ShoppingCartProductDto;
import com.meli.freshWarehouse.model.ShoppingCart;

public interface IShoppingCartService {
    ShoppingCartDTO addProductToShoppingCart(Long buyerId, ShoppingCartProductDto shoppingCartProductDto);
    ShoppingCart getById(Long purchaseOrderId);
    ShoppingCartDTO finalizePurchaseOrder(Long purchaseOrderId);
    void removeProductToShoppingCart(Long buyerId, ShoppingCartProductDto shoppingCartProductDto);
}
