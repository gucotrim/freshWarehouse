package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.model.Batch;

import java.util.List;

public interface IDueDateService {
    List<Batch> getBatchesByExpiringDate(Long id);
}
