package com.meli.freshWarehouse.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SectionDto {

    private String name;
    private int availableSpace;

}
