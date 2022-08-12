package com.meli.freshWarehouse.dto;

import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.Section;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseProductResponseDTO {
    private SectionDto section;
    private Long productId;
    private List<BatchListProductResponseDto> batchStockList;
}
