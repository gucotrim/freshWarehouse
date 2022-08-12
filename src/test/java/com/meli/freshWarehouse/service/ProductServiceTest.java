package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.dto.WarehouseProductResponseDTO;
import com.meli.freshWarehouse.exception.ItsNotBelongException;
import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.Seller;
import com.meli.freshWarehouse.repository.ProductRepository;
import com.meli.freshWarehouse.util.GenerateProduct;
import com.meli.freshWarehouse.util.GenerateSection;
import com.meli.freshWarehouse.util.GenerateSeller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SectionService sectionService;

    @Mock
    private SellerService sellerService;

//    @BeforeEach
//    public void setup() {
//        BDDMockito.when(productRepository.save(ArgumentMatchers.any(Product.class)))
//                .thenReturn(GenerateProduct.validProduct1());
//    }


//    @Test
//    void createProduct_when_ProductIsValid() {
//
//        BDDMockito.when(sectionService.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(GenerateSection.validSection1());
//
//        BDDMockito.when(sellerService.getSellerById(ArgumentMatchers.anyLong()))
//                .thenReturn(GenerateSeller.validSeller1());
//
//        ProductDTO productDTO = GenerateProduct.newProductDto1();
//
//        Product savedProductResponse = productService.createProduct(productDTO);
//
//        assertThat(savedProductResponse).isNotNull();
//
//
//    }

    @Test
    void getAll() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void productExists() {
    }

    @Test
    void isFromSection() {
    }

    @Test
    void getProductInAllBatches_ReturnWarehouseProductResponseDto_whenProductExits_andListBatchIsPositive(){
        BDDMockito.when(sectionService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateSection.validSection1());
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateProduct.validProduct1WithListBatch()));

        Product product = GenerateProduct.validProduct1();
        Long sectionId = product.getSections().stream().mapToLong(s -> s.getId()).findFirst().getAsLong();

        WarehouseProductResponseDTO response = productService.getProductInAllBatches(product.getId(),sectionId, "");

        assertThat(response.getProductId()).isEqualTo(product.getId());
        assertThat(response.getBatchStockList().size()).isPositive();
        assertThat(response).isInstanceOf(WarehouseProductResponseDTO.class);

        Mockito.verify(sectionService, Mockito.atLeastOnce()).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void getProductInAllBatches_ReturnWarehouseProductResponseDto_whenProductExits_andListBatchIsZero(){
        BDDMockito.when(sectionService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateSection.validSection1());
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateProduct.validProduct1WithListBatchWithDueDateLessThen3weeks()));

        Product product = GenerateProduct.validProduct1();
        Long sectionId = product.getSections().stream().mapToLong(s -> s.getId()).findFirst().getAsLong();

        WarehouseProductResponseDTO response = productService.getProductInAllBatches(product.getId(),sectionId, "");

        assertThat(response.getProductId()).isEqualTo(product.getId());
        assertThat(response.getBatchStockList().size()).isEqualTo(0);
        assertThat(response).isInstanceOf(WarehouseProductResponseDTO.class);

        Mockito.verify(sectionService, Mockito.atLeastOnce()).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void getProductInAllBatches_ReturnException_whenProductDontBelongToTheSection(){
        BDDMockito.when(sectionService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateSection.validSectionToExpection1());
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateProduct.validProduct1WithListBatchWithDueDateLessThen3weeks()));

        Product product = GenerateProduct.validProduct1();
        Long sectionId = 3L;

        ItsNotBelongException exception = Assertions.assertThrows(ItsNotBelongException.class, () -> {
            productService.getProductInAllBatches(product.getId(),sectionId, "");
        });

        assertThat(exception.getMessage()).
                isEqualTo("Product doesn't belong to the section id: " + sectionId);

        Mockito.verify(sectionService, Mockito.atLeastOnce()).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void getProductInAllBatches_ReturnException_whenBatchDontBelongToTheSection(){
        BDDMockito.when(sectionService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateSection.validSection1());
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateProduct.validProductToException2()));

        Product product = GenerateProduct.validProductToException2();
        Long sectionId = 1l;

        ItsNotBelongException exception = Assertions.assertThrows(ItsNotBelongException.class, () -> {
            productService.getProductInAllBatches(product.getId(),sectionId, "");
        });

        assertThat(exception.getMessage()).
                isEqualTo("Batch doesn't belong to the section id: " + sectionId);

        Mockito.verify(sectionService, Mockito.atLeastOnce()).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void getProductInAllBatches_ReturnWarehouseProductResponseDto_whenProductExits_WithFilter(){
        BDDMockito.when(sectionService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateSection.validSection1());
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateProduct.validProduct1WithListBatch()));

        Product product = GenerateProduct.validProduct1();
        Long sectionId = product.getSections().stream().mapToLong(s -> s.getId()).findFirst().getAsLong();

        WarehouseProductResponseDTO response = productService.getProductInAllBatches(product.getId(),sectionId, "L");

        assertThat(response.getProductId()).isEqualTo(product.getId());
        assertThat(response.getBatchStockList().size()).isPositive();
        assertThat(response).isInstanceOf(WarehouseProductResponseDTO.class);

        Mockito.verify(sectionService, Mockito.atLeastOnce()).findById(ArgumentMatchers.anyLong());
    }
}