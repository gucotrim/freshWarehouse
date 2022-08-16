package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.ProductStockResponseDTO;
import com.meli.freshWarehouse.dto.WarehouseDTO;
import com.meli.freshWarehouse.dto.WarehouseForProductStockResponseDTO;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.exception.WarehouseNotFoundException;
import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.repository.BatchRepo;
import com.meli.freshWarehouse.repository.WarehouseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService implements IWarehouseService {

    private final WarehouseRepo warehouseRepo;
    private final BatchRepo batchRepo;
    public WarehouseService(WarehouseRepo warehouseRepo, BatchRepo batchRepo) {
        this.warehouseRepo = warehouseRepo;
        this.batchRepo = batchRepo;
    }

    @Override
    public Warehouse createWarehouse(Warehouse createWarehouse) {
        return warehouseRepo.save(createWarehouse);
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
        if (exists) {
            return warehouseRepo.save(updateWarehouse);
        }
        throw new WarehouseNotFoundException("Warehouse ID not found.");
    }

    @Override
    public ProductStockResponseDTO getStockOfProductById(Long productId) {
        List<WarehouseForProductStockResponseDTO> warehouses = batchRepo.getStockOfProductById(productId);
        ProductStockResponseDTO productStockResponseDTO = ProductStockResponseDTO.builder()
                .productId(productId)
                .warehouses(
                        warehouses.stream()
                                .filter(w -> w.getTotalQuantity() != 0)
                                .collect(Collectors.toList())
                )
                .build();

        if(productStockResponseDTO.getWarehouses().isEmpty()) {
            throw new NotFoundException("Product not available in stock.");
        }

        return productStockResponseDTO;
    }

}
