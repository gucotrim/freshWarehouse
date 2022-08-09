package com.meli.freshWarehouse.repository;

import com.meli.freshWarehouse.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundOrderRepo extends JpaRepository <InboundOrder, Long>{


}
