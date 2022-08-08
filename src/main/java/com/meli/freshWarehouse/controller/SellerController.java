package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.model.Seller;
import com.meli.freshWarehouse.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/fresh-products/seller")
public class SellerController {

    @Autowired
    private SellerService service;

    @GetMapping("/{id}")
    public ResponseEntity<Seller> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.getSellerById(id));
    }

    @PostMapping
    public ResponseEntity<Seller> insertSeller(@RequestBody @Valid Seller seller) {
        return new ResponseEntity(service.insertSeller(seller),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Seller> updateSeller(@RequestBody @Valid Seller seller){
        return new ResponseEntity(service.updateSeller(seller),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable long id) {
        service.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
