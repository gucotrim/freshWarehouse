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
        return Section.builder()
                .id(1L)
                .name("Section test 1")
                .availableSpace(23)
                .warehouse(GenerateWarehouse.validWarehouse1())
                .build();
    }

    public static final Section validSection2() {
        return Section.builder()
                .id(2L)
                .name("Section test 2")
                .availableSpace(25)
                .warehouse(GenerateWarehouse.validWarehouse2())
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

        Warehouse warehouse = new Warehouse();
        warehouse.setCity("Belo Horizonte");
        warehouse.setAddress("Rua b");
        warehouse.setNumber(33);
        warehouse.setCountry("Brasil");
        warehouse.setState("Minas Gerais");

        Set<Product> productSet = new HashSet<>();
        Product product = new Product();
        product.setId(1l);
        product.setName("Leite");
        product.setPrice(2.0);

        productSet.add(product);

        Section newSection = Section.builder()
                .id(1L)
                .name("Section Name")
                .availableSpace(33)
                .warehouse(warehouse)
                .products(productSet)
                .build();

        Set<Section> sectionSet = new HashSet<>();
        sectionSet.add(newSection);

        return sectionSet;
    }


}
