package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findAllByBuyer_Id(Long buyerId);
}
