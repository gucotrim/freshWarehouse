package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.SellerDto;
import com.meli.freshWarehouse.model.Seller;
import com.meli.freshWarehouse.repository.ISellerRepo;
import com.meli.freshWarehouse.util.GenerateSeller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

    @InjectMocks
    private SellerService sellerService;

    @Mock
    private ISellerRepo iSellerRepo;

    @DisplayName("Buscar o id do seller com id valid")
    @Test
    void getSellerById() {
        BDDMockito.when(iSellerRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateSeller.validSeller1()));

        Seller seller = GenerateSeller.validSeller1();
        Seller sellerResponse = sellerService.getSellerById(seller.getId());

        assertThat(sellerResponse).isNotNull();
        assertThat(sellerResponse.getId()).isEqualTo(seller.getId());

    }

    @Test
    void insertSellerValid() {

        BDDMockito.when(iSellerRepo.save(ArgumentMatchers.any(Seller.class)))
                .thenReturn(GenerateSeller.validSeller1());

        SellerDto sellerDto = GenerateSeller.newSeller1();

        Seller sellerResponse = sellerService.insertSeller(sellerDto);

        assertThat(sellerResponse).isNotNull();
        assertThat(sellerResponse.getId()).isPositive();
    }



    @Test
    void deleteSeller() {
    }

    @Test
    void updateSeller() {

        //TODO refactor update seller

//        BDDMockito.when(iSellerRepo.save(ArgumentMatchers.any(Seller.class)))
//                .thenReturn(GenerateSeller.updateSeller());
//
//        BDDMockito.when(iSellerRepo.findById(ArgumentMatchers.anyLong()))
//                .thenReturn(Optional.ofNullable(GenerateSeller.validSeller1()));
//
//        Seller seller = GenerateSeller.validSeller1();
//        Seller sellerUpdated = sellerService.getSellerById(seller.getId());
//
//        sellerUpdated.setName("Update Seller test 1");
//
//        Seller responseSeller = sellerService.updateSeller(sellerUpdated);
//
//        assertThat(responseSeller).isNotNull();
//        assertThat(responseSeller.getId()).isEqualTo(seller.getId());
//        assertThat(responseSeller.getName()).isNotEqualTo(seller.getName());
//        verify(iSellerRepo, Mockito.times(1)).save(sellerUpdated);

    }

//    @Test
//    void existsById(Long id) {
//        Mockito.when(iSellerRepo.existsById(ArgumentMatchers.anyLong()))
//                .thenReturn(true);
//
//        Seller seller = GenerateSeller.validSeller1();
//
//        Seller sellerResponse = sellerService.existsById(id);
//
//        assertThat(sellerResponse).isNotNull();
//        assertThat(sellerResponse.getId()).isPositive();
//        assertThat(sellerResponse.getName()).isEqualTo(GenerateSeller.expectedResultSeller().get(0));
//    }




}