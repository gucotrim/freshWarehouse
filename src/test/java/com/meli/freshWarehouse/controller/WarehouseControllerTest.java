package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.ProductStockResponseDTO;
import com.meli.freshWarehouse.dto.WarehouseDTO;
import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.service.WarehouseService;
import com.meli.freshWarehouse.util.GenerateProduct;
import com.meli.freshWarehouse.util.GenerateWarehouse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WarehouseControllerTest {

    @InjectMocks
    private WarehouseController warehouseController;

    @Mock
    private WarehouseService warehouseService;

    @Mock
    WarehouseDTO warehouseDTO = GenerateWarehouse.newWarehouseDto1();

    @Test
    void getStockOfProductById_when_productIsAvailableInStock() {
        BDDMockito.when(warehouseService.getStockOfProductById(ArgumentMatchers.anyLong())).thenReturn(GenerateProduct.productStockResponseDTOAvailableInStock());
        ProductStockResponseDTO productStockResponseDTOGenerated = GenerateProduct.productStockResponseDTOAvailableInStock();


        ResponseEntity<ProductStockResponseDTO> ProductStockResponseDTOReturned = warehouseController.getStockOfProductById(1L);

        assertThat(ProductStockResponseDTOReturned.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(ProductStockResponseDTOReturned.getBody()).isNotNull();
        assertThat(ProductStockResponseDTOReturned.getBody().getProductId()).isEqualTo(productStockResponseDTOGenerated.getProductId());
        assertThat(ProductStockResponseDTOReturned.getBody().getWarehouses()).isNotNull();
        assertThat(ProductStockResponseDTOReturned.getBody().getWarehouses()).isNotEmpty();
        assertThat(ProductStockResponseDTOReturned.getBody().getWarehouses().size()).isEqualTo(2);
        assertThat(ProductStockResponseDTOReturned.getBody().getWarehouses().get(0).getWarehouseId()).isEqualTo(productStockResponseDTOGenerated.getWarehouses().get(0).getWarehouseId());
        assertThat(ProductStockResponseDTOReturned.getBody().getWarehouses().get(0).getTotalQuantity()).isEqualTo(productStockResponseDTOGenerated.getWarehouses().get(0).getTotalQuantity());
        assertThat(ProductStockResponseDTOReturned.getBody().getWarehouses().get(1).getWarehouseId()).isEqualTo(productStockResponseDTOGenerated.getWarehouses().get(1).getWarehouseId());
        assertThat(ProductStockResponseDTOReturned.getBody().getWarehouses().get(1).getTotalQuantity()).isEqualTo(productStockResponseDTOGenerated.getWarehouses().get(1).getTotalQuantity());
        verify(warehouseService, atLeastOnce()).getStockOfProductById(1L);
    }


    @Test
    void saveWarehouseTest() {
        Warehouse warehouse = GenerateWarehouse.newWarehouse1();

        BDDMockito.when(warehouseService.createWarehouse(ArgumentMatchers.any(Warehouse.class))).thenReturn(warehouse);
        BDDMockito.when(warehouseDTO.toModel()).thenReturn(warehouse);

        ResponseEntity<Warehouse> warehouseResponseDtoReturned = warehouseController.saveWarehouse(warehouseDTO);

        assertThat(warehouseResponseDtoReturned.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(warehouseResponseDtoReturned.getBody()).isNotNull();
        verify(warehouseService).createWarehouse(warehouse);
    }

    //
    @Test
    void listAllWarehouseTest() {
        Warehouse warehouse = GenerateWarehouse.newWarehouse1();
        List<Warehouse> warehouseList = new ArrayList<>();
        warehouseList.add(warehouse);
        BDDMockito.when(warehouseService.listAll()).thenReturn(warehouseList);

        ResponseEntity<List<Warehouse>> warehouseListResponseDtoReturned = warehouseController.listAllWarehouse();

        assertThat(warehouseListResponseDtoReturned.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(warehouseListResponseDtoReturned.getBody()).isNotNull();
        verify(warehouseService).listAll();
    }

    @Test
    void getWarehouseByIdTest() {
        BDDMockito.when(warehouseService.getWarehouseById(ArgumentMatchers.any(Long.class))).thenReturn(GenerateWarehouse.newWarehouse2());

        ResponseEntity<Warehouse> warehouseResponseDtoReturned = warehouseController.getWarehouseById(1L);

        assertThat(warehouseResponseDtoReturned.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(warehouseResponseDtoReturned.getBody()).isNotNull();
        verify(warehouseService).getWarehouseById(1L);
    }

    @Test
    void updateWarehouseTest() {
        Warehouse warehouse = GenerateWarehouse.newWarehouse2();
        BDDMockito.when(warehouseService.updateWarehouse(ArgumentMatchers.any(Warehouse.class))).thenReturn(warehouse);

        ResponseEntity<Warehouse> warehouseResponseDtoReturned = warehouseController.updateWarehouse(warehouse);

        assertThat(warehouseResponseDtoReturned.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(warehouseResponseDtoReturned.getBody()).isNotNull();
        verify(warehouseService).updateWarehouse(warehouse);
    }
}
