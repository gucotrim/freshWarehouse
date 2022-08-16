package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.RepresentativeDTO;
import com.meli.freshWarehouse.exception.RepresentativeNotFoundException;
import com.meli.freshWarehouse.model.Representative;
import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.repository.RepresentativeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentativeService implements IRepresentativeService {

    private final RepresentativeRepo representativeRepository;
    private final IWarehouseService warehouseService;

    public RepresentativeService(RepresentativeRepo representativeRepository, IWarehouseService warehouseService) {
        this.representativeRepository = representativeRepository;
        this.warehouseService = warehouseService;
    }

    @Override
    public Representative save(RepresentativeDTO representativeDto) {
        Warehouse warehouse = warehouseService.getWarehouseById(representativeDto.getWarehouseId());

        return representativeRepository.save(Representative.builder()
                .name(representativeDto.getName())
                .warehouse(warehouse)
                .build());
    }

    @Override
    public List<Representative> findAll() {
        return representativeRepository.findAll();
    }

    @Override
    public Representative update(Long id, RepresentativeDTO representativeDto) {
        Representative representative = this.findById(id);
        Warehouse warehouse = warehouseService.getWarehouseById(representativeDto.getWarehouseId());


        representative.setName(representativeDto.getName());
        representative.setWarehouse(warehouse);
        return representativeRepository.save(representative);
    }

    @Override
    public void delete(Long id) {
        representativeRepository.deleteById(this.findById(id).getId());
    }

    @Override
    public Representative findById(Long id) {
        return representativeRepository.findById(id).orElseThrow(()
                -> new RepresentativeNotFoundException("No representative was found with this id: " + id));
    }

    @Override
    public boolean existsById(Long id) {
        return representativeRepository.existsById(id);
    }
}
