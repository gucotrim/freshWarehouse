package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.model.Order;

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
        order.setListBatch(GenerateBachStock.validBatchResponse());
        return order;
    }

    public static final Order newOrder2() {
        Order order = new Order();
        order.setOrderDate(LocalDate.parse(String.valueOf(LocalDate.parse("2022-18-09",
                DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
        order.setRepresentative(GenerateRepresentative.validRepresentative2());
        order.setSection(GenerateSection.validSection2());
        order.setListBatch(GenerateBachStock.validBatchResponse());
        return order;
    }

    public static final Order validOrder1() {

        Order order = new Order();
        order.setId(1L);
        order.setOrderDate(LocalDate.parse("2022-08-09"));
        order.setRepresentative(GenerateRepresentative.validRepresentative1());
        order.setSection(GenerateSection.validSection1());
        order.setListBatch(GenerateBachStock.validBatchResponse());
        return order;
    }

    public static final Order validOrder2() {
        Order order = new Order();

        order.setId(2L);
        order.setOrderDate(LocalDate.parse(String.valueOf(LocalDate.parse("2022-18-09",
                DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
        order.setRepresentative(GenerateRepresentative.validRepresentative2());
        order.setSection(GenerateSection.validSection2());
        order.setListBatch(GenerateBachStock.validBatchResponse());
        return order;
    }

    public static final Set<Order> validOrderLit() {
        Set<Order> orderList = new HashSet<>();
        orderList.add(validOrder1());
        orderList.add(validOrder2());
        return orderList;
    }
}
