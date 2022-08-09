package com.meli.freshWarehouse.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class OrderResponseDto {
    private Long id;
    private LocalDate orderDate;
}
