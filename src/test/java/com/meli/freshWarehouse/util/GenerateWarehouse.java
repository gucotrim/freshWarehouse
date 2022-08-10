package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.model.Warehouse;

public class GenerateWarehouse {

    public static final Warehouse newWarehouse1() {
        return Warehouse.builder()
                .address("Rua um test")
                .city("São Paulo")
                .state("São Paulo")
                .country("Brasil")
                .number(23)
                .build();
    }

    public static final Warehouse newWarehouse2() {
        return Warehouse.builder()
                .address("Rua dois test")
                .city("Belo Horizonte")
                .state("Minas Gerais")
                .country("Brasil")
                .number(25)
                .build();
    }

    public static final Warehouse validWarehouse1() {

        return Warehouse.builder()
                .id(1L)
                .address("Rua um test")
                .city("São Paulo")
                .state("São Paulo")
                .country("Brasil")
                .number(23)
                .build();
    }

    public static final Warehouse validWarehouse2() {

        return Warehouse.builder()
                .id(2L)
                .address("Rua dois test")
                .city("Belo Horizonte")
                .state("Minas Gerais")
                .country("Brasil")
                .number(25)
                .build();
    }

}
