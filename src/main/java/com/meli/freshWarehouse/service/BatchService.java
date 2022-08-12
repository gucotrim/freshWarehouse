package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.DueDateResponseDto;
import com.meli.freshWarehouse.exception.EmptySectionListException;
import com.meli.freshWarehouse.exception.InvalidSectionNameException;
import com.meli.freshWarehouse.exception.SectionIdNotFoundException;
import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.repository.BatchRepo;
import com.meli.freshWarehouse.repository.DueDateRepo;
import com.meli.freshWarehouse.repository.ISectionRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
            Section sectionById = iSectionRepo.findById(sectionId).orElseThrow(() -> new SectionIdNotFoundException("Couldn't find any Section by this ID"));
            return dueDateRepo.getBySection(sectionById);
        }
        return dueDateRepo.getBySectionAndDueDate(iSectionRepo.findById(sectionId).orElseThrow(() -> new SectionIdNotFoundException("Couldn't find any Section by this ID")),
                                                    LocalDate.now(), LocalDate.now().plusDays(amountOfDays));
        }

    @Override
    public List<DueDateResponseDto> getBySectionAndDueDate(String sectionName, Integer amountOfDays) {
        List<DueDateResponseDto> batchList = new ArrayList<>();
        Set<Section> sectionList = new HashSet<>();
        switch (sectionName) {
            case "FS":
                sectionList.addAll(iSectionRepo.findByName("Fresh"));
                sectionList.forEach(section -> batchList.addAll(getBySectionAndDueDate(section.getId(), amountOfDays)));
                if (sectionList.isEmpty()) {
                    throw new EmptySectionListException("Couldn't find any products in this Section");
                }

                return batchList;
            case "RF":
                sectionList.addAll(iSectionRepo.findByName("Refrigerated"));
                sectionList.forEach(section -> batchList.addAll(getBySectionAndDueDate(section.getId(), amountOfDays)));
                if (sectionList.isEmpty()) {
                    throw new EmptySectionListException("Couldn't find any products in this Section");
                }

                return batchList;
            case "FF":
                sectionList.addAll(iSectionRepo.findByName("Frozen"));
                sectionList.forEach(section -> batchList.addAll(getBySectionAndDueDate(section.getId(), amountOfDays)));
                if (sectionList.isEmpty()) {
                    throw new EmptySectionListException("Couldn't find any products in this Section");
                }

                return batchList;
            default:
                throw new InvalidSectionNameException("Please, enter one of the options: FS, RF or FF");
        }
    }
}
