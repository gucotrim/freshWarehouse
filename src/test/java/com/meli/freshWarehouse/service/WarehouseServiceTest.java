package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.WarehouseDTO;
import com.meli.freshWarehouse.exception.WarehouseNotFoundException;
import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.repository.WarehouseRepo;
import com.meli.freshWarehouse.util.GenerateWarehouse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WarehouseServiceTest {

    @InjectMocks
    private WarehouseService warehouseService;

    @Mock
    private WarehouseRepo warehouseRepo;

    @Test
    void createWarehouse_WhenWarehouseIsValid() {

        BDDMockito.when(warehouseRepo.save(ArgumentMatchers.any(Warehouse.class)))
                .thenReturn(GenerateWarehouse.validWarehouse1());

        WarehouseDTO warehouseDTO = GenerateWarehouse.newWarehouseDto1();

        Warehouse warehouseResponse = warehouseService.createWarehouse(warehouseDTO);

        assertThat(warehouseResponse).isNotNull();
        assertThat(warehouseResponse.getId()).isPositive();
        assertThat(warehouseResponse.getAddress()).isEqualTo(GenerateWarehouse.expectedResultWarehouse().get(0));
        assertThat(warehouseResponse.getCity()).isEqualTo(GenerateWarehouse.expectedResultWarehouse().get(1));
        assertThat(warehouseResponse.getState()).isEqualTo(GenerateWarehouse.expectedResultWarehouse().get(2));
        assertThat(warehouseResponse.getCountry()).isEqualTo(GenerateWarehouse.expectedResultWarehouse().get(3));
        assertThat(warehouseResponse.getNumber()).isEqualTo(
                Integer.parseInt(GenerateWarehouse.expectedResultWarehouse().get(4)));

    }

    @Test
    void listAllWareHouse() {

        List<Warehouse> warehouseList = List.of(
                GenerateWarehouse.validWarehouse1(),
                GenerateWarehouse.validWarehouse2()
        );

        BDDMockito.when(warehouseRepo.findAll()).thenReturn(warehouseList);

        List<Warehouse> responseWarehouseList = warehouseService.listAll();
        assertThat(responseWarehouseList).isNotNull();
        assertThat(responseWarehouseList.size()).isEqualTo(2);
        verify(warehouseRepo, Mockito.times(1)).findAll();
    }

    @Test
    void getWarehouseById_When_IdIsValid() {
        BDDMockito.when(warehouseRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateWarehouse.validWarehouse1()));

        Warehouse warehouse = GenerateWarehouse.validWarehouse1();
        Warehouse warehouseResponse = warehouseService.getWarehouseById(warehouse.getId());

        assertThat(warehouseResponse).isNotNull();
        assertThat(warehouseResponse.getId()).isEqualTo(warehouse.getId());

    }

    @Test
    void returnedWarehouseNoFoundException_When_IdIsInValid_() {

        String expectedMessage = "Warehouse ID not found.";

        Exception exception = assertThrows(WarehouseNotFoundException.class, () ->
                warehouseService.getWarehouseById(
                        warehouseService.getWarehouseById(2L).getId())
        );

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);

    }


    @Test
    void updateWarehouse_When_IdWarehouseIsExists() {

        BDDMockito.when(warehouseRepo.save(ArgumentMatchers.any(Warehouse.class)))
                .thenReturn(GenerateWarehouse.updatedWarehouse());

        BDDMockito.when(warehouseRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateWarehouse.validWarehouse1()));
        BDDMockito.when(warehouseRepo.existsById(ArgumentMatchers.anyLong()))
                .thenReturn(true);

        Warehouse warehouse = GenerateWarehouse.validWarehouse1();
        Warehouse warehouseUpdated = warehouseService.getWarehouseById(warehouse.getId());

        warehouseUpdated.setAddress("Updated address");
        warehouseUpdated.setCity("Update city");
        warehouseUpdated.setCountry("Updated country");
        warehouseUpdated.setState("Updated state");
        warehouseUpdated.setNumber(26);

        Warehouse responseWarehouse = warehouseService.updateWarehouse(warehouseUpdated);

        assertThat(responseWarehouse).isNotNull();
        assertThat(responseWarehouse.getId()).isEqualTo(warehouse.getId());
        assertThat(responseWarehouse.getAddress()).isNotEqualTo(warehouse.getAddress());
        assertThat(responseWarehouse.getCity()).isNotEqualTo(warehouse.getCity());
        verify(warehouseRepo, Mockito.times(1)).save(warehouseUpdated);

    }


}