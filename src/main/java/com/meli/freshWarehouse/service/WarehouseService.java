package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.WarehouseDTO;
import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.repository.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    WarehouseRepo warehouseRepo;

    @Override
    public Warehouse createWarehouse(WarehouseDTO createWarehouse) {
        return warehouseRepo.save(createWarehouse.toModel());
    }
}
