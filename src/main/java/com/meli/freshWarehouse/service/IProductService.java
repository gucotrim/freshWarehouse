package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.model.Product;

import javax.validation.Valid;
import java.util.List;

public interface IProductService {
    Product createProduct(@Valid ProductDTO product);
    List<Product> getAll();
    Product getProductById(Long id);
    Product update(Product product);
    void delete(Long id);
    boolean isFromSection(Long sectionId, Product product);
    boolean productExists(Long id);
}
