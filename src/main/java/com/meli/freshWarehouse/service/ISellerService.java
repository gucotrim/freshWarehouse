package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.SellerDto;
import com.meli.freshWarehouse.model.Seller;

public interface ISellerService {
    Seller getSellerById(long id);

    Seller insertSeller(SellerDto sellerDto);

    void deleteSeller(long id);

    Seller updateSeller(Seller seller);

    boolean existsById(long id);
}