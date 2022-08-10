package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.InboundOrderResponseDto;
import com.meli.freshWarehouse.repository.*;
import com.meli.freshWarehouse.util.GenerateInboundOrder;
import com.meli.freshWarehouse.util.GenerateRepresentative;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@Log4j2
public class InboundOrderServiceTest {

    @InjectMocks
    private InboundOrderService inboundOrderService;

    @InjectMocks
    private RepresentativeService representativeService;

    @InjectMocks
    private WarehouseService warehouseService;

    @Mock
    private RepresentativeRepo representativeRepo;

    @Mock
    private ISectionRepo iSectionRepo;

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private BatchRepo batchRepo;

    @Mock
    private WarehouseRepo warehouseRepo;


//    @Test
//    public void save_InboundOrder_WhenInboundIsValid() {
//
//        BDDMockito.when(representativeRepo.findById(1l))
//                .thenReturn(Optional.ofNullable(GenerateRepresentative.validRepresentative1()));
//
//        InboundOrderResponseDto responseDto = inboundOrderService.save(
//                GenerateInboundOrder.validInboundOrderDto1()
//        );
//
//    }

}
