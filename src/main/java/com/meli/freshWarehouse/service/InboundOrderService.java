package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.BatchResponseDto;
import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.dto.InboundOrderResponseDto;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Order;
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

    @Override
    public InboundOrderResponseDto save(InboundOrderDto inboundOrderDto) {
        Order order = orderRepo.save(Order.builder()
                .orderDate(inboundOrderDto.getOrderDate())
                .representative(representativeService.findById(inboundOrderDto.getRepresentativeId()))
                .section(sectionService.getById(inboundOrderDto.getSectionId()))
                .build());



        List<Batch> batchList = batchRepo.saveAll(
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
}
