package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepo extends JpaRepository<Batch, Long> {
}
