package com.meli.freshWarehouse.util;

import com.meli.freshWarehouse.dto.InboundOrderDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GenerateInboundOrder {

    public static InboundOrderDto validInboundOrderDto1() {

        InboundOrderDto inboundOrderDto = new InboundOrderDto();
        inboundOrderDto.setOrderDate(String.valueOf(LocalDate.parse("2022-08-09",
                DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

        inboundOrderDto.setRepresentativeId(1L);
        inboundOrderDto.setBatchStockList(GenerateBachStock.validBatchResponseDto());

        return inboundOrderDto;

    }


}
