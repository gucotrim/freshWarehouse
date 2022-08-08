package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.RepresentativeDTO;
import com.meli.freshWarehouse.model.Representative;
import com.meli.freshWarehouse.service.RepresentativeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Representative API Controller Class
 */
@RestController
@RequestMapping("/api/v1/fresh-products/representative")
public class RepresentativeController {

    private final RepresentativeService representativeService;

    public RepresentativeController(RepresentativeService representativeService) {
        this.representativeService = representativeService;
    }

    /**
     * Save a new representative
     *
     * @return The representative saved.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/representative">Saves a representative</a>
     */
    @PostMapping
    public ResponseEntity<Representative> save(@RequestBody @Valid RepresentativeDTO representativeDto) {
        return new ResponseEntity<>(representativeService.save(representativeDto), HttpStatus.CREATED);
    }

    /**
     * Get all representatives saved
     *
     * @return Get all representatives saved.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/representative">Get all representatives</a>
     */
    @GetMapping
    public ResponseEntity<List<Representative>> findAll() {
        return ResponseEntity.ok(representativeService.findAll());
    }

    /**
     * Get a representative by Id.
     *
     * @param id - Representative id.
     * @return A representative, return an exception if representative isn't found.
     * @throws NotFoundException When a representative doesn't exist.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/representative/{id}">Get a representative by Id</a>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Representative> findById(@PathVariable long id) {
        return ResponseEntity.ok(representativeService.findById(id));
    }

    /**
     * Updates a representative by Id.
     *
     * @param id - Representative id.
     * @return The representative updated, return an exception if representative isn't found.
     * @throws NotFoundException When a representative doesn't exist.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/representative/{id}">Updates a representative by Id</a>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Representative> update(@PathVariable long id, @RequestBody @Valid RepresentativeDTO representativeDto) {
        return ResponseEntity.ok(representativeService.update(id, representativeDto));
    }

    /**
     * Delete a representative by Id.
     *
     * @param id - Representative id.
     * @return Void, return an exception if representative isn't found.
     * @throws NotFoundException When a representative doesn't exist.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/representative/{id}">Delete a representative by Id</a>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        representativeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
