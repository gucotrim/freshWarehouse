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

/**
 * Service to implement Section
 */
@Service
public class SectionService implements ISectionService {

    private final ISectionRepo sectionRepo;
    private final IWarehouseService warehouseService;

    public SectionService(ISectionRepo sectionRepo, IWarehouseService warehouseRepo) {
        this.sectionRepo = sectionRepo;
        this.warehouseService = warehouseRepo;
    }

    @Override
    public Section save(SectionDto newSection) {

        Warehouse findWarehouse = warehouseService.getWarehouseById(newSection.getIdWarehouse());

        return sectionRepo.save(Section.builder()
                        .name(newSection.getName())
                        .availableSpace(newSection.getAvailableSpace())
                        .warehouse(findWarehouse)
                .build());

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
    public Section updateSection(Long id, SectionDto section) {

        Section existsSection = this.getById(id);
        Warehouse findWarehouse = warehouseService.getWarehouseById(section.getIdWarehouse());

        existsSection.setName(section.getName());
        existsSection.setAvailableSpace(section.getAvailableSpace());
        existsSection.setWarehouse(findWarehouse);

        return sectionRepo.save(existsSection);

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
