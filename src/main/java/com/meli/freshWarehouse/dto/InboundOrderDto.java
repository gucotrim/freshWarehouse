package com.meli.freshWarehouse.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
public class InboundOrderDto {

        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "The valid Date is: 'yyyy-MM-dd'")
        @NotNull(message = "The field is not null.")
        private String orderDate;
        private Long sectionId;
        private Long representativeId;
        private Set<@Valid BatchDto> batchStockList;
}
