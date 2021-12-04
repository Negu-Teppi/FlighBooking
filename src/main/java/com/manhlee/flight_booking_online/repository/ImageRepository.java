package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.ImageEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {

    List<ImageEntity> findByAircraft_Id(int id);

    List<ImageEntity> findByAirport_Id(int id);

    List<ImageEntity> findByPromotion_Id(int id);

    List<ImageEntity> findByService_Id(int id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from image i where i.id = :id")
    public void deleteByImageId(@Param("id") int id);
}
