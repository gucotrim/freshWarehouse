package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.InboundOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
public class InboundOrderController {


    /**
     * Updates a inboundorder by id
     *
     * @return Update the inboundorder with the informed id.
     * @throws InboundOrderNotFoundException When a inboundorder with the informed ID isn't found.
     * @see <a href="http://localhost:8080/api/v1/inboundorder">Update a inboundorder</a>
     */
    @PutMapping("/{id}")
    public ResponseEntity<InboundOrder> update(@PathVariable Long id, @RequestBody @Valid InboundOrderDto inboundOrderDto) {
        return ResponseEntity.ok(inboundOrderService.update(id, inboundOrderDto));
    }


}
