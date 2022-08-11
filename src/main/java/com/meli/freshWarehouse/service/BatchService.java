package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.DueDateResponseDto;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.repository.BatchRepo;
import com.meli.freshWarehouse.repository.DueDateRepo;
import com.meli.freshWarehouse.repository.ISectionRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BatchService implements IBatchService {

    private final BatchRepo batchRepo;
    private final ISectionRepo iSectionRepo;
    private final DueDateRepo dueDateRepo;

    public BatchService(BatchRepo batchRepo, ISectionRepo iSectionRepo, DueDateRepo dueDateRepo) {
        this.batchRepo = batchRepo;
        this.iSectionRepo = iSectionRepo;
        this.dueDateRepo = dueDateRepo;
    }

    @Override
    public List<Batch> saveAll(List<Batch> batchList) {
        return batchRepo.saveAll(batchList);
    }

    @Override
    public List<DueDateResponseDto> getBySectionAndDueDate(Long sectionId, Integer amountOfDays) {
        if (amountOfDays == null) {
            return dueDateRepo.getBySection(iSectionRepo.findById(sectionId).orElseThrow());
        }
        return dueDateRepo.getBySectionAndDueDate(iSectionRepo.findById(sectionId).orElseThrow(), LocalDate.now(),
                LocalDate.now().plusDays(amountOfDays));
    }

    @Override
    public List<DueDateResponseDto> getBySectionAndDueDate(String sectionName, Integer amountOfDays) {
        List<DueDateResponseDto> batchList = new ArrayList<>();
        List<Section> sectionList = new ArrayList<>();
        switch (sectionName) {
            case "FS":
                sectionList = iSectionRepo.findByName("Fresh");
                sectionList.forEach(section -> batchList.addAll(getBySectionAndDueDate(section.getId(), amountOfDays)));
                return batchList;
            case "RF":
                sectionList = iSectionRepo.findByName("Refrigerated");
                sectionList.forEach(section -> batchList.addAll(getBySectionAndDueDate(section.getId(), amountOfDays)));
                return batchList;
            case "FF":
                sectionList = iSectionRepo.findByName("Frozen");
                sectionList.forEach(section -> batchList.addAll(getBySectionAndDueDate(section.getId(), amountOfDays)));
                return batchList;
            default:
                throw new RuntimeException("The section options are: FS, RF or FF");
        }
    }
}
