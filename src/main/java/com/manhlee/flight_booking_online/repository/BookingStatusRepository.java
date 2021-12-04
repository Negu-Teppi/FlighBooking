package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.BookingStatusEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingStatusRepository extends CrudRepository<BookingStatusEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM booking_status\n" +
            "where status!=?1")
    List<BookingStatusEntity> getStatusNot(String status);

    @Query(nativeQuery = true, value = "SELECT * FROM booking_status\n" +
            "where status=?1")
    List<BookingStatusEntity> getStatusEquals(String status);

    @Query(nativeQuery = true, value = "SELECT * FROM booking_status\n" +
            "where status=?1" )
    BookingStatusEntity getStatus(String status);
}
