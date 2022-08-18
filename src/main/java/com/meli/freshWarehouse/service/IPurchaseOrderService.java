package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.ShoppingCartDTO;
import com.meli.freshWarehouse.model.Buyer;
import com.meli.freshWarehouse.model.PurchaseOrder;
import com.meli.freshWarehouse.model.ShoppingCart;

import java.util.List;

public interface IPurchaseOrderService {
    PurchaseOrder save(ShoppingCart shoppingCart);
    PurchaseOrder getPurchaseOrderById(Long purchaseOrderId);
    List<PurchaseOrder> findAllByBuyerId(Long buyerId);
    PurchaseOrder updateStatusOrder(Long purchaseOrderId, String statusOrder);
}
