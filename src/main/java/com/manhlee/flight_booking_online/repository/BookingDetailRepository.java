package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.BookingDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDetailRepository extends CrudRepository<BookingDetailEntity, Integer> {

    List<BookingDetailEntity> findByBooking_Id(int id);
}
