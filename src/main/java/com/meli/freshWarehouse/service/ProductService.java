package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.exception.InvalidProductParam;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository repository;


    @Override
    public Product createProduct(ProductDTO product) {
        return repository.save(product.toModel());
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Can't find product with the informed id"));

    }

    @Override
    public Product update(Product product) {
        if (product == null || product.getId() == 0) {
            throw new InvalidProductParam("Product can't be null and can't have id defined");
        }
        Optional<Product> productFound = repository.findById(product.getId());
        if (productFound.isEmpty()) {
            throw new NotFoundException("Can't find product with the informed id");
        }
        return repository.save(product);
    }

    @Override
    public void delete(Long id) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("Can't find product with the informed id");
        }
        repository.deleteById(id);

    }

    @Override
    public boolean productExists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean isFromSection(Long sectionId, Product product) {
        return product.getSection().getId() == sectionId;
    }
}
