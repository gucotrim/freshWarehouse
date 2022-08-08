package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.SectionDto;
import com.meli.freshWarehouse.model.Section;

import java.util.List;


/**
 * Interface to section service
 */
public interface ISectionService {
    Section save(SectionDto newSection);

    List<Section> getAllSection();

    Section getById(long id);

    Section updateSection(Long id, SectionDto section);

    void deleteSectionById(long id);

    boolean existById(long id);
}
