package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.BatchResponseDto;
import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.dto.InboundOrderResponseDto;
import com.meli.freshWarehouse.exception.InboundOrderNotFoundException;
import com.meli.freshWarehouse.model.*;
import com.meli.freshWarehouse.repository.BatchRepo;
import com.meli.freshWarehouse.repository.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InboundOrderService implements IInboundOrderService{

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

    private List<Batch> saveBatchList(InboundOrderDto inboundOrderDto, Order order) {
        return batchRepo.saveAll(
                inboundOrderDto.getBatchStockList().stream().map(b -> Batch.builder()
                        .order(order)
                        .section(order.getSection())
                        .product(productService.getProductById(b.getProductId()))
                        .currentQuantity(b.getCurrentQuantity())
                        .currentTemperature(b.getCurrentTemperature())
                        .initialQuantity(b.getInitialQuantity())
                        .manufacturingDate(b.getManufacturingDate())
                        .minimumTemperature(b.getMinimumTemperature())
                        .dueDate(b.getDueDate())
                        .manufacturingTime(b.getManufacturingTime())
                        .build()).collect(Collectors.toList())
        );
    }

    private InboundOrderResponseDto buildInboundOrderResponseDto(Order order, List<Batch> batchList) {
        return InboundOrderResponseDto.builder()
                .order(order)
                .batchStockList(batchList.stream().map(b ->
                        BatchResponseDto.builder()
                                .id(b.getId())
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

    private boolean validateInboundOrderFields(Warehouse warehouse, Representative representative, Section section, Product product) {

        return warehouse.isValid() &&
                representative.belongsToWarehouse(warehouse) &&
                section.isValid() &&
                product.isFromSection(section) &&
                section.hasSpace();
    }

    @Override
    public InboundOrderResponseDto save(InboundOrderDto inboundOrderDto) {
        Order order = orderRepo.save(Order.builder()
                .orderDate(inboundOrderDto.getOrderDate())
                .representative(representativeService.findById(inboundOrderDto.getRepresentativeId()))
                .section(sectionService.getById(inboundOrderDto.getSectionId()))
                .build());


        List<Batch> batchList = saveBatchList(inboundOrderDto, order);

        return buildInboundOrderResponseDto(order, batchList);
    }

    @Override
    public Order getInboundOrderById(Long inboundOrderId) {
        return orderRepo.findById(inboundOrderId).orElseThrow(() -> new InboundOrderNotFoundException("Inbound Order ID not found."));
    }

    @Override
    public InboundOrderResponseDto update(Long id, InboundOrderDto inboundOrderDto) {
        Order order = this.getInboundOrderById(id);
        Representative representative = representativeService.findById(inboundOrderDto.getRepresentativeId());
        Section section = sectionService.getById(inboundOrderDto.getSectionId());
        order.setOrderDate(inboundOrderDto.getOrderDate());
        order.setRepresentative(representative);
        order.setSection(section);

        List<Batch> batchList = saveBatchList(inboundOrderDto, order);
        boolean exists = orderRepo.existsById(order.getId());
        if (!exists) throw new InboundOrderNotFoundException("Inbound Order ID not found.");
        return buildInboundOrderResponseDto(order, batchList);
    }
}
