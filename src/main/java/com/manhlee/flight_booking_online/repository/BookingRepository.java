package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.BookingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<BookingEntity, Integer> {

    @Query("SELECT f FROM BookingEntity f LEFT JOIN FETCH f.bookingDetails")
    List<BookingEntity> getBookingsBookingDetailLazy();

    @Query("SELECT b from BookingEntity b " +
            "where CONCAT(b.fullName, ' ', b.bookingNumber) like %?1%")
    List<BookingEntity> searchBooking(String query);

    @Query("SELECT b from BookingEntity b left join fetch b.payments where b.id= :id")
    BookingEntity getBookingPaymentLazy(@Param("id") int id);

    List<BookingEntity> findByBookingDateBetweenOrderByBookingDate(Date startDate, Date endDate);
}
