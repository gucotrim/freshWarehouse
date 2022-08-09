package com.meli.freshWarehouse.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class InboundOrderDto {

        private LocalDate orderDate;
        private Long sectionId;
        private Long representativeId;
        private Set<BatchResponseDto> batchStockList;
}
