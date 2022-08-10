package com.meli.freshWarehouse.dto;

import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.Section;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Setter
@Getter
@Builder
public class WarehouseProductResponseDTO {
    private SectionDto section;
    private Long productId;
    private List<BatchListProductResponseDto> batchStockList;
}
