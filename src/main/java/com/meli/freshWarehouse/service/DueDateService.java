package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Representative;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.model.Warehouse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DueDateService implements IDueDateService{

    private final RepresentativeService representativeService;
    private final BatchService batchService;

    public DueDateService(RepresentativeService representativeService, BatchService batchService) {
        this.representativeService = representativeService;
        this.batchService = batchService;
    }

    @Override
    public List<Batch> getBatchesByExpiringDate(Long id) {
        Representative representative = representativeService.findById(id);
        Warehouse warehouse = representative.getWarehouse();
        Set<Section> section = warehouse.getListSection();
    }
}
