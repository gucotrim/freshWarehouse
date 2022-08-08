package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.SellerDto;
import com.meli.freshWarehouse.model.Seller;

public interface ISellerService {
    Seller getSellerById(Long id);

    Seller insertSeller(SellerDto sellerDto);

    void deleteSeller(Long id);

    Seller updateSeller(Seller seller);

    boolean existsById(Long id);
}