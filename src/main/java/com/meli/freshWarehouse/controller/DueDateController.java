package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.DueDateResponseDto;
import com.meli.freshWarehouse.exception.SectionIdNotFoundException;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/due-date")
public class DueDateController {

    @Autowired
    private BatchService batchService;


    /**
     * Get a list of batches by amount of days to expire and section id.
     *
     * @param sectionId and amountOfDays
     * @return a Batch List
     * @throws SectionIdNotFoundException When a section doesn't exist.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/due-date/">Get Batch list by id and amount of days to expire</a>
     */
    @GetMapping
    public ResponseEntity<List<DueDateResponseDto>> getBatchesByExpiringDate(
            @RequestParam Long sectionId,
            @RequestParam(required = false) Integer amountOfDays) {
        return new ResponseEntity<>(batchService.getBySectionAndDueDate(sectionId, amountOfDays), HttpStatus.OK);
    }

    /**
     * Get a list of batches by amount of days to expire and section name.
     *
     * @param sectionName and amountOfDays
     * @return a Batch List
     * @throws SectionIdNotFoundException When a section doesn't exist.
     * @see <a href="http://localhost:8080/api/v1/fresh-products/due-date/list/">Get Batch list by id and amount of days to expire</a>
     */
    @GetMapping("/list")
    public ResponseEntity<List<DueDateResponseDto>> getBatchesByExpiringDateAndSectionName(
            @RequestParam String sectionName,
            @RequestParam(required = false) Integer amountOfDays) {
        return new ResponseEntity<>(batchService.getBySectionAndDueDate(sectionName, amountOfDays), HttpStatus.OK);
    }

}
