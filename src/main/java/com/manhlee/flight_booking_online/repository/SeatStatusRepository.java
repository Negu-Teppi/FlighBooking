package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.SeatStatusEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatStatusRepository extends CrudRepository<SeatStatusEntity, Integer> {

    @Query(value = "select * from seat_status s where s.status = ?1",nativeQuery = true)
    List<SeatStatusEntity> findByStatus(String status);
}
