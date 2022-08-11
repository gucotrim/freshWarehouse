package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.ProductDTO;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.model.Seller;
import com.meli.freshWarehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    @Transactional
    public Product createProduct(ProductDTO productDto) {
        Set<Section> sections = sectionService.findAllById(productDto.getSectionsId());
        Seller seller = sellerService.getSellerById(productDto.getSellerId());
        Product product = Product.builder()
                .name(productDto.getName())
                .sections(sections)
                .seller(seller)
                .price(productDto.getPrice())
                .build();
        sections.forEach(s -> {s.getProducts().add(product);});
        return productRepository.save(product);
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
        Set<Section> sections = sectionService.findAllById(productDto.getSectionsId());
        Seller seller = sellerService.getSellerById(productDto.getSellerId());

        product.setName(productDto.getName());
        product.setSections(sections);
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
        for (Section section : product.getSections()) {
            if(section.getId().equals(sectionId)) {
                return true;
            }
        }
        return false;
    }
}
