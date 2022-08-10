package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.dto.InboundOrderResponseDto;
import com.meli.freshWarehouse.dto.RepresentativeDTO;
import com.meli.freshWarehouse.model.Representative;
import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.repository.*;
import com.meli.freshWarehouse.util.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@Log4j2
public class InboundOrderServiceTest {

    @InjectMocks
    private InboundOrderService inboundOrderService;

    @InjectMocks
    private RepresentativeService representativeService;

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


    @Test
    public void save_InboundOrder_WhenInboundIsValid() {

        BDDMockito.when(warehouseRepo.save(ArgumentMatchers.any(Warehouse.class)))
                        .thenReturn(GenerateWarehouse.validWarehouse1());

        Warehouse savedWarehouse = warehouseRepo.save(GenerateWarehouse.newWarehouse1());

        BDDMockito.when(representativeRepo.save(ArgumentMatchers.any(Representative.class)))
                        .thenReturn(GenerateRepresentative.validRepresentative1());

        Representative savedRepresentative = representativeRepo.save(GenerateRepresentative.newRepresentative1());


        BDDMockito.when(representativeRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateRepresentative.validRepresentative1()));

        InboundOrderResponseDto responseDto = inboundOrderService.save(
                GenerateInboundOrder.validInboundOrderDto1()
        );

        log.info(responseDto);


    }

}
