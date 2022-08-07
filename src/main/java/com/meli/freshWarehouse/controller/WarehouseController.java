package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.WarehouseDTO;



import com.meli.freshWarehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<?> saveWarehouse(@RequestBody @Valid WarehouseDTO warehouseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseService.createWarehouse(warehouseDTO));
    }

}
