package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.*;
import com.meli.freshWarehouse.exception.EmptySectionListException;
import com.meli.freshWarehouse.exception.InvalidSectionNameException;
import com.meli.freshWarehouse.exception.ItsNotBelongException;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.model.Seller;
import com.meli.freshWarehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
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
    public List<ProductPurchaseDto> getProductByCategory(String category) {
        List<ProductPurchaseDto> productPurchaseDtoList;
        switch (category) {
            case "FS":
                productPurchaseDtoList = this.getAll().stream().filter(p -> {
                    Optional<Section> section = p.getSections().stream().filter(s -> s.getName().equals("Fresh")).findFirst();
                    if(section.isPresent()) {
                        return true;
                    }
                    return false;
                }).map(p -> ProductPurchaseDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .price(p.getPrice())
                        .build()).collect(Collectors.toList());
                break;
            case "RF":
                productPurchaseDtoList = this.getAll().stream().filter(p -> {
                    Optional<Section> section = p.getSections().stream().filter(s -> s.getName().equals("Refrigerated")).findFirst();
                    if(section.isPresent()) {
                        return true;
                    }
                    return false;
                }).map(p -> ProductPurchaseDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .price(p.getPrice())
                        .build()).collect(Collectors.toList());
                break;
            case "FF":
                productPurchaseDtoList = this.getAll().stream().filter(p -> {
                    Optional<Section> section = p.getSections().stream().filter(s -> s.getName().equals("Frozen")).findFirst();
                    if(section.isPresent()) {
                        return true;
                    }
                    return false;
                }).map(p -> ProductPurchaseDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .price(p.getPrice())
                        .build()).collect(Collectors.toList());
                break;
            default:
                throw new InvalidSectionNameException("Please, enter one of the options: FS, RF or FF");
        }
        if(productPurchaseDtoList.isEmpty()) {
            throw new EmptySectionListException("Products for this category not found!");
        }
        return productPurchaseDtoList;
    }

    @Override
    @Transactional
    public Product update(Long id, ProductDTO productDto) {
        Product product = this.getProductById(id);
        Set<Section> sections = sectionService.findAllById(productDto.getSectionsId());
        sections.forEach(s -> {s.getProducts().add(product);});
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
    public WarehouseProductResponseDTO getProductInAllBatches(Long id, Long idSection, String filter) {
        Product product = this.getProductById(id);
        Section section = sectionService.findById(idSection);

        validateSectionWithProduct(product,section);
        validateSectionWithBatch(product.getListBatch(),section);
        validateDueDateProduct(product);
        product.setListBatch(orderListBatch(product.getListBatch(),filter));

        return WarehouseProductResponseDTO.builder()
                .productId(id)
                .section(SectionDto.builder()
                        .name(section.getName())
                        .availableSpace(section.getAvailableSpace())
                        .idWarehouse(section.getWarehouse().getId())
                        .build())
                .batchStockList(product.getListBatch().stream().map(b ->
                        BatchListProductResponseDto.builder()
                                .batchNumber(b.getId())
                                .currentQuantity(b.getCurrentQuantity())
                                .dueDate(b.getDueDate())
                                .build()
                ).collect(Collectors.toList()))
                .build();
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

    private void validateSectionWithProduct(Product product, Section section) {
        if (!isFromSection(section.getId(), product)){ //negado
            throw new ItsNotBelongException("Product doesn't belong to the section id: " + section.getId());
        }
    }

    private void validateSectionWithBatch(Set<Batch> listBatch, Section section) {
        listBatch.stream().forEach(batch -> {
            if(!batch.getSection().getId().equals(section.getId())) {
                throw new ItsNotBelongException("Batch doesn't belong to the section id: " + section.getId());
            }
        });
    }

    private void validateDueDateProduct(Product product) {
        product.getListBatch().removeIf(batch -> batch.getDueDate().isBefore(LocalDate.now().plusWeeks(3)));
    }

    private Set<Batch> orderListBatch(Set<Batch> listBatch, String filter) {
        if (filter == null || filter == "") {
            return listBatch;
        }
        switch (filter) {
            case "L":
                listBatch = listBatch.stream().sorted(Comparator.comparing(
                        Batch::getId
                ).reversed()).collect(Collectors.toCollection(LinkedHashSet::new));
                break;
            case "Q":
                listBatch = listBatch.stream().sorted(Comparator.comparing(
                        Batch::getCurrentQuantity
                ).reversed()).collect(Collectors.toCollection(LinkedHashSet::new));
                break;
            case "V":
                listBatch = listBatch.stream().sorted(Comparator.comparing(
                        Batch::getDueDate
                ).reversed()).collect(Collectors.toCollection(LinkedHashSet::new));
                break;
        }
        return listBatch;
    }
}
