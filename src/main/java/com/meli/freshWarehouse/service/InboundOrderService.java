package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.*;
import com.meli.freshWarehouse.exception.ExceededStock;
import com.meli.freshWarehouse.exception.ItsNotBelongException;
import com.meli.freshWarehouse.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InboundOrderService implements IInboundOrderService {

    private final BatchService batchService;
    private final OrderService orderService;
    private final RepresentativeService representativeService;
    private final SectionService sectionService;
    private final ProductService productService;

    public InboundOrderService(BatchService batchService, OrderService orderService, RepresentativeService representativeService, SectionService sectionService, ProductService productService) {
        this.batchService = batchService;
        this.orderService = orderService;
        this.representativeService = representativeService;
        this.sectionService = sectionService;
        this.productService = productService;
    }

    @Override
    @Transactional
    public InboundOrderResponseDto save(InboundOrderDto inboundOrderDto) {

        Representative representative = representativeService.findById(inboundOrderDto.getRepresentativeId());
        Section section = sectionService.findById(inboundOrderDto.getSectionId());

        orderIsValid(representative, section);

        Integer quantityStock = validateAvailableSpace(section, inboundOrderDto);

        Order order = orderService.save(Order.builder()
                .orderDate(LocalDate.parse(inboundOrderDto.getOrderDate(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .representative(representative)
                .section(section)
                .build());

        List<Batch> batchListSaved = saveBatches(inboundOrderDto, section, order);

        section.setAvailableSpace(quantityStock);
        sectionService.updateSection(section.getId(), SectionDto.builder()
                .name(section.getName())
                .availableSpace(section.getAvailableSpace())
                .build());

        return getInboundOrderResponse(order, batchListSaved);
    }


    private InboundOrderResponseDto getInboundOrderResponse(Order order, List<Batch> batchList) {
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

    private List<Batch> saveBatches(InboundOrderDto inboundOrderDto, Section section, Order order) {
        List<Batch> batchList = batchService.saveAll(
                inboundOrderDto.getBatchStockList().stream().map(b -> Batch.builder()
                        .order(order)
                        .section(order.getSection())
                        .product(validateProduct(productService.getProductById(b.getProductId()), section))
                        .currentQuantity(b.getCurrentQuantity())
                        .currentTemperature(b.getCurrentTemperature())
                        .initialQuantity(b.getInitialQuantity())
                        .manufacturingDate(LocalDate.parse(b.getManufacturingDate(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .minimumTemperature(b.getMinimumTemperature())
                        .dueDate(LocalDate.parse(b.getDueDate(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .manufacturingTime(LocalDateTime.parse(b.getManufacturingTime(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
                        .build()).collect(Collectors.toList())
        );
        return batchList;
    }

    private void orderIsValid(Representative representative, Section section) {
        if (!(representative.getWarehouse().getId().equals(section.getWarehouse().getId()))) {
            throw new ItsNotBelongException("Representative doesn't belong to the warehouse");
        }
    }

    private Product validateProduct(Product product, Section section) {

        product.getSections().stream().filter((p) ->
                p.getId().equals(section.getId())
        ).findFirst().orElseThrow(() -> new ItsNotBelongException("Product doesn't belong to the section"));

        return product;

    }

    private Integer validateAvailableSpace(Section section, InboundOrderDto inboundOrderDto) {
        Integer quantity = inboundOrderDto.getBatchStockList().stream().reduce(0, (partialQuantity, batch) ->
                partialQuantity + batch.getInitialQuantity(), Integer::sum);

        if (section.getAvailableSpace() <= quantity) {
            throw new ExceededStock("Quantity of products in batches exceeds current stock value. Total Space of: "
                    + quantity);
        }
        return section.getAvailableSpace() - quantity;
    }

    @Override
    public Order getInboundOrderById(Long inboundOrderId) {
        return orderService.findById(inboundOrderId);
    }

    @Override
    public InboundOrderResponseDto update(Long id, InboundOrderDto inboundOrderDto) {
        Order order = this.getInboundOrderById(id);
        Representative representative = representativeService.findById(inboundOrderDto.getRepresentativeId());
        Section section = sectionService.findById(inboundOrderDto.getSectionId());

        orderIsValid(representative, section);

        Integer quantityStock = validateAvailableSpace(section, inboundOrderDto);
        List<Batch> batchList = saveBatches(inboundOrderDto, section, order);
        Set<Batch> batchSet = new HashSet<>(batchList);

        order.setOrderDate(LocalDate.parse(inboundOrderDto.getOrderDate(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        order.setRepresentative(representative);
        order.setSection(section);
        order.setListBatch(batchSet);


        section.setAvailableSpace(quantityStock);
        sectionService.updateSection(section.getId(), SectionDto.builder()
                .name(section.getName())
                .availableSpace(section.getAvailableSpace())
                .build());

        return getInboundOrderResponse(order, batchList);
    }
}
