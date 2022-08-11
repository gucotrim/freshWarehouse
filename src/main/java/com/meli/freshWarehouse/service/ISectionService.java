package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.SectionDto;
import com.meli.freshWarehouse.model.Section;

import java.util.List;
import java.util.Set;


/**
 * Interface to section service
 */
public interface ISectionService {
    Section save(SectionDto newSection);

    List<Section> getAllSection();

    Section findById(Long id);

    Section updateSection(Long id, SectionDto section);

    void deleteSectionById(Long id);

    boolean existById(Long id);

    Set<Section> findAllById(Set<Long> sectionsId);
}
