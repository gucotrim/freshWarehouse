package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBuyerRepo extends JpaRepository<Buyer,Long> {
}
