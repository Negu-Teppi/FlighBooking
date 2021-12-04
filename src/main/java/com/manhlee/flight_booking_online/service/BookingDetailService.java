package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.BookingDetailEntity;
import com.manhlee.flight_booking_online.repository.BookingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public String getDirection(List<BookingDetailEntity> bookingDetails){
        if(bookingDetails.get(0).getFlight().getId()==bookingDetails.get(bookingDetails.size()-1).getFlight().getId()){
            return "OneTrip";
        }else {
            return "TwoTrip";
        }
    }

    public BookingDetailEntity getBookingDetail(int id){
        Optional<BookingDetailEntity> optional = bookingDetailRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public void save(BookingDetailEntity bookingDetailEntity){
        bookingDetailRepository.save(bookingDetailEntity);
    }
}
