package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.BookingStatusEntity;
import com.manhlee.flight_booking_online.repository.BookingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingStatusService {

    @Autowired
    private BookingStatusRepository statusRepository;

    public List<BookingStatusEntity> getBookingStatus(){
        return (List<BookingStatusEntity>) statusRepository.findAll();
    }

    public List<BookingStatusEntity> getBookingStatusNotEquals(String status){
        return statusRepository.getStatusNot(status);
    }

    public BookingStatusEntity getStatus(String status){
        return statusRepository.getStatusEquals(status);
    }
}
