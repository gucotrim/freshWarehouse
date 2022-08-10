package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.SectionDto;
import com.meli.freshWarehouse.model.Section;

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


}
