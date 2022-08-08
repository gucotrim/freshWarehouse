package com.meli.freshWarehouse.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.freshWarehouse.exception.NotFoundException;
import com.meli.freshWarehouse.model.Seller;
import com.meli.freshWarehouse.repository.ISellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SellerService implements ISellerService{

    @Autowired
    ISellerRepo repository;

    @Override
    public Seller getSellerById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Seller not found!"));
    }

    @Override
    public Seller insertSeller(Seller seller) {
        if(seller.getId() > 0) return null;
        return repository.save(seller);
    }

    @Override
    public void deleteSeller(long id) {
        getSellerById(id);

        repository.deleteById(id);
    }

    @Override
    public Seller updateSeller(Seller seller) {
        getSellerById(seller.getId());

       return repository.save(seller);
    }
}
