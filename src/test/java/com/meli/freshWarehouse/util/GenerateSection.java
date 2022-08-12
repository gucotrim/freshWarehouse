package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.SectionDto;
import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.model.Warehouse;

import java.util.HashSet;
import java.util.Set;

public class GenerateSection {
    public static final SectionDto newSection1() {

        return SectionDto.builder()
                .name("Section test 1")
                .availableSpace(23)
                .idWarehouse(1L)
                .build();

    }

    public static final SectionDto newSection2() {

        return SectionDto.builder()
                .name("Section test 2")
                .availableSpace(25)
                .idWarehouse(2L)
                .build();

    }

    public static final Section validSection1() {
        //section
        Set<Product> productSet = new HashSet<>();

        productSet.add(Product.builder()
                .id(1l)
                .name("Leite")
                .price(2.0)
                .build());

        productSet.add(Product.builder()
                .id(1l)
                .name("Tody")
                .price(10.0)
                .build());

        return Section.builder()
                .id(1L)
                .name("Section test 1")
                .availableSpace(23)
                .products(productSet)
                .warehouse(GenerateWarehouse.validWarehouse1())
                .build();
    }

    public static final Section validSection2() {
        return Section.builder()
                .id(2L)
                .name("Section test 2")
                .availableSpace(25)
                .warehouse(Warehouse.builder()
                        .id(2L)
                        .address("Rua dois test")
                        .city("Belo Horizonte")
                        .state("Minas Gerais")
                        .country("Brasil")
                        .number(25)
                        .build())
                .build();
    }

    public static final Section updatedSection() {
        return Section.builder()
                .id(1L)
                .name("New section")
                .availableSpace(33)
                .warehouse(GenerateWarehouse.validWarehouse2())
                .build();
    }

    public static final Set<Section> validSectionList() {

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

        Set<Section> sectionSet = new HashSet<>();
        sectionSet.add(newSection);

        return sectionSet;
    }


}
