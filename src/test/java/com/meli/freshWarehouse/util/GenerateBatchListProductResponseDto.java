package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.BatchListProductResponseDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenerateBatchListProductResponseDto {

    public static List<BatchListProductResponseDto> validBatchListProductResponseDto(){
        List<BatchListProductResponseDto> list = new ArrayList<>();
        list.add(BatchListProductResponseDto.builder()
                .batchNumber(1L)
                .currentQuantity(20)
                .dueDate(LocalDate.now())
                .build());
        return list;
    }
}
