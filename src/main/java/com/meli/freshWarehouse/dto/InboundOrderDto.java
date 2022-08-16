package com.meli.freshWarehouse.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InboundOrderDto {

        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "The valid Date is: 'yyyy-MM-dd'")
        @NotNull(message = "The field is not null.")
        private String orderDate;

        @Positive
        @NotNull(message = "The field is not null.")
        private Long sectionId;

        @Positive
        @NotNull(message = "The field is not null.")
        private Long representativeId;

        @NotEmpty(message = "The batch is not empty.")
        private Set<@Valid BatchDto> batchStockList;
}
