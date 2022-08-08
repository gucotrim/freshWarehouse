package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.model.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentativeRepo extends JpaRepository<Representative, Long> {
}
