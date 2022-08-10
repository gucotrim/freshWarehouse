package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.model.Order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GenerateOrder {

    public static final Order newOrder1(){
        return Order.builder()
                .orderDate(LocalDate.parse(String.valueOf(LocalDate.parse("2022-08-09",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .representative(GenerateRepresentative.validRepresentative1())
                .section(GenerateSection.validSection1())
                .listBatch(GenerateBachStock.validBatchResponse())
                .build();

    }

    public static final Order newOrder2(){
        return Order.builder()
                .orderDate(LocalDate.parse(String.valueOf(LocalDate.parse("2022-18-09",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .representative(GenerateRepresentative.validRepresentative2())
                .section(GenerateSection.validSection2())
                .listBatch(GenerateBachStock.validBatchResponse())
                .build();
    }

    public static final Order validOrder1() {
        return Order.builder()
                .id(1L)
                .orderDate(LocalDate.parse(String.valueOf(LocalDate.parse("2022-08-09",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .representative(GenerateRepresentative.validRepresentative1())
                .section(GenerateSection.validSection1())
                .listBatch(GenerateBachStock.validBatchResponse())
                .build();
    }

    public static final Order validOrder2() {
        return Order.builder()
                .id(2L)
                .orderDate(LocalDate.parse(String.valueOf(LocalDate.parse("2022-18-09",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")))))
                .representative(GenerateRepresentative.validRepresentative2())
                .section(GenerateSection.validSection2())
                .listBatch(GenerateBachStock.validBatchResponse())
                .build();
    }
}
