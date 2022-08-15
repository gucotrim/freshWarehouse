package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.OrderResponseDto;
import com.meli.freshWarehouse.model.Order;
import com.meli.freshWarehouse.model.Representative;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.model.Warehouse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class GenerateOrder {

    public static final Order newOrder1() {
        Order order = new Order();
        order.setOrderDate(LocalDate.parse(String.valueOf(LocalDate.parse("2022-08-09",
                DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
        order.setRepresentative(GenerateRepresentative.validRepresentative1());
        order.setSection(GenerateSection.validSection1());
        return order;
    }

    public static final Order newOrder2() {
        Order order = new Order();
        order.setOrderDate(LocalDate.parse(String.valueOf(LocalDate.parse("2022-08-09",
                DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
        order.setRepresentative(GenerateRepresentative.validRepresentative2());
        order.setSection(GenerateSection.validSection2());
        return order;
    }

    public static final Order validOrder1() {

        Order order = new Order();
        order.setId(1L);
        order.setOrderDate(LocalDate.parse("2022-08-09"));
        order.setRepresentative(Representative.builder()
                .id(1L)
                .name("Name Representative Test")
                .warehouse(Warehouse.builder()
                        .id(1L)
                        .address("Rua um test")
                        .city("São Paulo")
                        .state("São Paulo")
                        .country("Brasil")
                        .number(23)
                        .build())
                .build());

        order.setSection(Section.builder()
                .id(1L)
                .name("Section test 1")
                .availableSpace(23)
                .warehouse(Warehouse.builder()
                        .id(1L)
                        .address("Rua um test")
                        .city("São Paulo")
                        .state("São Paulo")
                        .country("Brasil")
                        .number(23)
                        .build())
                .build());
        return order;
    }

    public static final Order validOrder2() {
        Order order = new Order();

        order.setId(2L);
        order.setOrderDate(LocalDate.parse(String.valueOf(LocalDate.parse("2022-08-09",
                DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
        order.setRepresentative(GenerateRepresentative.validRepresentative2());
        order.setSection(GenerateSection.validSection2());
        return order;
    }

    public static final Set<Order> validOrderLit() {
        Set<Order> orderList = new HashSet<>();
        orderList.add(validOrder1());
        return orderList;
    }

    public static Order validOrderResponse() {

        return Order.builder()
                .id(1L)
                .orderDate(LocalDate.parse("2022-08-09"))
                .build();

    }
}
