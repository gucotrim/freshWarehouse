package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.SellerDto;
import com.meli.freshWarehouse.model.Seller;

import java.util.Arrays;
import java.util.List;

public class GenerateSeller {

    public static final SellerDto newSeller1() {

        return SellerDto.builder()
                .name("New Seller test 1")
                .build();
    }

    public static final SellerDto newSeller2() {

        return SellerDto.builder()
                .name("New Seller test 2")
                .build();
    }

    public static final Seller validSeller1() {

        return Seller.builder()
                .id(1L)
                .name("New Seller test 1")
                .build();
    }

    public static final Seller validSeller2() {

        return Seller.builder()
                .id(2L)
                .name("New Seller test 2")
                .build();
    }

    public static final Seller updateSeller() {

        return Seller.builder()
                .id(1L)
                .name("Update Seller test 1")
                .build();
    }

    public static final List<String> expectedResultSeller() {
        List<String> expectedList =
                Arrays.asList("New Seller test 1");

        return expectedList;

    }

}
