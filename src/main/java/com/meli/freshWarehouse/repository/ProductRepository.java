package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.dto.ProductPurchaseDto;
import com.meli.freshWarehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.meli.freshWarehouse.dto.ProductPurchaseDto(p.id, p.name, p.price) " +
            "FROM Product p LEFT JOIN Section s on p.seller.id = s.id INNER JOIN Batch b on p.id = b.product.id " +
            "WHERE s.name = :category AND b.dueDate > :dueDate")
    List<ProductPurchaseDto> findBySectionName(String category, LocalDate dueDate);
}
