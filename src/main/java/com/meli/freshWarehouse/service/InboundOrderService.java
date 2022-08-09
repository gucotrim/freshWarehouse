package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.exception.InboundOrderNotFoundException;
import com.meli.freshWarehouse.exception.WarehouseNotFoundException;
import com.meli.freshWarehouse.model.InboundOrder;
import com.meli.freshWarehouse.model.Representative;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.repository.InboundOrderRepo;
import org.springframework.stereotype.Service;

@Service
public class InboundOrderService implements IInboundOrder {

   private final InboundOrderRepo inboundOrderRepo;
   private final RepresentativeService representativeService;
   private final SectionService sectionService;

    public InboundOrderService(InboundOrderRepo inboundOrderRepo, RepresentativeService representativeService, SectionService sectionService) {
        this.inboundOrderRepo = inboundOrderRepo;
        this.representativeService = representativeService;
        this.sectionService = sectionService;

    }

    @Override
    public InboundOrder getInboundOrderById(Long inboundOrderId) {
        return inboundOrderRepo.findById(inboundOrderId).orElseThrow(() -> new InboundOrderNotFoundException("Inbound Order ID not found."));
       }

    @Override
    public InboundOrder update(Long id, InboundOrderDto update) {
        InboundOrder order = this.getInboundOrderById(id);
        Representative representative = representativeService.findById(update.getRepresentativeId());
        Section section = sectionService.getById(update.getSectionId());
        order.setOrderDate(update.getOrderDate());
        order.setRepresentative(representative);
        order.setSection(section);
        boolean exists = inboundOrderRepo.existsById(order.getId());
        if (!exists) throw new InboundOrderNotFoundException("Inbound Order ID not found.");
        return inboundOrderRepo.save(order);
    }

}