package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.model.Order;
import com.meli.freshWarehouse.model.Representative;
import com.meli.freshWarehouse.model.Seller;
import com.meli.freshWarehouse.repository.OrderRepo;
import com.meli.freshWarehouse.util.GenerateOrder;
import com.meli.freshWarehouse.util.GenerateRepresentative;
import com.meli.freshWarehouse.util.GenerateSeller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Spy
    private OrderRepo orderRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Order order = GenerateOrder.newOrder1();

        BDDMockito.when(orderRepo.save(order))
                .thenReturn(order);

        orderService.save(order);
        assertThat(order).isNotNull();
        verify(orderRepo, Mockito.times(1)).save(order);


    }

    @Test
    void findById() {
        BDDMockito.when(orderRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateOrder.validOrder1()));

        Order order = GenerateOrder.validOrder1();
        Order orderResponse = orderService.findById(order.getId());

        assertThat(orderResponse).isNotNull();
        assertThat(orderResponse.getId()).isEqualTo(order.getId());
    }
}