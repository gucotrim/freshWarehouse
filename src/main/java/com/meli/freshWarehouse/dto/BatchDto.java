package com.meli.freshWarehouse.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class BatchDto {

    @Positive
    @NotNull(message = "The field is not null.")
    private Long productId;

    @NotNull(message = "The field is not null.")
    private Float currentTemperature;

    @NotNull
    private Float minimumTemperature;

    @NotNull
    @Positive
    private Integer initialQuantity;

    @Positive
    @NotNull(message = "The field is not null.")
    private Integer currentQuantity;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "The valid Date is: 'yyyy-MM-dd'")
    @NotBlank(message = "The field is not Blank.")
    private String manufacturingDate;

    @Pattern(regexp = "^(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2})$",
            message = "The valid Date is: 'yyyy-MM-ddTHH:mm:ss'")
    @NotBlank(message = "The field is not Blank.")
    private String manufacturingTime;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "The valid Date is: 'yyyy-MM-dd'")
    @NotBlank(message = "The field is not Blank.")
    private String dueDate;
}
