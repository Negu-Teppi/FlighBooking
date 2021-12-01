package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.PromotionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends CrudRepository<PromotionEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM promotion\n" +
            "order by createDate asc;")
    List<PromotionEntity> getPromotions();
}
