package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.repository.BatchRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService implements IBatchService {

    private final BatchRepo batchRepo;

    public BatchService(BatchRepo batchRepo) {
        this.batchRepo = batchRepo;
    }

    @Override
    public List<Batch> saveAll(List<Batch> batchList) {
        return batchRepo.saveAll(batchList);
    }
}
