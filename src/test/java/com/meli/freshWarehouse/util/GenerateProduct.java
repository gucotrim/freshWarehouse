package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.dto.ProductStockResponseDTO;
import com.meli.freshWarehouse.dto.WarehouseForProductStockResponseDTO;
import com.meli.freshWarehouse.model.Product;

import java.util.Arrays;
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

    public final static ProductStockResponseDTO productStockResponseDTOAvailableInStock() {
        return ProductStockResponseDTO.builder()
                .productId(1L)
                .warehouses(Arrays.asList(
                        WarehouseForProductStockResponseDTO.builder()
                                .warehouseId(1L)
                                .totalQuantity(4L)
                                .build(),
                        WarehouseForProductStockResponseDTO.builder()
                                .warehouseId(2L)
                                .totalQuantity(4L)
                                .build()
                ))
                .build();
    }

    public final static ProductStockResponseDTO productStockResponseDTONotAvailableInStock() {
        return ProductStockResponseDTO.builder()
                .productId(1L)
                .warehouses(Arrays.asList(
                        WarehouseForProductStockResponseDTO.builder()
                                .warehouseId(1L)
                                .totalQuantity(0L)
                                .build(),
                        WarehouseForProductStockResponseDTO.builder()
                                .warehouseId(2L)
                                .totalQuantity(0L)
                                .build()
                ))
                .build();
    }
}
