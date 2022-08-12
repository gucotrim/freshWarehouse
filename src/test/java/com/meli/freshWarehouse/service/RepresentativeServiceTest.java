package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.RepresentativeDTO;
import com.meli.freshWarehouse.exception.RepresentativeNotFoundException;
import com.meli.freshWarehouse.model.Order;
import com.meli.freshWarehouse.model.Representative;
import com.meli.freshWarehouse.repository.RepresentativeRepo;
import com.meli.freshWarehouse.util.GenerateOrder;
import com.meli.freshWarehouse.util.GenerateRepresentative;
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
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RepresentativeServiceTest {

    @InjectMocks
    private RepresentativeService representativeService;

    @Mock
    WarehouseService warehouseService;

    @Mock
    private RepresentativeRepo representativeRepo;


    @BeforeEach
    public void setup() {
        BDDMockito.when(warehouseService.getWarehouseById(ArgumentMatchers.anyLong()))
                .thenReturn(GenerateWarehouse.validWarehouse1());

        BDDMockito.when(representativeRepo.save(ArgumentMatchers.any(Representative.class)))
                .thenReturn(GenerateRepresentative.validRepresentative1());
    }

    @Test
    void saveRepresentative_WhenSRepresentativeIsValid() {

        RepresentativeDTO representativeDTO = GenerateRepresentative.newRepresentativeDto1();
        Representative representativeSavedResponse = representativeService.save(representativeDTO);

        assertThat(representativeSavedResponse).isNotNull();
        assertThat(representativeSavedResponse.getId()).isPositive();
        assertThat(representativeSavedResponse.getWarehouse().getId())
                .isEqualTo(GenerateWarehouse.validWarehouse1().getId());

        verify(warehouseService, Mockito.times(1)).getWarehouseById(Mockito.anyLong());
        verify(representativeRepo, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void findAll() {
        Mockito.when(representativeRepo.findAll())
                .thenReturn(List.of(new Representative()));
        List<Representative> representativeList = representativeService.findAll();

        assertTrue(Objects.nonNull(representativeList));
        verify(representativeRepo, Mockito.times(1)).findAll();
    }

    @Test
    void update(){
        BDDMockito.when(representativeRepo.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateRepresentative.validRepresentative1()));

        Mockito.when(warehouseService.getWarehouseById(Mockito.anyLong()))
                .thenReturn(GenerateWarehouse.validWarehouse1());

        RepresentativeDTO representativeDTO = RepresentativeDTO.builder()
                .name("New Name Representative Test")
                .warehouseId(1L)
                .build();

        representativeService.update(1L, representativeDTO);

        verify(representativeRepo, Mockito.times(1)).save(Mockito.any(Representative.class));
        verify(representativeRepo, Mockito.times(1)).findById(Mockito.anyLong());
        verify(warehouseService, Mockito.times(1)).getWarehouseById(Mockito.anyLong());

        assertEquals(representativeService.findById(1L).getName(), "New Name Representative Test");
    }

    @Test
    void deleteById_when_RepresentativeExist() {

        BDDMockito.doNothing().when(representativeRepo).deleteById(ArgumentMatchers.anyLong());

        BDDMockito.when(representativeRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateRepresentative.validRepresentative1()));

        Representative representative = GenerateRepresentative.validRepresentative1();
        representativeService.delete(representative.getId());

        assertThatCode(
                () -> representativeService.delete(representative.getId())
        );

        verify(representativeRepo, Mockito.atLeastOnce()).deleteById(representative.getId());

    }


    @Test
    void returnRepresentativeNotFoundException_when_RepresentativeById_NotExist() {

        BDDMockito.doNothing().when(representativeRepo).deleteById(ArgumentMatchers.anyLong());

        String expectedMessage = "No representative was found with this id: " + 3L;

        Exception exception = assertThrows(RepresentativeNotFoundException.class,
                () -> representativeService.delete(3L));

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void findById() {
        BDDMockito.when(representativeRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(GenerateRepresentative.validRepresentative1()));

        Representative representative = GenerateRepresentative.validRepresentative1();
        Representative representativeResponse = representativeService.findById(representative.getId());

        assertThat(representativeResponse).isNotNull();
        assertThat(representativeResponse.getId()).isEqualTo(representative.getId());
    }


    @ParameterizedTest(name = "[{index}] ==> Input value: ''{0}'' - Expected result: ''{1}''")
    @CsvSource({"true, true", "false, false"})
    void existsById(boolean inputValue, boolean expectedResult) {

        BDDMockito.when(representativeRepo.existsById(ArgumentMatchers.anyLong()))
                .thenReturn(inputValue);

        Representative representative = GenerateRepresentative.validRepresentative1();

        boolean responseExists = representativeService.existsById(representative.getId());
        assertThat(responseExists).isEqualTo(expectedResult);
    }
}