package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.repository.BatchRepo;
import com.meli.freshWarehouse.repository.ISectionRepo;
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

    @GetMapping
    public ResponseEntity<List<Batch>> getBatchesByExpiringDate(
            @RequestParam Long sectionId,
            @RequestParam(required = false) Integer amountOfDays) {
        return new ResponseEntity<>(batchService.getBySectionAndDueDate(sectionId, amountOfDays), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Batch>> getBatchesByExpiringDateAndSectionName(
            @RequestParam String sectionName,
            @RequestParam(required = false) Integer amountOfDays) {
        return new ResponseEntity<>(batchService.getBySectionAndDueDate(sectionName, amountOfDays), HttpStatus.OK);
    }

}
