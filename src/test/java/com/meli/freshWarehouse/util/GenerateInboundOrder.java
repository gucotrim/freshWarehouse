package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.*;
import com.meli.freshWarehouse.model.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GenerateInboundOrder {

    public static InboundOrderDto validInboundOrderDto1() {

        InboundOrderDto inboundOrderDto = new InboundOrderDto();
        inboundOrderDto.setOrderDate(String.valueOf(LocalDate.parse("2022-08-09",
                DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

        inboundOrderDto.setRepresentativeId(1L);
        inboundOrderDto.setBatchStockList(GenerateBachStock.validBatchResponseDto());
        inboundOrderDto.setSectionId(1L);

        return inboundOrderDto;

    }

    public static InboundOrderResponseDto inboundOrderResponseDto() {
        return InboundOrderResponseDto.builder()
                .order(OrderResponseDto.builder()
                        .id(1L)
                        .orderDate(LocalDate.parse("2022-08-15", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .build())
                .batchStockList(Arrays.asList(BatchResponseDto.builder()
                        .id(1L)
                        .productId(1L)
                        .minimumTemperature(5F)
                        .currentTemperature(7F)
                        .initialQuantity(5)
                        .currentQuantity(5)
                        .manufacturingDate(LocalDate.parse("2022-08-14", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .manufacturingTime(LocalDateTime.parse("2021-08-14T14:24:54"))
                        .dueDate(LocalDate.parse("2022-08-25", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .build()))
                .build();
    }

    public static InboundOrderDto inboundOrderDto() {
        Set<BatchDto> batchDtoSetList = new HashSet<>();
        batchDtoSetList.add(BatchDto.builder()
                .productId(1L)
                .minimumTemperature(5F)
                .currentTemperature(7F)
                .initialQuantity(5)
                .currentQuantity(5)
                .manufacturingDate("2022-08-14")
                .manufacturingTime("2021-08-14T14:24:54")
                .dueDate("2022-08-25")
                .build());
        return InboundOrderDto.builder()
                .orderDate("2022-08-15")
                .representativeId(1L)
                .sectionId(1L)
                .batchStockList(batchDtoSetList)
                .build();
    }
}
