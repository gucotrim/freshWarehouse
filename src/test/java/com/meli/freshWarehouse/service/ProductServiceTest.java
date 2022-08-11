package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.Seller;
import com.meli.freshWarehouse.repository.ProductRepository;
import com.meli.freshWarehouse.util.GenerateProduct;
import com.meli.freshWarehouse.util.GenerateSection;
import com.meli.freshWarehouse.util.GenerateSeller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}