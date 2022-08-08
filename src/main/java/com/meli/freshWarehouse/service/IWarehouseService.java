package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.WarehouseDTO;
import com.meli.freshWarehouse.model.Warehouse;

import java.util.List;

public interface IWarehouseService {

    Warehouse createWarehouse(WarehouseDTO createWarehouse);
    List<Warehouse> listAll();
    Warehouse getWarehouseById(Long warehouseId);
    Warehouse updateWarehouse (Warehouse updateWarehouse);
}
