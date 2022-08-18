package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.model.PurchaseOrder;
import com.meli.freshWarehouse.service.PurchaseOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/purchase-order")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("/{purchaseOrderId}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable Long purchaseOrderId){
        return ResponseEntity.ok().body(purchaseOrderService.getPurchaseOrderById(purchaseOrderId));
    }

    @GetMapping("/all-buyer-purchase-orders/{buyerId}")
    public ResponseEntity<List<PurchaseOrder>> findAllByBuyerId(@PathVariable Long buyerId){
        return ResponseEntity.ok().body(purchaseOrderService.findAllByBuyerId(buyerId));
    }

    @PutMapping("/{purchaseOrderId}")
    public ResponseEntity<PurchaseOrder> updateStatusOrder(
            @PathVariable Long purchaseOrderId,
            @RequestParam(name = "order-status") String orderStatus
    ){
        return ResponseEntity.ok().body(purchaseOrderService.updateStatusOrder(purchaseOrderId, orderStatus));
    }
}
