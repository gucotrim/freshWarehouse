package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.model.Batch;
import com.meli.freshWarehouse.repository.BatchRepo;
import com.meli.freshWarehouse.util.GenerateBachStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BatchServiceTest {

    @InjectMocks
    private BatchService batchService;

    @Spy
    private BatchRepo batchRepo;

   @BeforeEach
   public void setUp(){
       MockitoAnnotations.openMocks(this);
   }

   @Test
    public void saveAll(){

       List<Batch> batchList = List.of(GenerateBachStock.newBatch2());

       BDDMockito.when(batchRepo.saveAll(batchList))
               .thenReturn(batchList);

       batchService.saveAll(batchList);
       assertThat(batchList).isNotNull();
       verify(batchRepo, Mockito.times(1)).saveAll(batchList);
   }
}