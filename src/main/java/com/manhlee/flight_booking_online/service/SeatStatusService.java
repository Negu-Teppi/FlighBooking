package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.SeatStatusEntity;
import com.manhlee.flight_booking_online.repository.SeatStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatStatusService {

    @Autowired
    private SeatStatusRepository seatStatusRepository;

    public List<SeatStatusEntity> getSeatStatus(String status){
        return seatStatusRepository.findByStatus(status);
    }

    public void saveSeatStatus(SeatStatusEntity seatStatus){
        seatStatusRepository.save(seatStatus);
    }
}
