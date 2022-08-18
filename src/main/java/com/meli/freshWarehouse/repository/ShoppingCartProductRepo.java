package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.model.ShoppingCart;
import com.meli.freshWarehouse.model.ShoppingCartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartProductRepo extends JpaRepository<ShoppingCartProduct, Long> {
    boolean existsShoppingCartProductByShoppingCart_IdAndProduct_Id(Long shoppginCartId, Long productId);
    Optional<ShoppingCartProduct> findShoppingCartProductByShoppingCartAndProduct_Id(ShoppingCart shoppingCart, Long productId);
    void deleteByProduct_Id(Long productId);
}
