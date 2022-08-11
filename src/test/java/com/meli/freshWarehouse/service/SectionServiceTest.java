package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.SectionDto;
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
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SectionServiceTest {

    @InjectMocks
    private SectionService sectionService;

    @Mock
    private WarehouseService warehouseService;

    @Mock
    private ISectionRepo iSectionRepo;

    @Mock
    private WarehouseRepo warehouseRepo;

    @BeforeEach
    public void setup() {

        BDDMockito.when(warehouseService.getWarehouseById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateWarehouse.validWarehouse1());

        BDDMockito.when(iSectionRepo.save(ArgumentMatchers.any(Section.class)))
                .thenReturn(GenerateSection.validSection1());
    }

    @Test
    void saveSection_WhenSectionsIsValid() {

        SectionDto sectionDto = GenerateSection.newSection1();
        Section sectionSaved = sectionService.save(sectionDto);

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