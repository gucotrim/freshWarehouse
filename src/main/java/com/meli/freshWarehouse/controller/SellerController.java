package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.SellerDto;
import com.meli.freshWarehouse.exception.NotFoundException;
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

    /**
     * Gets the id of a seller
     *
     * @param id - Property id.
     * @return A seller, return an exception if property isn't found.
     * @throws NotFoundException When a property doesn't exist.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/seller/{id}">Get seller</a>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Seller> findById(@PathVariable long id) {
        return ResponseEntity.ok(service.getSellerById(id));
    }

    /**
     * Save a new seller
     *
     * @param sellerDto - Property sellerDto.
     * @return A seller.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/seller"> Post seller</a>
     */
    @PostMapping
    public ResponseEntity<Seller> insertSeller(@RequestBody @Valid SellerDto sellerDto) {
        return new ResponseEntity(service.insertSeller(sellerDto),HttpStatus.CREATED);
    }

    /**
     * Update a seller with id
     *
     * @param seller - Property seller.
     * @return A seller.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/seller"> Update seller</a>
     */
    @PutMapping
    public ResponseEntity<Seller> updateSeller(@RequestBody @Valid Seller seller){
        return new ResponseEntity(service.updateSeller(seller),HttpStatus.OK);
    }

    /**
     * Delete a seller with id
     *
     * @param id - Property id.
     * @return Void
     * @see <a href="http://localhost:8080/api/v1/fresh-products/seller"> Delete seller</a>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable long id) {
        service.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
