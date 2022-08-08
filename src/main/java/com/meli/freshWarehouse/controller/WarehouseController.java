package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.WarehouseDTO;


import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
public class WarehouseController {


    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseEntity<Warehouse> saveWarehouse(@RequestBody @Valid WarehouseDTO warehouseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseService.createWarehouse(warehouseDTO));
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> listWarehouse() {
        return ResponseEntity.status(HttpStatus.OK).body(warehouseService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(warehouseService.getWarehouseById(id));
    }

    @PutMapping
    public ResponseEntity <Warehouse> updateWarehouse(@RequestBody @Valid Warehouse warehouse) {
       return ResponseEntity.status(HttpStatus.OK).body(warehouseService.updateWarehouse(warehouse));
    }
}
