package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.dto.InboundOrderResponseDto;
import com.meli.freshWarehouse.service.InboundOrderService;
import com.meli.freshWarehouse.util.GenerateInboundOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class InboundOrderControllerTest {

    @InjectMocks
    InboundOrderController inboundOrderController;

    @Mock
    InboundOrderService inboundOrderService;

    @Test
    void saveInboundOrder() {
        Mockito.when(inboundOrderService.save(ArgumentMatchers.any(InboundOrderDto.class)))
                .thenReturn(GenerateInboundOrder.inboundOrderResponseDto());

        InboundOrderDto inboundOrderDtoGenerated = GenerateInboundOrder.inboundOrderDto();

        ResponseEntity<InboundOrderResponseDto> inboundOrderResponseDtoResponseEntity = inboundOrderController.save(inboundOrderDtoGenerated);

        assertThat(inboundOrderResponseDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(inboundOrderResponseDtoResponseEntity.getBody()).isNotNull();
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getOrder().getId()).isPositive();
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getOrder().getOrderDate().toString()).isEqualTo(inboundOrderDtoGenerated.getOrderDate());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList()).isNotEmpty();
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList()).isNotNull();
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().size()).isEqualTo(inboundOrderDtoGenerated.getBatchStockList().size());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getId()).isPositive();
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getProductId())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getProductId());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getMinimumTemperature())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getMinimumTemperature());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getCurrentTemperature())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getCurrentTemperature());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getInitialQuantity())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getInitialQuantity());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getCurrentQuantity())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getCurrentQuantity());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getManufacturingDate().toString())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getManufacturingDate());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getManufacturingTime().toString())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getManufacturingTime());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getDueDate().toString())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getDueDate());
        Mockito.verify(inboundOrderService, Mockito.atLeastOnce()).save(inboundOrderDtoGenerated);
    }

    @Test
    void updateInboundOrder() {
        Mockito.when(inboundOrderService.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(InboundOrderDto.class)))
                .thenReturn(GenerateInboundOrder.inboundOrderResponseDto());

        InboundOrderDto inboundOrderDtoGenerated = GenerateInboundOrder.inboundOrderDto();

        ResponseEntity<InboundOrderResponseDto> inboundOrderResponseDtoResponseEntity = inboundOrderController.update(1L, inboundOrderDtoGenerated);

        assertThat(inboundOrderResponseDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(inboundOrderResponseDtoResponseEntity.getBody()).isNotNull();
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getOrder().getId()).isEqualTo(1L);
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getOrder().getOrderDate().toString()).isEqualTo(inboundOrderDtoGenerated.getOrderDate());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList()).isNotEmpty();
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList()).isNotNull();
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().size()).isEqualTo(inboundOrderDtoGenerated.getBatchStockList().size());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getId()).isPositive();
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getProductId())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getProductId());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getMinimumTemperature())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getMinimumTemperature());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getCurrentTemperature())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getCurrentTemperature());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getInitialQuantity())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getInitialQuantity());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getCurrentQuantity())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getCurrentQuantity());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getManufacturingDate().toString())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getManufacturingDate());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getManufacturingTime().toString())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getManufacturingTime());
        assertThat(inboundOrderResponseDtoResponseEntity.getBody().getBatchStockList().get(0).getDueDate().toString())
                .isEqualTo(inboundOrderDtoGenerated.getBatchStockList().stream().findAny().get().getDueDate());
        Mockito.verify(inboundOrderService, Mockito.atLeastOnce()).update(1L, inboundOrderDtoGenerated);
    }
}
