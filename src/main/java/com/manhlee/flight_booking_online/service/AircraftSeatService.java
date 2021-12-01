package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.AircraftSeatEntity;
import com.manhlee.flight_booking_online.repository.AircraftSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftSeatService {

    @Autowired
    private AircraftSeatRepository aircraftSeatRepository;

    public List<AircraftSeatEntity> getAircraftSeats(){
        return (List<AircraftSeatEntity>) aircraftSeatRepository.findAll();
    }

    public AircraftSeatEntity getAircraftSeat(int id){
        Optional<AircraftSeatEntity> optional = aircraftSeatRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public void save(AircraftSeatEntity aircraftSeatEntity){
        aircraftSeatRepository.save(aircraftSeatEntity);
    }
}
