package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.SectionDto;
import com.meli.freshWarehouse.exception.DataNotFoundException;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.repository.ISectionRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;

/**
 * Service to implement Section
 */
@Service
public class SectionService implements ISectionService {

    private final ISectionRepo sectionRepo;

    public SectionService(ISectionRepo sectionRepo) {
        this.sectionRepo = sectionRepo;
    }

    @Override
    public Section save(SectionDto newSection) {

//        Section section = sectionRepo.save(Section.builder()
//                        .name(newSection.getName())
//                        .n
//                .build())

        return null;
    }

    @Transactional
    public List<Section> getAllSection() {

        try {
            return sectionRepo.findAll();
        } catch (NotFoundException e) {
            throw new DataNotFoundException("Data not found.");
        }

    }

    @Override
    public Section getById(long id) {

        return sectionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Section not found by id: " + id
                ));
    }

    @Override
    public SectionDto updateSection(Section section) {

        Section existsSection = this.getById(section.getId());

        existsSection.setName(section.getName());
        existsSection.setAvailableSpace(section.getAvailableSpace());
        existsSection.setWarehouse(section.getWarehouse());

        Section updatedSection = sectionRepo.save(existsSection);

        return SectionDto.builder()
                .name(updatedSection.getName())
                .availableSpace(updatedSection.getAvailableSpace())
                .build();
    }

    @Override
    public void deleteSectionById(long id) {

        Optional<Section> sectionFound = sectionRepo.findById(id);
        if (sectionFound.isEmpty()) {
            throw new DataNotFoundException("Id not found");
        }
        sectionRepo.deleteById(id);

    }

    @Override
    public boolean existById(long id){
        return sectionRepo.existsById(id);
    }

}
