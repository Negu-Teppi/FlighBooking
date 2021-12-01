package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.BookingDetailEntity;
import com.manhlee.flight_booking_online.repository.BookingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingDetailService {

    @Autowired
    private BookingDetailRepository bookingDetailRepository;

    public List<BookingDetailEntity> getBookingDetails(){
        return (List<BookingDetailEntity>) bookingDetailRepository.findAll();
    }

    public List<BookingDetailEntity> getBookingDetailsByBooking(int id){
        return bookingDetailRepository.findByBooking_Id(id);
    }

}
