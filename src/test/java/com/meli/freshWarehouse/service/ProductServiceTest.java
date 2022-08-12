package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.dto.WarehouseProductResponseDTO;
import com.meli.freshWarehouse.exception.ItsNotBelongException;
import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.repository.ProductRepository;
import com.meli.freshWarehouse.util.GenerateProduct;
import com.meli.freshWarehouse.util.GenerateSection;
import com.meli.freshWarehouse.util.GenerateSeller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.*;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SectionService sectionService;

    @Mock
    private SellerService sellerService;


    @Test
    void createProduct_when_ProductIsValid() {
        BDDMockito.when(productRepository.save(ArgumentMatchers.any(Product.class)))
                .thenReturn(GenerateProduct.validProduct1());

        BDDMockito.when(sectionService.findAllById(ArgumentMatchers.anySet()))
                .thenReturn(GenerateSection.validSectionList());

        ProductDTO productDTO = GenerateProduct.newProductDto1();

        Product savedProductResponse = productService.createProduct(productDTO);

        assertThat(savedProductResponse).isNotNull();
        assertThat(savedProductResponse.getId()).isPositive();
        assertThat(savedProductResponse.getName()).isEqualTo(GenerateProduct.validProduct1().getName());

    }

    @Test
    void getAllProducts() {

        List<Product> productList = List.of(GenerateProduct.validProduct1(), GenerateProduct.validProduct2());
        BDDMockito.when(productRepository.findAll()).thenReturn(productList);
        List<Product> productListResponse = productService.getAll();
        assertThat(productListResponse).isNotNull();
        assertThat(productListResponse.size()).isEqualTo(2);
        verify(productRepository, Mockito.times(1)).findAll();

    }

    @Test
    void getProductById_when_IdIsValid() {
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateProduct.validProduct1()));

        Product productResponse = productService.getProductById(GenerateProduct.validProduct1().getId());

        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getId()).isEqualTo(1L);

    }

    @Test
    void return_NotFoundException_when_IdIsNotValid() {
        String expectedMessage = "Can't find product with the informed id";

        Exception exception = assertThrows(NotFoundException.class,
                () -> productService.getProductById(3L).getId());

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }


    @Test
    void updateProduct_when_IsProductExists() {

        BDDMockito.when(productRepository.save(ArgumentMatchers.any(Product.class)))
                .thenReturn(GenerateProduct.responseProduct());

        BDDMockito.when(sectionService.findAllById(ArgumentMatchers.anySet()))
                .thenReturn(GenerateSection.validSectionList());

        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateProduct.validProduct2()));

        Product product = GenerateProduct.validProduct1();

        ProductDTO productDTOUpdated = GenerateProduct.validProductDto();
        productDTOUpdated.setName("New Product");

        Product productResponse = productService.update(product.getId(), productDTOUpdated);

        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getId()).isEqualTo(product.getId());

    }

    @Test
    void deleteProductById_When_ProductExist() {

        BDDMockito.doNothing().when(productRepository).deleteById(ArgumentMatchers.anyLong());
        BDDMockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateProduct.validProduct1()));

        Product product = GenerateProduct.validProduct1();
        productService.delete(product.getId());

        assertThatCode(
                () -> productService.delete(product.getId())
        );

        verify(productRepository, Mockito.atLeastOnce()).deleteById(product.getId());

    }

    @Test
    void returnNotFoundException_When_ProductById_NotExist() {

        BDDMockito.doNothing().when(productRepository)
                .deleteById(ArgumentMatchers.anyLong());

        String expectedMessage = "Can't find product with the informed id";

        Exception exception = assertThrows(NotFoundException.class,
                () -> productService.delete(4L)
        );

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    @ParameterizedTest(name = "[{index}] ==> Input value: ''{0}'' - Expected result: ''{1}''")
    @CsvSource({"true, true", "false, false"})
    void productExists_when_ProductIdIsValid(boolean inputValue, boolean expectedResult) {

        BDDMockito.when(productRepository.existsById(ArgumentMatchers.anyLong()))
                .thenReturn(inputValue);

        Product product = GenerateProduct.validProduct1();
        boolean productResponse = productService.productExists(product.getId());

        assertThat(productResponse).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "[{index}] ==> Input value: ''{0}'' - Expected result: ''{1}''")
    @CsvSource({"1, true", "2, false", "3, false"})
    void isFromSection(String inputValue, boolean expectedResult) {

        Long inputSectionId = Long.parseLong(inputValue);
        Product product = GenerateProduct.validProduct1();
        boolean productResponse = productService.isFromSection(inputSectionId, product);

        assertThat(productResponse).isEqualTo(expectedResult);

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