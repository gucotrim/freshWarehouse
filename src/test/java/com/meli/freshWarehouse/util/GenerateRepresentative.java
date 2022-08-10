package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.RepresentativeDTO;
import com.meli.freshWarehouse.model.Representative;

public class GenerateRepresentative {


    public static Representative newRepresentative1() {
        return Representative.builder()
                .name("Name Representative Test")
                .warehouse(GenerateWarehouse.validWarehouse1())
                .listOrder(GenerateOrder.validOrderLit())
                .build();
    }


    public static RepresentativeDTO newRepresentativeDto1() {
        return RepresentativeDTO.builder()
                .name("Name Representative Test")
                .warehouseId(1l)
                .build();
    }


    public static Representative validRepresentative1() {

        return Representative.builder()
                .id(1L)
                .name("Name Representative Test")
                .warehouse(GenerateWarehouse.validWarehouse1())
                .listOrder(GenerateOrder.validOrderLit())
                .build();
    }

    public static Representative validRepresentative2() {
        return Representative.builder()
                .id(2L)
                .name("Name Representative2 Test")
                .build();
    }

}
