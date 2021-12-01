package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.AircraftSeatEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftSeatRepository extends CrudRepository<AircraftSeatEntity, Integer> {
}
