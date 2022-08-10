package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.model.Warehouse;
import com.meli.freshWarehouse.repository.ISectionRepo;
import com.meli.freshWarehouse.repository.WarehouseRepo;
import com.meli.freshWarehouse.util.GenerateSection;
import com.meli.freshWarehouse.util.GenerateWarehouse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@Log4j2
class SectionServiceTest {

    @InjectMocks
    private SectionService sectionService;

    @InjectMocks
    private WarehouseService warehouseService;

    @Mock
    private ISectionRepo iSectionRepo;

    @Mock
    private WarehouseRepo warehouseRepo;

    @BeforeEach
    public void setup() {
        BDDMockito.when(this.warehouseRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateWarehouse.validWarehouse1()));

        BDDMockito.when(this.iSectionRepo.save(ArgumentMatchers.any(Section.class)))
                .thenReturn(GenerateSection.validSection1());
    }


    @Test
    void saveSection_WhenSectionsValid() {


        Section sectionSaved = sectionService.save(GenerateSection.newSection1());

        assertThat(sectionSaved).isNotNull();


    }

    @Test
    void getAllSection() {
    }

    @Test
    void findById() {
    }

    @Test
    void updateSection() {
    }

    @Test
    void deleteSectionById() {
    }

    @Test
    void existById() {
    }
}