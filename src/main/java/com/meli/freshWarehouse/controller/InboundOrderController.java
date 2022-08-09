package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.dto.InboundOrderResponseDto;
import com.meli.freshWarehouse.service.InboundOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller to access end-points inbound order.
 */
@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
public class InboundOrderController {

    private final InboundOrderService inboundOrderService;

    public InboundOrderController(InboundOrderService inboundOrderService) {
        this.inboundOrderService = inboundOrderService;
    }

    /**
     * Save a new inbound order
     *
     * @param inboundOrderDto
     * @return saved inbound order
     */
    @PostMapping
    public ResponseEntity<InboundOrderResponseDto> save(@RequestBody @Valid InboundOrderDto inboundOrderDto) {
        return new ResponseEntity<>(inboundOrderService.save(inboundOrderDto), HttpStatus.CREATED);
    }
}
