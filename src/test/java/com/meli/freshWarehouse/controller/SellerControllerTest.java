package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.SellerDto;
import com.meli.freshWarehouse.model.Seller;
import com.meli.freshWarehouse.service.SellerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SellerControllerTest {

    @InjectMocks
    private SellerController sellerController;

    @Mock
    private SellerService sellerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {

        Seller sellerMock = Mockito.mock(Seller.class);

        Mockito.when(sellerService.getSellerById(Mockito.anyLong()))
                .thenReturn(sellerMock);

        ResponseEntity<Seller> sellerFound = sellerController.findById(1L);

       Assertions.assertEquals(sellerFound.getStatusCode(), HttpStatus.OK);
       Assertions.assertEquals(sellerFound.getBody(), sellerMock);
    }

    @Test
    void insertSeller() {
        SellerDto sellerDtoMock = Mockito.mock(SellerDto.class);
        Seller sellerMock = Mockito.mock(Seller.class);

        Mockito.when(sellerService.insertSeller(sellerDtoMock))
                .thenReturn(sellerMock);

        ResponseEntity<Seller> sellerInsert = sellerController.insertSeller(sellerDtoMock);

        Assertions.assertEquals(sellerInsert.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals(sellerInsert.getBody(), sellerMock);
    }

    @Test
    void updateSeller() {

        Seller sellerMock = Mockito.mock(Seller.class);

        Mockito.when(sellerService.updateSeller(sellerMock))
                .thenReturn(sellerMock);

        ResponseEntity<Seller> sellerUpdate= sellerController.updateSeller(sellerMock);

        Assertions.assertEquals(sellerUpdate.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(sellerUpdate.getBody(), sellerMock);

    }

    @Test
    void deleteSeller() {

        sellerService.deleteSeller(Mockito.anyLong());

        ResponseEntity<Void> sellerDelete = sellerController.deleteSeller(Mockito.anyLong());

        Assertions.assertEquals(sellerDelete.getStatusCode(),HttpStatus.NO_CONTENT);
        verify(sellerService, Mockito.atLeastOnce()).deleteSeller(Mockito.anyLong());
    }
}