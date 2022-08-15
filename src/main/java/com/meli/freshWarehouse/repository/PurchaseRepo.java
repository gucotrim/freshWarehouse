package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepo extends JpaRepository<PurchaseOrder, Long> {
}
