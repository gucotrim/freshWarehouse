package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.service.IDueDateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/due-date")
public class DueDateController {

    private IDueDateService dueDateService;

    @GetMapping
    public ResponseEntity<List<Batch>> getBatchesByExpiringDate(@PathVariable Long idRepresentative) {
        return new ResponseEntity<>(dueDateService.getBatchesByExpiringDate(idRepresentative), HttpStatus.OK);
    }

}
