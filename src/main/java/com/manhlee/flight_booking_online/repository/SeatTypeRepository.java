package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.SeatTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatTypeRepository extends CrudRepository<SeatTypeEntity, Integer> {

    @Query(value = "select * from seat_type s\n" +
            "where s.seatType=?1",nativeQuery = true)
    List<SeatTypeEntity> findBySeatType(String name);
}
