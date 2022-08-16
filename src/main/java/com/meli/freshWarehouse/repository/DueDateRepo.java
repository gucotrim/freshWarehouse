package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.dto.DueDateResponseDto;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DueDateRepo extends JpaRepository<Batch, Long> {

    @Query("SELECT new com.meli.freshWarehouse.dto.DueDateResponseDto(b.id, b.product.id, b.section.name, b.dueDate, b.initialQuantity) FROM Batch b WHERE b.section = :section AND b.dueDate >= :startDate AND b.dueDate <= :endDate ORDER BY b.dueDate")
    List<DueDateResponseDto> getBySectionAndDueDate(Section section, LocalDate startDate, LocalDate endDate);

    @Query("SELECT new com.meli.freshWarehouse.dto.DueDateResponseDto(b.id, b.product.id, b.section.name, b.dueDate, b.initialQuantity) FROM Batch b WHERE b.section = :section ORDER BY b.dueDate")
    List<DueDateResponseDto> getBySection(Section section);
}
