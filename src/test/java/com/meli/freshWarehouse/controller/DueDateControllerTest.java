package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.DueDateResponseDto;
import com.meli.freshWarehouse.service.BatchService;
import com.meli.freshWarehouse.util.GenerateDueDateResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DueDateControllerTest {

    @InjectMocks
    private DueDateController dueDateController;

    @Mock
    BatchService batchService;

    @Test
    void getBatchesByExpiringDateTest() {
        BDDMockito.when(batchService.getBySectionAndDueDate(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Integer.class)))
                                                            .thenReturn(GenerateDueDateResponseDto.validDueDateResponseDto1());

        ResponseEntity<List<DueDateResponseDto>> dueDateResponseDtoReturned = dueDateController.getBatchesByExpiringDate(1L,20);

        assertThat(dueDateResponseDtoReturned.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(dueDateResponseDtoReturned.getBody()).isNotNull();
        verify(batchService).getBySectionAndDueDate(1L, 20);
    }

    @Test
    void getBatchesByExpiringDateAndSectionNameTest() {
        BDDMockito.when(batchService.getBySectionAndDueDate(ArgumentMatchers.any(String.class), ArgumentMatchers.any(Integer.class)))
                .thenReturn(GenerateDueDateResponseDto.validDueDateResponseDto1());

        ResponseEntity<List<DueDateResponseDto>> dueDateResponseDtoReturned = dueDateController.getBatchesByExpiringDateAndSectionName("Fresh",20);

        assertThat(dueDateResponseDtoReturned.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(dueDateResponseDtoReturned.getBody()).isNotNull();
        verify(batchService).getBySectionAndDueDate("Fresh", 20);
    }

}
