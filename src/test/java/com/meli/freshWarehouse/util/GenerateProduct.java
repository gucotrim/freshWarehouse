package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.BatchResponseDto;
import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GenerateProduct {

    public final static Product newProduct1() {

        return Product.builder()
                .name("Toddy")
                .price(10.0)
                .seller(GenerateSeller.validSeller1())
                .sections(GenerateSection.validSectionList())
                .build();

    }

    public final static ProductDTO newProductDto1() {

        Set<Long> longSet = new HashSet<>();
        longSet.add(1l);

        return ProductDTO.builder()
                .name("Toddy")
                .price(10.0)
                .sectionsId(longSet)
                .sellerId(1L)
                .build();
    }

    public final static Product newProduct2() {

        return Product.builder()
                .name("Milk")
                .price(8.0)
                .seller(GenerateSeller.validSeller2())
                .sections(GenerateSection.validSectionList())
                .build();
    }

    public final static Product validProduct1(){

        return Product.builder()
                .id(1L)
                .name("Toddy")
                .price(10.0)
                .seller(GenerateSeller.validSeller1())
                .sections(GenerateSection.validSectionList())
                .build();
    }

    public final static Product validProductToException1(){

        return Product.builder()
                .id(1L)
                .name("Toddy")
                .price(10.0)
                .seller(GenerateSeller.validSeller1())
                .sections(GenerateSection.validSectionListToException())
                .build();
    }

    public final static Product validProduct1WithListBatch(){

        Set<Batch> batchList = new HashSet<>();

        Batch batchResponse1 = Batch.builder()
                .id(1L)
                .minimumTemperature(25F)
                .currentTemperature(14F)
                .initialQuantity(2)
                .currentQuantity(2)
                .manufacturingDate(LocalDate.parse("2021-06-03"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2022-11-24"))
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
                .dueDate(LocalDate.parse("2022-11-12"))
                .order(GenerateOrder.validOrder1())
                .section(GenerateSection.validSection1())
                .product(GenerateProduct.validProduct1())
                .build();

        batchList.add(batchResponse1);
        batchList.add(batchResponse2);

        return Product.builder()
                .id(1L)
                .name("Toddy")
                .price(10.0)
                .seller(GenerateSeller.validSeller1())
                .sections(GenerateSection.validSectionList())
                .listBatch(batchList)
                .build();
    }

    public final static Product validProduct1WithListBatchWithDueDateLessThen3weeks(){

        Set<Batch> batchList = new HashSet<>();

        Batch batchResponse1 = Batch.builder()
                .id(1L)
                .minimumTemperature(25F)
                .currentTemperature(14F)
                .initialQuantity(2)
                .currentQuantity(2)
                .manufacturingDate(LocalDate.parse("2021-06-03"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2021-11-24"))
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
                .dueDate(LocalDate.parse("2021-11-12"))
                .order(GenerateOrder.validOrder1())
                .section(GenerateSection.validSection1())
                .product(GenerateProduct.validProduct1())
                .build();

        batchList.add(batchResponse1);
        batchList.add(batchResponse2);

        return Product.builder()
                .id(1L)
                .name("Toddy")
                .price(10.0)
                .seller(GenerateSeller.validSeller1())
                .sections(GenerateSection.validSectionList())
                .listBatch(batchList)
                .build();
    }

    public final static Product validProductToException2(){

        Set<Batch> batchList = new HashSet<>();

        Batch batchResponse1 = Batch.builder()
                .id(1L)
                .minimumTemperature(25F)
                .currentTemperature(14F)
                .initialQuantity(2)
                .currentQuantity(2)
                .manufacturingDate(LocalDate.parse("2021-06-03"))
                .manufacturingTime(LocalDateTime.parse("2021-06-03T14:24:54"))
                .dueDate(LocalDate.parse("2021-11-24"))
                .order(GenerateOrder.validOrder1())
                .section(GenerateSection.validSectionToExpection1())
                .product(GenerateProduct.validProduct1())
                .build();

        batchList.add(batchResponse1);

        return Product.builder()
                .id(1L)
                .name("Toddy")
                .price(10.0)
                .seller(GenerateSeller.validSeller1())
                .sections(GenerateSection.validSectionList())
                .listBatch(batchList)
                .build();
    }

    public final static Product validProduct2(){

        return Product.builder()
                .id(2L)
                .name("Milk")
                .price(8.0)
                .seller(GenerateSeller.validSeller2())
                .sections(GenerateSection.validSectionList())
                .build();
    }


}
