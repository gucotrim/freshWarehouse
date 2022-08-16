package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.SellerDto;
import com.meli.freshWarehouse.model.Seller;
import com.meli.freshWarehouse.repository.ISellerRepo;
import com.meli.freshWarehouse.util.GenerateSeller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

    @InjectMocks
    private SellerService sellerService;

    @Mock
    private ISellerRepo iSellerRepo;

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

        BDDMockito.doNothing().when(iSellerRepo).deleteById(ArgumentMatchers.anyLong());

        BDDMockito.when(iSellerRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateSeller.validSeller1()));

        Seller seller = GenerateSeller.validSeller1();
        sellerService.deleteSeller(seller.getId());

        assertThatCode(
                () -> sellerService.deleteSeller(seller.getId())
        );

        verify(iSellerRepo, Mockito.atLeastOnce()).deleteById(seller.getId());
    }

    @Test
    void updateSeller() {

        BDDMockito.when(iSellerRepo.save(ArgumentMatchers.any(Seller.class)))
                .thenReturn(GenerateSeller.updateSeller());

        BDDMockito.when(iSellerRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateSeller.validSeller1()));

        Seller seller = GenerateSeller.validSeller1();
        Seller sellerUpdated = sellerService.getSellerById(seller.getId());

        sellerUpdated.setName("Update Seller test 1");

        Seller responseSeller = sellerService.updateSeller(sellerUpdated);

        assertThat(responseSeller).isNotNull();
        assertThat(responseSeller.getId()).isEqualTo(seller.getId());
        assertThat(responseSeller.getName()).isNotEqualTo(seller.getName());
        verify(iSellerRepo, Mockito.times(1)).save(sellerUpdated);

    }


    @ParameterizedTest(name = "[{index}] ==> Input value: ''{0}'' - Expected result: ''{1}''")
    @CsvSource({"true, true", "false, false"})
    void existsById(boolean inputValue, boolean expectedResult) {

        BDDMockito.when(iSellerRepo.existsById(ArgumentMatchers.anyLong()))
                .thenReturn(inputValue);

        Seller seller = GenerateSeller.validSeller1();

        boolean responseExists = sellerService.existsById(seller.getId());
        assertThat(responseExists).isEqualTo(expectedResult);
    }
}