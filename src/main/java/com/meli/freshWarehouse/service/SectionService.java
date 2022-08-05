package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.SectionDto;
import com.meli.freshWarehouse.exception.DataNotFoundException;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.repository.ISectionRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service to implement Section
 */
public class SectionService implements ISectionService {

    @Autowired
    private ISectionRepo sectionRepo;

    @Override
    public SectionDto save(Section newSection) {

        if (newSection == null || newSection.getId() != 0) {
            throw new DataNotFoundException("The section cannot be null or have an id defined.");
        }

        Section savedSection = sectionRepo.save(newSection);

        return SectionDto.builder()
                .name(savedSection.getName())
                .availableSpace(savedSection.getAvailableSpace())
                .build();
    }

    @Override
    public List<SectionDto> getAllSection() {

        try {
            List<Section> sectionList = sectionRepo.findAll();

            return sectionList.stream().map(s -> SectionDto.builder()
                            .name(s.getName())
                            .availableSpace(s.getAvailableSpace())
                            .build())
                    .collect(Collectors.toList());

        } catch (NotFoundException e) {
            throw new DataNotFoundException("Data not found.");
        }

    }

    @Override
    public SectionDto getById(long id) {

        try {
            Section foundSection = sectionRepo.findById(id).get();
            return SectionDto.builder()
                    .name(foundSection.getName())
                    .availableSpace(foundSection.getAvailableSpace())
                    .build();
        } catch (NotFoundException e) {
            throw new NotFoundException("Section not found by id: " + id);
        }

    }

    @Override
    public SectionDto updateSection(Section section) {
        if (section == null || section.getId() == 0) {
            throw new NotFoundException("The section cannot be null or have an id..");
        }

        Section updatedSection = sectionRepo.save(section);

        return SectionDto.builder()
                .name(updatedSection.getName())
                .availableSpace(updatedSection.getAvailableSpace())
                .build();
    }

    @Override
    public void deleteSectionById(long id) {

        Optional<Section> sectionFound = sectionRepo.findById(id);
        if(sectionFound.isEmpty()) {
            throw new DataNotFoundException("Id not found");
        }
        sectionRepo.deleteById(id);

    }
}
