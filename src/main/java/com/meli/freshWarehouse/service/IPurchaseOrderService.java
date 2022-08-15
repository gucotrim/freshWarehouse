package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.PurchaseOrderDto;
import com.meli.freshWarehouse.dto.PurchaseOrderTotalPriceDTO;
import com.meli.freshWarehouse.model.PurchaseOrder;

public interface IPurchaseOrderService {
    PurchaseOrderTotalPriceDTO save(PurchaseOrderDto purchaseOrderDto);
    PurchaseOrder getById(Long purchaseOrderId);
    PurchaseOrderTotalPriceDTO finalizePurchaseOrder(Long purchaseOrderId);
}
