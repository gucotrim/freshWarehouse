package com.meli.freshWarehouse.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class BatchListProductResponseDto {
    private Long batchNumber;
    private Integer currentQuantity;
    private LocalDate dueDate;
}
