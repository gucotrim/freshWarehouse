package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.RepresentativeDto;
import com.meli.freshWarehouse.model.Representative;

import java.util.List;

public interface RepresentativeServiceInterface {
    Representative save(RepresentativeDto representativeDto);
    List<Representative> findAll();
    Representative update(long id, RepresentativeDto representativeDto);
    void delete(Long id);
    Representative findById(long id);
    boolean existsById(long id);
}
