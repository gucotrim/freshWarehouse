package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.dto.SectionDto;
import com.meli.freshWarehouse.dto.WarehouseProductResponseDTO;
import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.service.IProductService;
import com.meli.freshWarehouse.service.ProductService;
import com.meli.freshWarehouse.util.GenerateProduct;
import com.meli.freshWarehouse.util.GenerateSection;
import com.meli.freshWarehouse.util.GenerateSection02;
import com.meli.freshWarehouse.util.GenerateWarehouseProductResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;


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
        Long productId = product.getSections().stream().mapToLong(s -> s.getId()).findFirst().getAsLong();

        ResponseEntity<WarehouseProductResponseDTO> returnedList = controller
                .getProductInAllBatches(product.getId(), productId,"");

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
        Long productId = product.getSections().stream().mapToLong(s -> s.getId()).findFirst().getAsLong();

        ResponseEntity<WarehouseProductResponseDTO> returnedList = controller
                .getProductInAllBatches(product.getId(), productId,"L");

        assertThat(returnedList.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(returnedList.getBody().getProductId()).isEqualTo(product.getId());

        Mockito.verify(productService, Mockito.atLeastOnce()).getProductInAllBatches(
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.anyString());
    }

    @Test
    public void saveNewProduct_WhenProductIsValid() {
        BDDMockito.when(productService.createProduct(ArgumentMatchers.any(ProductDTO.class)))
                .thenReturn(GenerateProduct.newProduct());

        ProductDTO productDto = GenerateProduct.validProductDto();

        ResponseEntity<Product> response = controller.createProduct(productDto);

        assertThat(response.getBody().getName()).isEqualTo(productDto.getName());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Mockito.verify(productService, Mockito.atLeastOnce()).createProduct(ArgumentMatchers.any(ProductDTO.class));
    }

    @Test
    public void getAllProduct(){
        BDDMockito.when(productService.getAll())
                .thenReturn(GenerateProduct.validProductList());

        ResponseEntity<List<Product>> response = controller.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().get(0).getId()).isPositive();
        assertThat(response.getBody().size()).isGreaterThan(0);

        Mockito.verify(productService, Mockito.atLeastOnce()).getAll();
    }

    @Test
    public void getProductById() {
        BDDMockito.when(productService.getProductById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateProduct.validProduct1());

        Product product = GenerateProduct.validProduct1();

        ResponseEntity<Product> response = controller.getProductById(product.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(product.getName());
        assertThat(response.getBody().getId()).isEqualTo(product.getId());

        Mockito.verify(productService, Mockito.atLeastOnce()).getProductById(ArgumentMatchers.anyLong());
    }

    @Test
    public void updateProduct(){
        BDDMockito.when(productService.update(ArgumentMatchers.anyLong(),
                ArgumentMatchers.any(ProductDTO.class)))
                .thenReturn(GenerateProduct.newProduct());

        ProductDTO product = GenerateProduct.validProductDto();
        final Long productID = 1L;

        ResponseEntity<Product> response = controller.update(productID, product);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(product.getName());
        assertThat(response.getBody().getId()).isEqualTo(productID);
        Mockito.verify(productService, Mockito.atLeastOnce())
                .update(ArgumentMatchers.anyLong(),
                        ArgumentMatchers.any(ProductDTO.class));
    }

    @Test
    public void delete(){
        BDDMockito.willDoNothing().given(productService).delete(anyLong());

        final Long productID = 1L;

        ResponseEntity<Void> response = controller.delete(productID);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isEqualTo(null);

        Mockito.verify(productService, Mockito.atLeastOnce())
                .delete(ArgumentMatchers.anyLong());
    }

}
