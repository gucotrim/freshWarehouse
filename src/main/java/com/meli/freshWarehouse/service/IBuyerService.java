package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.BuyerDto;
import com.meli.freshWarehouse.model.Buyer;

public interface IBuyerService {
    Buyer getBuyerById(Long id);

    Buyer insertBuyer(BuyerDto buyerDto);

    void deleteBuyer(Long id);

    Buyer updateBuyer(Long id, BuyerDto buyerDto);

    boolean existsById(Long id);
}