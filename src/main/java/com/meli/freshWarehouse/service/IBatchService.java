package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.model.Batch;

import java.util.List;

public interface IBatchService {
    List<Batch> saveAll(List<Batch> batchList);
}
