package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.dto.WarehouseForProductStockResponseDTO;
import com.meli.freshWarehouse.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepo extends JpaRepository<Batch, Long> {
//    SELECT SUM(current_quantity), id_warehouse
//    FROM batch b LEFT JOIN section s
//    ON b.id_section = s.id
//    WHERE b.id_product = 1
//    GROUP BY s.id_warehouse;
    @Query("SELECT new com.meli.freshWarehouse.dto.WarehouseForProductStockResponseDTO(s.warehouse.id, SUM(b.currentQuantity)) FROM Batch b LEFT JOIN Section s ON b.section.id = s.id WHERE b.product.id = :productId GROUP BY s.warehouse.id")
    List<WarehouseForProductStockResponseDTO> getStockOfProductById(@Param("productId") Long productId);
}
