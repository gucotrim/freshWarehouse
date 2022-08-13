package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.DueDateResponseDto;
import com.meli.freshWarehouse.exception.EmptySectionListException;
import com.meli.freshWarehouse.exception.SectionIdNotFoundException;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.repository.DueDateRepo;
import com.meli.freshWarehouse.repository.ISectionRepo;
import com.meli.freshWarehouse.util.GenerateDueDateResponseDto;
import com.meli.freshWarehouse.util.GenerateSection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BachServiceTest {
    @InjectMocks
    private BatchService batchService;

    @Mock
    private DueDateRepo dueDateRepo;

    @Mock
    private ISectionRepo iSectionRepo;

    @Test
    void getBySectionAndDueDate_WhenSectionId_IsValid_AndAmountOfDays_IsNull() {

        BDDMockito.when(iSectionRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateSection.validFreshSection()));

        BDDMockito.when(dueDateRepo.getBySection(ArgumentMatchers.any(Section.class)))
                .thenReturn(GenerateDueDateResponseDto.validDueDateResponseDto1());


        List<DueDateResponseDto> responseDueDateDtoList = batchService.getBySectionAndDueDate(3L, null);

        assertThat(responseDueDateDtoList).isNotNull();
        assertThat(responseDueDateDtoList).size().isEqualTo(1);
        verify(iSectionRepo, Mockito.times(1)).findById(3L);
    }

    @Test
    void getBySectionAndDueDate_WhenSectionId_IsNotValid_AndAmountOfDays_IsNull() {

        BDDMockito.when(iSectionRepo.findById(ArgumentMatchers.anyLong()))
                .thenThrow(SectionIdNotFoundException.class);

        Exception exception = assertThrows(SectionIdNotFoundException.class, () -> batchService.getBySectionAndDueDate(50L, null));

        assertThat(exception).isInstanceOf(SectionIdNotFoundException.class);
    }

    @Test
    void getBySectionAndDueDate_WhenSectionId_IsValid_AndAmountOfDays_Exists() {

        BDDMockito.when(iSectionRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(GenerateSection.validFreshSection()));

        BDDMockito.when(iSectionRepo.findByName(ArgumentMatchers.anyString()))
                .thenReturn(new ArrayList<>(GenerateSection.validFreshSectionList()));


        List<DueDateResponseDto> responseDueDateDtoList = batchService.getBySectionAndDueDate("FS", 10);

        assertThat(responseDueDateDtoList).isNotNull();
        verify(iSectionRepo, Mockito.times(1)).findByName("Fresh");
    }
    
}
