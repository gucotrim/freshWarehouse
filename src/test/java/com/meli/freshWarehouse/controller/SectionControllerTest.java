package com.meli.freshWarehouse.controller;

import com.meli.freshWarehouse.dto.SectionDto;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.service.SectionService;
import com.meli.freshWarehouse.util.GenerateSection;
import com.meli.freshWarehouse.util.GenerateSection02;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;


@ExtendWith(MockitoExtension.class)
public class SectionControllerTest {

    @InjectMocks
    SectionController controller;

    @Mock
    SectionService service;

    @Test
    public void saveNewSection_WhenSectionIsValid() {
        BDDMockito.when(service.save(ArgumentMatchers.any(SectionDto.class)))
                .thenReturn(GenerateSection.validSection1());

        SectionDto sectionDto = GenerateSection.validSectionDto1();

        ResponseEntity<Section> response = controller.saveNewSection(sectionDto);

        assertThat(response.getBody().getName()).isEqualTo(sectionDto.getName());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isPositive();

        Mockito.verify(service, Mockito.atLeastOnce()).save(ArgumentMatchers.any(SectionDto.class));
    }

    @Test
    public void getAllSection(){
        BDDMockito.when(service.getAllSection())
                .thenReturn(GenerateSection02.validSectionList());

        ResponseEntity<List<Section>> response = controller.getAllSection();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().get(0).getId()).isPositive();
        assertThat(response.getBody().size()).isGreaterThan(0);

        Mockito.verify(service, Mockito.atLeastOnce()).getAllSection();
    }

    @Test
    public void getSectionById() {
        BDDMockito.when(service.findById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateSection.validSection1());

        Section section = GenerateSection.validSection1();

        ResponseEntity<Section> response = controller.getSectionById(section.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(section.getName());
        assertThat(response.getBody().getId()).isEqualTo(section.getId());

        Mockito.verify(service, Mockito.atLeastOnce()).findById(ArgumentMatchers.anyLong());
    }

    @Test
    public void updateSection(){
        BDDMockito.when(service.updateSection(ArgumentMatchers.anyLong(),
                ArgumentMatchers.any(SectionDto.class)))
                .thenReturn(GenerateSection.validSection1());

        SectionDto section = GenerateSection.validSectionDto1();
        final Long sectionID = 1L;

        ResponseEntity<Section> response = controller.updateSection(sectionID, section);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(section.getName());
        assertThat(response.getBody().getId()).isEqualTo(sectionID);

        Mockito.verify(service, Mockito.atLeastOnce())
                .updateSection(ArgumentMatchers.anyLong(),
                        ArgumentMatchers.any(SectionDto.class));
    }

    @Test
    public void delete(){
        BDDMockito.willDoNothing().given(service).deleteSectionById(anyLong());

        final Long sectionID = 1L;

        ResponseEntity<Void> response = controller.delete(sectionID);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isEqualTo(null);

        Mockito.verify(service, Mockito.atLeastOnce())
                .deleteSectionById(ArgumentMatchers.anyLong());
    }


}
