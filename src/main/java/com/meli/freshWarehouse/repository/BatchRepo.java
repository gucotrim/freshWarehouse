package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BatchRepo extends JpaRepository<Batch, Long> {

    @Query("SELECT b FROM Batch b WHERE b.section = :section AND b.dueDate >= :startDate AND b.dueDate <= :endDate ORDER BY b.dueDate")
    List<Batch> getBySectionAndDueDate(Section section, LocalDate startDate, LocalDate endDate);

    @Query("SELECT b FROM Batch b WHERE b.section = :section ORDER BY b.dueDate")
    List<Batch> getBySection(Section section);

}

