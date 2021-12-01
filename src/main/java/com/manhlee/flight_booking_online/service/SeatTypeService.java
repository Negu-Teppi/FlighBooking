package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.SeatTypeEntity;
import com.manhlee.flight_booking_online.repository.SeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatTypeService {

    @Autowired
    private SeatTypeRepository seatTypeRepository;

    public List<SeatTypeEntity> getSeatType(String name){
        return seatTypeRepository.findBySeatType(name);
    }

    public void saveSeatType(SeatTypeEntity seatType){
        seatTypeRepository.save(seatType);
    }
}
