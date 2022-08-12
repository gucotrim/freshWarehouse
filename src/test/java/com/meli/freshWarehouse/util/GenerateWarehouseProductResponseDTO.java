package com.meli.freshWarehouse.util;


import com.meli.freshWarehouse.dto.WarehouseProductResponseDTO;

public class GenerateWarehouseProductResponseDTO {

    public static WarehouseProductResponseDTO validWarehouseProductresponseDTO(){
        return WarehouseProductResponseDTO.builder()
                .productId(GenerateProduct.validProduct1().getId())
                .batchStockList(GenerateBatchListProductResponseDto.validBatchListProductResponseDto())
                .section(GenerateSection.validSectionDto1())
                .build();
    }
}
