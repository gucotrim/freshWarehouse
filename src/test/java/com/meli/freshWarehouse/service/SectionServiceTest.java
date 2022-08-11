package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.SectionDto;
import com.meli.freshWarehouse.exception.SectionNotFoundException;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.repository.ISectionRepo;
import com.meli.freshWarehouse.util.GenerateSection;
import com.meli.freshWarehouse.util.GenerateWarehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SectionServiceTest {

    @InjectMocks
    private SectionService sectionService;

    @Mock
    private WarehouseService warehouseService;

    @Mock
    private ISectionRepo iSectionRepo;


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
        Section sectionSavedResponse = sectionService.save(sectionDto);

        assertThat(sectionSavedResponse).isNotNull();
        assertThat(sectionSavedResponse.getId()).isPositive();
        assertThat(sectionSavedResponse.getWarehouse().getId())
                .isEqualTo(GenerateWarehouse.validWarehouse1().getId());

    }

    @Test
    void getAllSection() {

        List<Section> sectionList = List.of(
                GenerateSection.validSection1(),
                GenerateSection.validSection2()
        );

        BDDMockito.when(iSectionRepo.findAll()).thenReturn(sectionList);

        List<Section> responseSection = sectionService.getAllSection();
        assertThat(responseSection).isNotNull();
        assertThat(responseSection.size()).isEqualTo(2);
        verify(iSectionRepo, Mockito.times(1)).findAll();

    }

    @Test
    void findById_When_IdIsValid() {

        BDDMockito.when(iSectionRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateSection.validSection1()));

        Section section = GenerateSection.validSection1();
        Section sectionResponse = sectionService.findById(section.getId());

        assertThat(sectionResponse).isNotNull();
        assertThat(sectionResponse.getId()).isEqualTo(section.getId());

    }

    @Test
    void returnNotFoundException_When_IdIsInvalid() {
        String expectedMessage = "Section not found by id: " + 3L;

        Exception exception = assertThrows(SectionNotFoundException.class,
                () -> sectionService.findById(3L).getId());

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void updateSection_When_IsSectionExists() {

        BDDMockito.when(iSectionRepo.save(ArgumentMatchers.any(Section.class)))
                .thenReturn(GenerateSection.updatedSection());

        BDDMockito.when(iSectionRepo.existsById(ArgumentMatchers.anyLong()))
                .thenReturn(true);

        BDDMockito.when(iSectionRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateSection.validSection1()));

        Section section = GenerateSection.validSection1();
        Section sectionUpdated = sectionService.findById(section.getId());

        SectionDto sectionDtoUpdated = SectionDto.builder()
                .name("New section")
                .availableSpace(33)
                .idWarehouse(2L)
                .build();

        Section responseSection = sectionService.updateSection(sectionUpdated.getId(), sectionDtoUpdated);

        assertThat(responseSection).isNotNull();
        assertThat(responseSection.getId()).isEqualTo(section.getId());
        assertThat(responseSection.getName()).isEqualTo(sectionDtoUpdated.getName());
        assertThat(responseSection.getWarehouse().getId()).isEqualTo(sectionDtoUpdated.getIdWarehouse());

        verify(iSectionRepo, Mockito.times(1)).save(sectionUpdated);

    }

    @Test
    void deleteSectionById_When_SectionExist() {

        BDDMockito.doNothing().when(iSectionRepo).deleteById(ArgumentMatchers.anyLong());

        BDDMockito.when(iSectionRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateSection.validSection1()));

        Section section = GenerateSection.validSection1();
        sectionService.deleteSectionById(section.getId());

        assertThatCode(
                () -> sectionService.deleteSectionById(section.getId())
        );

        verify(iSectionRepo, Mockito.atLeastOnce()).deleteById(section.getId());

    }

    @Test
    void returnSectionNotFoundException_whe_SectionById_NotExist() {

        BDDMockito.doNothing().when(iSectionRepo).deleteById(ArgumentMatchers.anyLong());

        String expectedMessage = "Section not found by id: " + 3L;

        Exception exception = assertThrows(SectionNotFoundException.class,
                () -> sectionService.deleteSectionById(3L));

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);

    }

    @ParameterizedTest(name = "[{index}] ==> Input value: ''{0}'' - Expected result: ''{1}''")
    @CsvSource({"true, true", "false, false"})
    void testMethodExistsById(boolean inputValue, boolean expectedResult) {

        BDDMockito.when(iSectionRepo.existsById(ArgumentMatchers.anyLong()))
                .thenReturn(inputValue);

        Section section = GenerateSection.validSection1();

        boolean responseExists = iSectionRepo.existsById(section.getId());
        assertThat(responseExists).isEqualTo(expectedResult);

    }
}