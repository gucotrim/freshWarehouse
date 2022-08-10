package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDueDateRepository extends JpaRepository<Representative, Long> {

    @Query(value = "" +
            "SELECT  b.id as batchNumber, p.id as productId, s.name as productTypeId, b.due_date as dueDate, b.initial_quantity as quantity  FROM batch b" +
            "INNER JOIN product p on b.id_product = p.id" +
            "INNER JOIN section s on b.id_section = s.id and p.id_section = s.id" +
            "INNER JOIN warehouse w on s.id_warehouse = w.id" +
            "INNER JOIN representative r on w.id = r.id_warehouse " +
            "WHERE s.id  = :sectionId " +
            "AND b.due_date BETWEEN NOW() " +
            "AND (DATE_ADD(NOW(), INTERVAL :amountOfDays DAY)) " +
            "AND s.name = :sectionName" +
            "ORDER BY b.due_date DESC", nativeQuery = true
    )
    List<Batch> getBatchesByExpiringDate(@Param("sectionId") Long sectionId, @Param("amountOfDays") Integer amountOfDays, @Param("sectionName") String sectionName);
}
