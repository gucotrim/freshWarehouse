package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.RepresentativeDTO;
import com.meli.freshWarehouse.model.Representative;

import java.util.List;

public interface IRepresentativeService {
    Representative save(RepresentativeDTO representativeDto);
    List<Representative> findAll();
    Representative update(Long id, RepresentativeDTO representativeDto);
    void delete(Long id);
    Representative findById(Long id);
    boolean existsById(Long id);
}
