package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.ProductStockResponseDTO;
import com.meli.freshWarehouse.dto.WarehouseDTO;
import com.meli.freshWarehouse.exception.WarehouseNotFoundException;
import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.repository.WarehouseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService implements IWarehouseService {

    private final WarehouseRepo warehouseRepo;
    public WarehouseService(WarehouseRepo warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }

    @Override
    public Warehouse createWarehouse(WarehouseDTO createWarehouse) {
        return warehouseRepo.save(createWarehouse.toModel());
    }

    @Override
    public List<Warehouse> listAll() {
        return warehouseRepo.findAll();
    }

    @Override
    public Warehouse getWarehouseById(Long warehouseId) {
        return warehouseRepo.findById(warehouseId).orElseThrow(() ->
                new WarehouseNotFoundException("Warehouse ID not found."));
    }

    @Override
    public Warehouse updateWarehouse(Warehouse updateWarehouse) {
        boolean exists = warehouseRepo.existsById(updateWarehouse.getId());
        if(!exists) throw new WarehouseNotFoundException("Warehouse ID not found.");
        return warehouseRepo.save(updateWarehouse);

    }

    @Override
    public ProductStockResponseDTO getStockOfProductById(Long productId) {


        return ProductStockResponseDTO.builder().build();
    }

}
