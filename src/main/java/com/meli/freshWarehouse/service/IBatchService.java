package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.model.Batch;

import java.util.List;

public interface IBatchService {
    List<Batch> saveAll(List<Batch> batchList);
    List<Batch> getBySectionAndDueDate(Long sectionId, Integer amountOfDays);
    List<Batch> getBySectionAndDueDate(String sectionName, Integer amountOfDays);
}
