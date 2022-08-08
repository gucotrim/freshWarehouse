package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.WarehouseDTO;


import com.meli.freshWarehouse.exception.WarehouseNotFoundException;
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
    /**
     * Saves a new warehouse
     * @return Saves a new warehouse.
     * @see <a href="http://localhost:8080/api/v1/warehouse">Saves a warehouse</a>
     */
    @PostMapping
    public ResponseEntity<Warehouse> saveWarehouse(@RequestBody @Valid WarehouseDTO warehouseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseService.createWarehouse(warehouseDTO));
    }
    /**
     * Get all warehouses saved
     *
     * @return Get all warehouses saved.
     * @see <a href="http://localhost:8080/api/v1/warehouse">Gets all warehouses saved</a>
     */
    @GetMapping
    public ResponseEntity<List<Warehouse>> listAllWarehouse() {
        return ResponseEntity.status(HttpStatus.OK).body(warehouseService.listAll());
    }
    /**
     * Gets warehouses by id
     *
     * @return warehouses by id, return an exception if a warehouse ID isn't found.
     * @throws WarehouseNotFoundException When a warehouse ID doesn't exist.
     * @see <a href="http://localhost:8080/api/v1/warehouse/{id}">Get all the warehouses by id</a>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(warehouseService.getWarehouseById(id));
    }
    /**
     * Updates a warehouse by id
     *
     * @return an updated warehouse, returns an exception if a warehouse ID isn't found.
     * @throws WarehouseNotFoundException When a warehouse ID doesn't exist.
     * @see <a href="http://localhost:8080/api/v1/warehouse">Saves a warehouse</a>
     */
    @PutMapping
    public ResponseEntity <Warehouse> updateWarehouse(@RequestBody @Valid Warehouse warehouse) {
       return ResponseEntity.status(HttpStatus.OK).body(warehouseService.updateWarehouse(warehouse));
    }
}
