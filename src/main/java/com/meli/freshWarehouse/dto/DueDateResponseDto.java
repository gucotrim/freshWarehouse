package com.meli.freshWarehouse.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class DueDateResponseDto {

    private Long batchNumber;

    private Long productId;

    private String productTypeId;

    private LocalDate dueDate;

    private Integer quantity;

    public DueDateResponseDto() {
    }

    public DueDateResponseDto(Long batchNumber, Long productId, String productTypeId, LocalDate dueDate, Integer quantity) {
        this.batchNumber = batchNumber;
        this.productId = productId;
        this.productTypeId = productTypeId;
        this.dueDate = dueDate;
        this.quantity = quantity;
    }

    public DueDateResponseDto toModel() {
        return new DueDateResponseDto(this.batchNumber, this.productId, this.productTypeId, this.dueDate, this.quantity);
    }
}
