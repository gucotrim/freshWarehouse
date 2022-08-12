package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.DueDateResponseDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenerateDueDateResponseDto {
    public static final List<DueDateResponseDto> validDueDateResponseDto1() {
        List<DueDateResponseDto> dueDateResponseDtoList = new ArrayList<>();

        DueDateResponseDto dueDateResponseDto1 = DueDateResponseDto.builder()
                .batchNumber(1L)
                .productId(GenerateProduct.validFreshProduct().getId())
                .productTypeId("Fresh")
                .dueDate(LocalDate.parse("2022-08-20"))
                .quantity(2)
                .build();

        dueDateResponseDtoList.add(dueDateResponseDto1);

        return dueDateResponseDtoList;

    }

    public static final List<DueDateResponseDto> validDueDateResponseDto2() {
        List<DueDateResponseDto> dueDateResponseDtoList = new ArrayList<>();

        DueDateResponseDto dueDateResponseDto1 = DueDateResponseDto.builder()
                .batchNumber(1L)
                .productId(GenerateProduct.validFreshProduct().getId())
                .productTypeId("Fresh")
                .dueDate(LocalDate.parse("2022-08-12"))
                .quantity(2)
                .build();

        dueDateResponseDtoList.add(dueDateResponseDto1);

        return dueDateResponseDtoList;

    }
}
