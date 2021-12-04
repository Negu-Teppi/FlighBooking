package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.AircraftEntity;
import com.manhlee.flight_booking_online.entities.ServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<ServiceEntity, Integer> {
    Page<ServiceEntity> findAll(Pageable pageable);

}
