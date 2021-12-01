package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.SeatEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends CrudRepository<SeatEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM SEAT S\n" +
            "WHERE NUMBER NOT IN (SELECT S.NUMBER FROM SEAT S\n" +
            "INNER JOIN aircraft_seat AI ON S.ID=AI.seat_id\n" +
            "INNER JOIN aircraft A ON AI.aircraft_id= A.ID\n" +
            "WHERE  AI.aircraft_id=?1)")
    List<SeatEntity> findBySeatAvailable(int id);
}
