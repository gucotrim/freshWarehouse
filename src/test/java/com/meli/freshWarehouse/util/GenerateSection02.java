package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.Section;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateSection02 {

    public static List<Section> validSectionList(){
        Set<Product> productSet = new HashSet<>();
        productSet.add(Product.builder()
                .id(1l)
                .name("Leite")
                .price(2.0)
                .build());

        Section newSection = Section.builder()
                .id(1L)
                .name("Section test 1")
                .availableSpace(23)
                .warehouse(GenerateWarehouse.validWarehouse1())
                .products(productSet)
                .build();

        List<Section> sectionSet = new ArrayList<>();
        sectionSet.add(newSection);

        return sectionSet;
    }
}
