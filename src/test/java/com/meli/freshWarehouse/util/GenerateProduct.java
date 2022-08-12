package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.model.Product;

import java.util.HashSet;
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

    public final static Product validProduct2(){

        return Product.builder()
                .id(2L)
                .name("Milk")
                .price(8.0)
                .seller(GenerateSeller.validSeller2())
                .sections(GenerateSection.validSectionList())
                .build();
    }

    public final static Product validFreshProduct(){

        return Product.builder()
                .id(3L)
                .name("Milk")
                .price(10.0)
                .seller(GenerateSeller.validSeller2())
                .sections(GenerateSection.validFreshSectionList())
                .build();
    }


}
