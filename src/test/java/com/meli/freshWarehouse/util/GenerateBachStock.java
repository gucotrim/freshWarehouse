package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.BatchDto;
import com.meli.freshWarehouse.dto.BatchResponseDto;
import com.meli.freshWarehouse.model.Batch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class GenerateBachStock {


    public static Batch newBatch1() {
        return Batch.builder()
                .currentTemperature(24F)
                .minimumTemperature(25F)
                .initialQuantity(2)
                .currentQuantity(2)
                .manufacturingDate(LocalDate.parse("2021-06-03T14:24:54"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2021-07-24"))
                .section(GenerateSection.validSection1())
                .order(GenerateOrder.validOrder1())
                .product(GenerateProduct.validProduct1())
                .build();
    }

    public static Batch newBatch2() {
        return Batch.builder()
                .minimumTemperature(15F)
                .currentTemperature(18F)
                .initialQuantity(4)
                .currentQuantity(5)
                .manufacturingDate(LocalDate.parse("2021-06-03"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2021-05-12"))
                .build();


    }

    public static Set<Batch> validBatchResponse() {

        Set<Batch> batchDtoList = new HashSet<>();

        Batch batchResponse1 = Batch.builder()
                .id(1L)
                .minimumTemperature(25F)
                .currentTemperature(14F)
                .initialQuantity(2)
                .currentQuantity(2)
                .manufacturingDate(LocalDate.parse("2021-06-03"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2021-07-24"))
                .order(GenerateOrder.validOrder1())
                .section(GenerateSection.validSection1())
                .product(GenerateProduct.validProduct1())
                .build();

        Batch batchResponse2 = Batch.builder()
                .id(2L)
                .minimumTemperature(15F)
                .currentTemperature(18F)
                .initialQuantity(4)
                .currentQuantity(5)
                .manufacturingDate(LocalDate.parse("2021-06-03"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2021-05-12"))
                .order(GenerateOrder.validOrder2())
                .section(GenerateSection.validSection2())
                .product(GenerateProduct.validProduct2())
                .build();

        batchDtoList.add(batchResponse1);
        batchDtoList.add(batchResponse2);


        return batchDtoList;

    }

    public static Set<BatchResponseDto> validBatchResponseDtoList() {

        Set<BatchResponseDto> batchDtoList = new HashSet<>();

        BatchResponseDto batchResponseDto1 = BatchResponseDto.builder()
                .id(1L)
                .productId(1L)
                .minimumTemperature(25F)
                .currentTemperature(14F)
                .initialQuantity(2)
                .currentQuantity(2)
                .manufacturingDate(LocalDate.parse("2021-06-03"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2021-07-24"))
                .build();

        BatchResponseDto batchResponseDto2 = BatchResponseDto.builder()
                .id(2L)
                .productId(1L)
                .minimumTemperature(15F)
                .currentTemperature(18F)
                .initialQuantity(4)
                .currentQuantity(5)
                .manufacturingDate(LocalDate.parse("2021-06-03"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2021-05-12"))
                .build();

        batchDtoList.add(batchResponseDto1);
        batchDtoList.add(batchResponseDto2);


        return batchDtoList;

    }

    public static Set<BatchDto> validBatchResponseDto() {

        Set<BatchDto> batchDtoList = new HashSet<>();

        BatchDto batchDto1 = BatchDto.builder()
                .productId(1L)
                .minimumTemperature(25F)
                .currentTemperature(14F)
                .initialQuantity(2)
                .currentQuantity(2)
                .manufacturingDate("2021-06-03T14:10:14")
                .manufacturingTime("2021-06-03T14:24:54")
                .dueDate("2021-07-24")
                .build();

        BatchDto batchDto2 = BatchDto.builder()
                .productId(1L)
                .minimumTemperature(15F)
                .currentTemperature(18F)
                .initialQuantity(4)
                .currentQuantity(5)
                .manufacturingDate("2021-06-03T14:24:54")
                .manufacturingTime("2021-06-03T14:24:54")
                .dueDate("2021-05-12")
                .build();

        batchDtoList.add(batchDto1);
        batchDtoList.add(batchDto2);

        return batchDtoList;

    }
}
