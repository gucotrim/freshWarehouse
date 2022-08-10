package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.model.Seller;
import com.meli.freshWarehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ISectionService sectionService;
    private final ISellerService sellerService;

    public ProductService(ProductRepository productRepository, ISectionService sectionService, ISellerService sellerService) {
        this.productRepository = productRepository;
        this.sectionService = sectionService;
        this.sellerService = sellerService;
    }


    @Override
    public Product createProduct(ProductDTO productDto) {
        Section section = sectionService.findById(productDto.getSellerId());
        Seller seller = sellerService.getSellerById(productDto.getSellerId());
        return productRepository.save(Product.builder()
                .name(productDto.getName())
                .section(section)
                .seller(seller)
                .price(productDto.getPrice())
                .build());
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Can't find product with the informed id"));

    }

    @Override
    public Product update(Long id, ProductDTO productDto) {
        Product product = this.getProductById(id);
        Section section = sectionService.findById(productDto.getSellerId());
        Seller seller = sellerService.getSellerById(productDto.getSellerId());

        product.setName(productDto.getName());
        product.setSection(section);
        product.setSeller(seller);
        product.setPrice(product.getPrice());
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("Can't find product with the informed id");
        }
        productRepository.deleteById(id);

    }

    @Override
    public boolean productExists(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public boolean isFromSection(Long sectionId, Product product) {
        return product.getSection().getId() == sectionId;
    }
}
