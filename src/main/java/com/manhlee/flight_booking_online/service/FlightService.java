package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.FlightEntity;
import com.manhlee.flight_booking_online.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<FlightEntity> getFlights(){
        return (List<FlightEntity>) flightRepository.findAll();
    }

    public void save(FlightEntity flight){
        flightRepository.save(flight);
    }

    public FlightEntity getFlight(int id){
        Optional<FlightEntity> optional = flightRepository.findById(id);
        if(optional.isPresent()){
            return  optional.get();
        }
        return null;
    }

    public FlightEntity getFlightPromotion(int id){
        return flightRepository.getFlightById(id);
    }

    public List<FlightEntity> getFlightByAircraftId(int id){
        return flightRepository.findByAircraft_Id(id);
    }

    public List<FlightEntity> getFlightsPromotion(){
        return flightRepository.getFlightsPromotionLazy();
    }
}
