package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.ServiceBookingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceBookingRepository extends CrudRepository<ServiceBookingEntity, Integer> {

    List<ServiceBookingEntity> findByBookingDetail_Id(int id);


}
