package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.dto.InboundOrderResponseDto;
import com.meli.freshWarehouse.exception.ExceededStock;
import com.meli.freshWarehouse.exception.ItsNotBelongException;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class InboundOrderServiceTest {

    @InjectMocks
    private InboundOrderService inboundOrderService;

    @Mock
    private InboundOrderService inboundOrderServiceMock;

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

        BDDMockito.when(representativeService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateRepresentative.validRepresentative1());

        Long expectedId = 1L;

        InboundOrderResponseDto responseDto = inboundOrderService.save(
                GenerateInboundOrder.validInboundOrderDto1()
        );

        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getOrder().getId()).isEqualTo(expectedId);
        assertThat(responseDto.getBatchStockList().get(0).getId()).isEqualTo(expectedId);

    }

    @Test
    public void returnItsNotBelongExceptionValidateProduct_when_representativeIsInvalid() {
        String expectedMessage = "Representative doesn't belong to the warehouse";
        BDDMockito.when(representativeService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateRepresentative.validRepresentative2());

        Exception exception = assertThrows(ItsNotBelongException.class,
                () -> inboundOrderService.save(GenerateInboundOrder.validInboundOrderDto1()));

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);

    }

    @Test
    public void returnExceededStock_when_ThereIsNoSpaceInStock() {

        String expectedMessage = "Quantity of products in batches exceeds current stock value. Total Space of: " + 29;
        BDDMockito.when(representativeService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateRepresentative.validRepresentative1());

        Exception exception = assertThrows(ExceededStock.class,
                () -> inboundOrderService.save(GenerateInboundOrder.validInboundOrderDto2()));

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);

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
    void updateInboundOrder_When_IsInboundOrderExist() {

        Long inputId = 1L;

        BDDMockito.when(inboundOrderServiceMock.update(ArgumentMatchers.anyLong(),
                        ArgumentMatchers.any(InboundOrderDto.class)))
                .thenReturn(GenerateInboundOrder.inboundOrderDtoUpdate());

        BDDMockito.when(representativeService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateRepresentative.validRepresentative1());

        BDDMockito.when(orderService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateOrder.validOrderResponse());

        InboundOrderDto inboundOrderDto = GenerateInboundOrder.inboundOrderDto();

        inboundOrderDto.getBatchStockList().forEach(p -> {
            p.setCurrentTemperature(22.0F);
            p.setMinimumTemperature(25.0F);
            p.setInitialQuantity(2);
            p.setCurrentQuantity(2);
            p.setManufacturingDate("2022-05-24");
            p.setManufacturingTime("2022-06-03T14:24:54");
            p.setDueDate("2022-08-15");
        });


        InboundOrderResponseDto inboundOrderResponseDto = inboundOrderService.update(inputId, inboundOrderDto);
        assertThat(inboundOrderResponseDto).isNotNull();
        assertThat(inboundOrderResponseDto.getOrder().getId()).isEqualTo(1L);
        assertThat(inboundOrderResponseDto.getOrder().getOrderDate()).isEqualTo("2022-08-15");
    }
}
