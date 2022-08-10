package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.repository.IDueDateRepository;
import com.meli.freshWarehouse.service.IDueDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/due-date")
public class DueDateController {

    @Autowired
    private IDueDateRepository dueDateRepository;

    @GetMapping
    public ResponseEntity<List<Batch>> getBatchesByExpiringDate(
            @RequestParam(required = false) Long sectionId,
            @RequestParam(required = false) Integer amountOfDays,
            @RequestParam(required = false) String sectionName
    ) {
        return new ResponseEntity<>(dueDateRepository.getBatchesByExpiringDate(sectionId, amountOfDays, sectionName), HttpStatus.OK);
    }

}
