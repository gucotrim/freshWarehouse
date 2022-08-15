package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.BuyerDto;
import com.meli.freshWarehouse.dto.SellerDto;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Buyer;
import com.meli.freshWarehouse.model.Seller;
import com.meli.freshWarehouse.service.BuyerService;
import com.meli.freshWarehouse.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/fresh-products/buyer")
public class BuyerController {

    private final BuyerService service;

    public BuyerController(BuyerService service) {
        this.service = service;
    }

    /**
     * Gets the id of a buyer
     *
     * @param id - Buyer id.
     * @return A buyer, return an exception if buyer isn't found.
     * @throws NotFoundException When a buyer doesn't exist.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/buyer/{id}">Get buyer</a>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Buyer> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBuyerById(id));
    }

    /**
     * Save a new buyer
     *
     * @param buyerDto - BuyerDto buyerDto.
     * @return A buyer.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/buyer"> Post buyer</a>
     */
    @PostMapping
    public ResponseEntity<Buyer> insertBuyer(@RequestBody @Valid BuyerDto buyerDto) {
        return new ResponseEntity(service.insertBuyer(buyerDto),HttpStatus.CREATED);
    }

    /**
     * Update a buyer with id
     *
     * @param id - Buyer id.
     * @param buyerDto - BuyerDto buyerDto.
     * @return A buyer.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/buyer"> Update buyer</a>
     */
    @PutMapping
    public ResponseEntity<Buyer> updateBuyer(@PathVariable Long id, @RequestBody @Valid BuyerDto buyerDto){
        return new ResponseEntity(service.updateBuyer(id, buyerDto),HttpStatus.OK);
    }

    /**
     * Delete a buyer with id
     *
     * @param id - Buyer id.
     * @return Void
     * @see <a href="http://localhost:8080/api/v1/fresh-products/buyer"> Delete buyer</a>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable Long id) {
        service.deleteBuyer(id);
        return ResponseEntity.noContent().build();
    }
}
