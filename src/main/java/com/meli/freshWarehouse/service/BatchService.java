package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.repository.BatchRepo;
import com.meli.freshWarehouse.repository.ISectionRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BatchService implements IBatchService {

    private final BatchRepo batchRepo;
    private final ISectionRepo iSectionRepo;

    public BatchService(BatchRepo batchRepo, ISectionRepo iSectionRepo) {
        this.batchRepo = batchRepo;
        this.iSectionRepo = iSectionRepo;
    }

    @Override
    public List<Batch> saveAll(List<Batch> batchList) {
        return batchRepo.saveAll(batchList);
    }

    @Override
    public List<Batch> getBySectionAndDueDate(Long sectionId, Integer amountOfDays) {
        if (amountOfDays == null) {
            return batchRepo.getBySection(iSectionRepo.findById(sectionId).orElseThrow());
        }
        return batchRepo.getBySectionAndDueDate(iSectionRepo.findById(sectionId).orElseThrow(), LocalDate.now(),
                LocalDate.now().plusDays(amountOfDays));
    }

    @Override
    public List<Batch> getBySectionAndDueDate(String sectionName, Integer amountOfDays) {
        List<Batch> batchList = new ArrayList<>();
        List<Section> sectionList = iSectionRepo.findByName(sectionName);
        sectionList.forEach(section -> batchList.addAll(getBySectionAndDueDate(section.getId(), amountOfDays)));
        return batchList;
    }
}
