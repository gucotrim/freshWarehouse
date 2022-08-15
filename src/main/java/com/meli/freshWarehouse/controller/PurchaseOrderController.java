package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.DueDateResponseDto;
import com.meli.freshWarehouse.dto.PurchaseOrderDto;
import com.meli.freshWarehouse.exception.SectionIdNotFoundException;
import com.meli.freshWarehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/purchase-order")
public class PurchaseOrderController {

    private final ProductService productService;

    public PurchaseOrderController(ProductService productService) {
        this.productService = productService;
    }

}
