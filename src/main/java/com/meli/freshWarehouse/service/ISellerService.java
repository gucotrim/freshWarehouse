package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.model.Seller;

public interface ISellerService {
    Seller getSellerById(long id);
    Seller insertSeller(Seller seller);
    void deleteSeller(long id);
    Seller updateSeller(Seller seller);
}
