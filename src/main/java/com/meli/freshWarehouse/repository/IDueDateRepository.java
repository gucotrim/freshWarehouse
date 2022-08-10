package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.dto.DueDateDto;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDueDateRepository extends JpaRepository<Representative, Long> {

    @Query("SELECT new DueDateDto(b.id, p.id, s.name, b.dueDate, b.initialQuantity) FROM Batch b " +
            "INNER JOIN Product p on b.product.id = p.id " +
            "INNER JOIN Section s on b.section.id = s.id and p.section.id = s.id " +
            "INNER JOIN Warehouse w on s.warehouse.id = w.id " +
            "INNER JOIN Representative r on w.id = r.warehouse.id " +
            "WHERE s.id  = :sectionId " +
            "AND s.name = :sectionName " +
            "ORDER BY b.dueDate DESC "
    )
    List<DueDateDto> getBatchesByExpiringDate(@Param("sectionId") Long sectionId,
                                              @Param("amountOfDays") Integer amountOfDays,
                                              @Param("sectionName") String sectionName);
}
