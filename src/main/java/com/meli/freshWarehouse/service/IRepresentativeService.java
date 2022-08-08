package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.RepresentativeDTO;
import com.meli.freshWarehouse.model.Representative;

import java.util.List;

public interface IRepresentativeService {
    Representative save(RepresentativeDTO representativeDto);
    List<Representative> findAll();
    Representative update(long id, RepresentativeDTO representativeDto);
    void delete(Long id);
    Representative findById(long id);
    boolean existsById(long id);
}
