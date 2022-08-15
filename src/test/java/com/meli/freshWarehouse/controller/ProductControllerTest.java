package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.WarehouseProductResponseDTO;
import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.service.IProductService;
import com.meli.freshWarehouse.service.ProductService;
import com.meli.freshWarehouse.util.GenerateProduct;
import com.meli.freshWarehouse.util.GenerateSection;
import com.meli.freshWarehouse.util.GenerateWarehouseProductResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController controller;

    @Mock
    ProductService productService;

    @Test
    public void getProductInAllBatches_WithoutFilter() {
        WarehouseProductResponseDTO warehouseProductResponseDTO = GenerateWarehouseProductResponseDTO.validWarehouseProductresponseDTO();
        BDDMockito.when(productService.getProductInAllBatches(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Long.class), ArgumentMatchers.any()))
                .thenReturn(warehouseProductResponseDTO);

        Product product = GenerateProduct.validProduct1();
        Long sectionId = product.getSections().stream().mapToLong(s -> s.getId()).findFirst().getAsLong();

        ResponseEntity<WarehouseProductResponseDTO> returnedList = controller
                .getProductInAllBatches(product.getId(), sectionId,"");

        assertThat(returnedList.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(returnedList.getBody().getProductId()).isEqualTo(product.getId());

        Mockito.verify(productService, Mockito.atLeastOnce()).getProductInAllBatches(
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.anyString());
    }

    @Test
    public void getProductInAllBatches_WithFilter() {
        WarehouseProductResponseDTO warehouseProductResponseDTO = GenerateWarehouseProductResponseDTO.validWarehouseProductresponseDTO();
        BDDMockito.when(productService.getProductInAllBatches(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Long.class), ArgumentMatchers.any()))
                .thenReturn(warehouseProductResponseDTO);

        Product product = GenerateProduct.validProduct1();
        Long sectionId = product.getSections().stream().mapToLong(s -> s.getId()).findFirst().getAsLong();

        ResponseEntity<WarehouseProductResponseDTO> returnedList = controller
                .getProductInAllBatches(product.getId(), sectionId,"L");

        assertThat(returnedList.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(returnedList.getBody().getProductId()).isEqualTo(product.getId());

        Mockito.verify(productService, Mockito.atLeastOnce()).getProductInAllBatches(
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.anyString());
    }

}
