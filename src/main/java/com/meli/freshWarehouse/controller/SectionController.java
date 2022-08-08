package com.meli.freshWarehouse.controller;


import com.meli.freshWarehouse.dto.SectionDto;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to access end-points Section
 */
@RestController
@RequestMapping("/api/v1/fresh-products/section")
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    /**
     * Save a new section
     *
     * @param newSection
     * @return The section saved.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/section">Saves a section</a>
     */
    @PostMapping
    public ResponseEntity<Section> saveNewSection(@RequestBody SectionDto newSection) {
        return new ResponseEntity<>(sectionService.save(newSection), HttpStatus.CREATED);
    }

    /**
     * Get all section
     *
     * @return All Section
     * @see <a href="http://localhost:8080/api/v1/fresh-products/section">Get All Section</a>
     */
    @GetMapping
    public ResponseEntity<List<Section>> getAllSection() {
        return new ResponseEntity<>(sectionService.getAllSection(), HttpStatus.OK);
    }

    /**
     * Get a section by id.
     *
     * @param id
     * @return a Section
     * @throws NotFoundException When a section doesn't exist.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/section">Get Section by id</a>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable long id) {
        return ResponseEntity.ok(sectionService.getById(id));
    }

    /**
     * Update a section by id
     *
     * @param section
     * @return
     * @see <a href="http://localhost:8080/api/v1/fresh-products/section">Update section</a>
     */
    @PutMapping("/{id}")
    public ResponseEntity<SectionDto> updateSection(@RequestBody Section section) {
        return ResponseEntity.ok(sectionService.updateSection(section));
    }

    /**
     * Delete a section by id
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        sectionService.deleteSectionById(id);
        return ResponseEntity.noContent().build();
    }

}
