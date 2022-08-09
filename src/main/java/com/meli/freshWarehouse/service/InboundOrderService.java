package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.*;
import com.meli.freshWarehouse.exception.ExceededStock;
import com.meli.freshWarehouse.exception.ItsNotBelongException;
import com.meli.freshWarehouse.model.*;
import com.meli.freshWarehouse.repository.BatchRepo;
import com.meli.freshWarehouse.repository.OrderRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class InboundOrderService implements IInboundOrderService {

    private final BatchRepo batchRepo;
    private final OrderRepo orderRepo;
    private final RepresentativeService representativeService;
    private final SectionService sectionService;
    private final ProductService productService;

    public InboundOrderService(BatchRepo batchRepo, OrderRepo orderRepo, RepresentativeService representativeService, SectionService sectionService, ProductService productService) {
        this.batchRepo = batchRepo;
        this.orderRepo = orderRepo;
        this.representativeService = representativeService;
        this.sectionService = sectionService;
        this.productService = productService;
    }

    @Override
    public InboundOrderResponseDto save(InboundOrderDto inboundOrderDto) {

        Representative representative = representativeService.findById(inboundOrderDto.getRepresentativeId());

        //Validate section
        Section section = sectionService.getById(inboundOrderDto.getSectionId());

        orderIsValid(representative, section);

        Integer quantityStock = validateAvailableSpace(section, inboundOrderDto);

        Order order = orderRepo.save(Order.builder()
                .orderDate(inboundOrderDto.getOrderDate())
                .representative(representative)
                .section(section)
                .build());

        List<Batch> batchList = batchRepo.saveAll(
                inboundOrderDto.getBatchStockList().stream().map(b -> Batch.builder()
                        .order(order)
                        .section(order.getSection())
                        .product(validateProduct(productService.getProductById(b.getProductId()), section))
                        .currentQuantity(b.getCurrentQuantity())
                        .currentTemperature(b.getCurrentTemperature())
                        .initialQuantity(b.getInitialQuantity())
                        .manufacturingDate(b.getManufacturingDate())
                        .minimumTemperature(b.getMinimumTemperature())
                        .dueDate(b.getDueDate())
                        .manufacturingTime(b.getManufacturingTime())
                        .build()).collect(Collectors.toList())
        );

        section.setAvailableSpace(quantityStock);

        sectionService.updateSection(section.getId(), SectionDto.builder()
                .name(section.getName())
                .availableSpace(section.getAvailableSpace())
                .build());


        return InboundOrderResponseDto.builder()
                .order(OrderResponseDto.builder()
                        .id(order.getId())
                        .orderDate(order.getOrderDate())
                        .build())
                .batchStockList(batchList.stream().map(b ->
                        BatchResponseDto.builder()
                                .id(b.getId())
                                .productId(b.getProduct().getId())
                                .currentTemperature(b.getCurrentTemperature())
                                .minimumTemperature(b.getMinimumTemperature())
                                .initialQuantity(b.getInitialQuantity())
                                .currentQuantity(b.getCurrentQuantity())
                                .manufacturingDate(b.getManufacturingDate())
                                .manufacturingTime(b.getManufacturingTime())
                                .dueDate(b.getDueDate())
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }

    private void orderIsValid(Representative representative, Section section) {
        if (representative.getWarehouse().getId().equals(section.getWarehouse().getId())) {
            log.info("Representative is valid.");
        } else {
            throw new ItsNotBelongException("Representative doesn't belong to the warehouse");
        }
    }

    private Product validateProduct(Product product, Section section) {
        if (product.getSection().getId().equals(section.getId())) {
            return product;
        } else {
            throw new ItsNotBelongException("Product doesn't belong to the section");
        }
    }

    private Integer validateAvailableSpace(Section section, InboundOrderDto inboundOrderDto) {
        Integer quantity = inboundOrderDto.getBatchStockList().stream().reduce(0, (partialQuantity, batch) ->
                partialQuantity + batch.getInitialQuantity(), Integer::sum);

        if (section.getAvailableSpace() <= quantity) {
            throw new ExceededStock("Quantity of products in batches exceeds current stock value");
        }
        return section.getAvailableSpace() - quantity;
    }
}
