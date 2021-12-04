package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.FlightEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<FlightEntity, Integer> {

    @Query("SELECT f FROM FlightEntity f LEFT JOIN FETCH f.promotions")
    List<FlightEntity> getFlightsPromotionLazy();

    @Query("SELECT f FROM FlightEntity f LEFT JOIN FETCH f.promotions where f.id=(:id)")
    FlightEntity getFlightById(@Param("id") int id);

    List<FlightEntity> findByAircraft_Id(int id);

}
