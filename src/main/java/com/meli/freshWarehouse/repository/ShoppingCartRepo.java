package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findShoppingCartByBuyer_IdAndAndOrderStatusEquals(Long buyerId, String orderStatus);
}
