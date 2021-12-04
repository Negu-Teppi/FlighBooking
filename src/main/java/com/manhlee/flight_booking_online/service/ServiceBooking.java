package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.ServiceBookingEntity;
import com.manhlee.flight_booking_online.repository.ServiceBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBooking {

    @Autowired
    private ServiceBookingRepository serviceBookingRepository;

    public List<ServiceBookingEntity> getServiceBookings(){
        return (List<ServiceBookingEntity>) serviceBookingRepository.findAll();
    }

    public List<ServiceBookingEntity> getServiceBookingByBookingDetail(int id) {
        return serviceBookingRepository.findByBookingDetail_Id(id);
    }

    public void delete(int id) {
        serviceBookingRepository.deleteById(id);
    }
}
