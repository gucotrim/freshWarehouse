package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.dto.InboundOrderResponseDto;
import com.meli.freshWarehouse.model.Order;
import com.meli.freshWarehouse.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class InboundOrderServiceTest {

    @InjectMocks
    private InboundOrderService inboundOrderService;

    @Mock
    private RepresentativeService representativeService;

    @Mock
    private SectionService sectionService;

    @Mock
    private OrderService orderService;

    @Mock
    private BatchService batchService;

    @Mock
    private ProductService productService;


    @BeforeEach
    private void setup() {
        BDDMockito.when(representativeService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateRepresentative.validRepresentative1());

        BDDMockito.when(sectionService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateSection.validSection1());

        BDDMockito.when(orderService.save(ArgumentMatchers.any()))
                .thenReturn(GenerateOrder.validOrder1());

        BDDMockito.when(batchService.saveAll(ArgumentMatchers.anyList()))
                .thenReturn(GenerateBachStock.validBatchResponseDtoList());

        BDDMockito.when(productService.getProductById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateProduct.validProduct1());

    }

    @Test
    public void save_InboundOrder_WhenInboundIsValid() {

        Long expectedId = 1L;

        InboundOrderResponseDto responseDto = inboundOrderService.save(
                GenerateInboundOrder.validInboundOrderDto1()
        );

        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getOrder().getId()).isEqualTo(expectedId);
        assertThat(responseDto.getBatchStockList().get(0).getId()).isEqualTo(expectedId);

    }

    @Test
    void getInboundOrderById() {

        Long inputOrderId = 1L;

        BDDMockito.when(orderService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateOrder.validOrder1());

        Order inboundOrderServiceResponse = inboundOrderService.getInboundOrderById(inputOrderId);
        assertThat(inboundOrderServiceResponse).isNotNull();
        assertThat(inboundOrderServiceResponse.getId()).isEqualTo(inputOrderId);


    }

    @Test
    void update() {
        
    }
}
