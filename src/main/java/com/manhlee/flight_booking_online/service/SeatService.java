package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.SeatEntity;
import com.manhlee.flight_booking_online.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<SeatEntity> getSeats(int id){
        return (List<SeatEntity>) seatRepository.findBySeatAvailable(id);
    }
    public void save(SeatEntity seat){
        seatRepository.save(seat);
    }
}
