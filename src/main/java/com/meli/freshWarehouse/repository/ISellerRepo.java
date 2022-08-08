package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISellerRepo extends JpaRepository<Seller,Long> {
}
