package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.SectionDto;
import com.meli.freshWarehouse.exception.SectionIdNotFoundException;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.repository.ISectionRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service to implement Section
 */
@Service
public class SectionService implements ISectionService {

    private final ISectionRepo sectionRepo;
    private final IWarehouseService warehouseService;


    public SectionService(ISectionRepo sectionRepo, IWarehouseService warehouseService) {
        this.sectionRepo = sectionRepo;
        this.warehouseService = warehouseService;
    }

    @Override
    public Section save(SectionDto newSection) {

        Warehouse findWarehouse = warehouseService
                .getWarehouseById(newSection.getIdWarehouse());

        return sectionRepo.save(Section.builder()
                        .name(newSection.getName())
                        .availableSpace(newSection.getAvailableSpace())
                        .warehouse(findWarehouse)
                .build());

    }

    @Transactional
    public List<Section> getAllSection() {
       return sectionRepo.findAll();
    }

    @Override
    public Section findById(Long id) {

        return sectionRepo.findById(id)
                .orElseThrow(() -> new SectionIdNotFoundException(
                        "Section not found by id: " + id
                ));
    }

    @Override
    public Section updateSection(Long id, SectionDto section) {

        Section existsSection = this.findById(id);
        Warehouse findWarehouse = warehouseService.getWarehouseById(existsSection.getWarehouse().getId());

        existsSection.setName(section.getName());
        existsSection.setAvailableSpace(section.getAvailableSpace());
        existsSection.setWarehouse(findWarehouse);

        return sectionRepo.save(existsSection);

    }

    @Override
    public void deleteSectionById(Long id) {

        Optional<Section> sectionFound = sectionRepo.findById(id);
        if (sectionFound.isEmpty()) {
            throw new SectionIdNotFoundException("Section not found by id: " + id);
        }
        sectionRepo.deleteById(id);

    }

    @Override
    public boolean existById(Long id){
        return sectionRepo.existsById(id);
    }

    @Override
    public Set<Section> findAllById(Set<Long> sectionsId) {
        Set<Section> sections = new HashSet<>();
        sectionsId.forEach(s -> {
            sections.add(this.findById(s));
        });
        return sections;
    }

}
