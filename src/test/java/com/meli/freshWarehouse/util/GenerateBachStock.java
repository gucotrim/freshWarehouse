package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.BatchDto;
import com.meli.freshWarehouse.dto.BatchResponseDto;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.model.Warehouse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public static List<Batch> validBatchResponseDtoList() {

        List<Batch> batchDtoList = new ArrayList<>();
        Batch batchResponse1 = Batch.builder()
                .id(1L)
                .currentTemperature(22.0F)
                .minimumTemperature(25.0F)
                .initialQuantity(2)
                .currentQuantity(2)
                .manufacturingDate(LocalDate.parse("2022-05-24"))
                .manufacturingTime(LocalDateTime.parse("2022-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2022-07-22"))
                .order(GenerateOrder.validOrder1())
                .product(GenerateProduct.validProduct1())
                .section(Section.builder()
                        .id(1L)
                        .name("Section test 1")
                        .availableSpace(23)
                        .warehouse(Warehouse.builder()
                                .id(1L)
                                .address("Rua um test")
                                .city("São Paulo")
                                .state("São Paulo")
                                .country("Brasil")
                                .number(23)
                                .build())
                        .build())
                .build();

        batchDtoList.add(batchResponse1);

        return batchDtoList;

    }

    public static Set<BatchDto> validBatchResponseDto2() {

        Set<BatchDto> batchDtoList = new HashSet<>();

        BatchDto batchDto1 = BatchDto.builder()
                .productId(1L)
                .minimumTemperature(25F)
                .currentTemperature(14F)
                .initialQuantity(25)
                .currentQuantity(25)
                .manufacturingDate("2021-06-03")
                .manufacturingTime("2021-06-03T14:24:54")
                .dueDate("2021-07-27")
                .build();

        BatchDto batchDto2 = BatchDto.builder()
                .productId(1L)
                .minimumTemperature(15F)
                .currentTemperature(18F)
                .initialQuantity(4)
                .currentQuantity(5)
                .manufacturingDate("2021-06-03")
                .manufacturingTime("2021-06-03T14:24:54")
                .dueDate("2021-05-12")
                .build();

        batchDtoList.add(batchDto1);
        batchDtoList.add(batchDto2);

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
                .manufacturingDate("2021-06-03")
                .manufacturingTime("2021-06-03T14:24:54")
                .dueDate("2021-07-27")
                .build();

        BatchDto batchDto2 = BatchDto.builder()
                .productId(1L)
                .minimumTemperature(15F)
                .currentTemperature(18F)
                .initialQuantity(4)
                .currentQuantity(5)
                .manufacturingDate("2021-06-03")
                .manufacturingTime("2021-06-03T14:24:54")
                .dueDate("2021-05-12")
                .build();

        batchDtoList.add(batchDto1);
        batchDtoList.add(batchDto2);

        return batchDtoList;

    }


    public static Set<Batch> validBatchResponse() {
        Set<Batch> batchSet = new HashSet<>();
        batchSet.add(Batch.builder()
                .id(1L)
                .minimumTemperature(25F)
                .currentTemperature(14F)
                .initialQuantity(2)
                .currentQuantity(2)
                .manufacturingDate(LocalDate.parse("2021-06-03T14:10:14"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2021-07-24"))
                .build()
        );

        batchSet.add(Batch.builder()
                .id(1L)
                .minimumTemperature(15F)
                .currentTemperature(18F)
                .initialQuantity(4)
                .currentQuantity(5)
                .manufacturingDate(LocalDate.parse("2021-06-03T14:24:54"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2021-05-12"))
                .build()
        );

        return batchSet;
    }

}
