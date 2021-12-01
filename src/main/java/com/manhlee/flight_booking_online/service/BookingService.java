package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.BookingEntity;
import com.manhlee.flight_booking_online.repository.BookingRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<BookingEntity> getBookings() {
        return (List<BookingEntity>) bookingRepository.findAll();
    }

    public List<BookingEntity> searchBooking(String query) {
        return bookingRepository.searchBooking(query);
    }

    public List<BookingEntity> getBookingsBookingDetailLazy() {
        return bookingRepository.getBookingsBookingDetailLazy();
    }

    public BookingEntity getBooking(int id) {
        Optional<BookingEntity> optional = bookingRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public BookingEntity getBookingPaymentLazy(int id) {
        return bookingRepository.getBookingPaymentLazy(id);
    }

    public void save(BookingEntity booking) {
        bookingRepository.save(booking);
    }

    public List<BookingEntity> searchByBookingDate(Date startDate, Date endDate){
        return bookingRepository.findByBookingDateBetweenOrderByBookingDate(startDate, endDate);
    }
}
