package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.SellerDto;
import com.meli.freshWarehouse.model.Seller;

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


}
