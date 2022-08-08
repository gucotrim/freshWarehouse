package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.RepresentativeDto;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Representative;
import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.repository.RepresentativeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentativeService implements RepresentativeServiceInterface{

    private final RepresentativeRepository representativeRepository;

    public RepresentativeService(RepresentativeRepository representativeRepository) {
        this.representativeRepository = representativeRepository;
    }

    @Override
    public Representative save(RepresentativeDto representativeDto) {
        // findById representative.warehouseId Address 1', 'City 1', 'Brasil', 1, 'DF'

        return representativeRepository.save(Representative.builder()
                .name(representativeDto.getName())
                .warehouse(Warehouse.builder()
                        .id(1)
                        .address("Address 1")
                        .city("City 1")
                        .country("Brasil")
                        .number(1)
                        .state("DF")
                        .build())
                .build());
    }

    @Override
    public List<Representative> findAll() {
        return representativeRepository.findAll();
    }

    @Override
    public Representative update(long id, RepresentativeDto representativeDto) {
        Representative representative = this.findById(id);

        // newWarehouse = findById representative.warehouseId

        representative.setName(representativeDto.getName());
        representative.setWarehouse(Warehouse.builder()
                .id(2)
                .address("Address 1")
                .city("City 1")
                .country("Brasil")
                .number(1)
                .state("DF")
                .build());
        return representativeRepository.save(representative);
    }

    @Override
    public void delete(Long id) {
        representativeRepository.delete(this.findById(id));
    }

    @Override
    public Representative findById(long id) {
        return representativeRepository.findById(id).orElseThrow(() -> new NotFoundException("No representative was found with this id"));
    }

    @Override
    public boolean existsById(long id) {
        return representativeRepository.existsById(id);
    }
}
