package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.model.Product;

public class GenerateProduct {

    public final static Product newProduct1() {

        return Product.builder()
                .name("Toddy")
                .price(10.0)
                .seller(GenerateSeller.validSeller1())
                .section(GenerateSection.validSection1())
                .listBatch(GenerateBachStock.validBatchResponse())
                .build();

    }

    public final static Product newProduct2() {

        return Product.builder()
                .name("Milk")
                .price(8.0)
                .seller(GenerateSeller.validSeller2())
                .section(GenerateSection.validSection2())
                .listBatch(GenerateBachStock.validBatchResponse())
                .build();
    }

    public final static Product validProduct1(){

        return Product.builder()
                .id(1L)
                .name("Toddy")
                .price(10.0)
                .seller(GenerateSeller.validSeller1())
                .section(GenerateSection.validSection1())
                .listBatch(GenerateBachStock.validBatchResponse())
                .build();
    }

    public final static Product validProduct2(){

        return Product.builder()
                .id(2L)
                .name("Milk")
                .price(8.0)
                .seller(GenerateSeller.validSeller2())
                .section(GenerateSection.validSection2())
                .listBatch(GenerateBachStock.validBatchResponse())
                .build();
    }


}
