package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.RepresentativeDTO;
import com.meli.freshWarehouse.model.Representative;
import com.meli.freshWarehouse.service.RepresentativeService;
import com.meli.freshWarehouse.service.WarehouseService;
import com.meli.freshWarehouse.util.GenerateRepresentative;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RepresentativeControllerTest {

    @InjectMocks
    private RepresentativeController representativeController;

    @Mock
    private RepresentativeService representativeService;

    @Mock
    private WarehouseService warehouseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        RepresentativeDTO representativeDtoMock = GenerateRepresentative.newRepresentativeDto1();
        Representative representativeMock = GenerateRepresentative.newRepresentative1();

        Mockito.when(warehouseService.getWarehouseById(Mockito.anyLong()))
                .thenReturn(representativeMock.getWarehouse());

        Mockito.when(representativeService.save(representativeDtoMock))
                .thenReturn(representativeMock);

        ResponseEntity<Representative> representativeSaved = representativeController.save(representativeDtoMock);

        Assertions.assertEquals(representativeSaved.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void findAll() {

       List<Representative> representativeList = List.of(new Representative(), new Representative());
        Mockito.when(representativeService.findAll())
                .thenReturn(representativeList);
        representativeController.findAll();

        Assertions.assertEquals(representativeController.findAll().getStatusCode(),HttpStatus.OK);
    }

    @Test
    void findById() {

        Representative representativeMock = Mockito.mock(Representative.class);

        Mockito.when(representativeService.findById(Mockito.anyLong()))
                .thenReturn(representativeMock);

        ResponseEntity<Representative> representativeFound = representativeController.findById(Mockito.anyLong());

        Assertions.assertEquals(representativeFound.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void update() {
        Representative representativeMock = Mockito.mock(Representative.class);
        RepresentativeDTO representativeMockDto = Mockito.mock(RepresentativeDTO.class);

        Mockito.when(representativeService.update(ArgumentMatchers.anyLong(), ArgumentMatchers.any(RepresentativeDTO.class)))
                .thenReturn(representativeMock);

        ResponseEntity<Representative> representativeUpdate = representativeController.update(1L,representativeMockDto);

        Assertions.assertEquals(representativeUpdate.getStatusCode(), HttpStatus.OK);
        //Assertions.assertEquals(representativeUpdate.getBody(), representativeMock);

    }

    @Test
    void delete() {
        representativeService.delete(Mockito.anyLong());

        ResponseEntity<Void> representativeDelete = representativeController.delete(Mockito.anyLong());

        Assertions.assertEquals(representativeDelete.getStatusCode(),HttpStatus.NO_CONTENT);
    }
}