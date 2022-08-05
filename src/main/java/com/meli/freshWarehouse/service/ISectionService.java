package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.SectionDto;
import com.meli.freshWarehouse.model.Section;

import java.util.List;


/**
 * Interface to section service
 */
public interface ISectionService {
    SectionDto save(Section newSection);

    List<SectionDto> getAllSection();

    SectionDto getById(long id);

    SectionDto updateSection(Section section);

    void deleteSectionById(long id);
}
