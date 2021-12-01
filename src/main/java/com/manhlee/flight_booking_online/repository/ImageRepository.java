package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, ImageEntity> {

    List<ImageEntity> findByAircraft_Id(int id);

    List<ImageEntity> findByAirport_Id(int id);

    List<ImageEntity> findByPromotion_Id(int id);

    List<ImageEntity> findByService_Id(int id);
}
