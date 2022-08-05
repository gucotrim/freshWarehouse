package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.RepresentativeDto;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Representative;
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
        // findById representative.warehouseId

        return representativeRepository.save(Representative.builder()
                .name(representativeDto.getName())
                //warehouse()
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
        //representative.setWarehouse(newWarehouse);
        return null;
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
