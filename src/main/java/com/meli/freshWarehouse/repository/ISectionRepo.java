package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface Section Repository
 */
@Repository
public interface ISectionRepo extends JpaRepository<Section, Long> {

    List<Section> findByName(String sectionName);

}
