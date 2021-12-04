package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.AirportEntity;
import com.manhlee.flight_booking_online.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public List<AirportEntity> getAirports(){
        return (List<AirportEntity>) airportRepository.findAll();
    }

    public AirportEntity getAirport(int id){
        Optional<AirportEntity> optional = airportRepository.findById(id);
        if(optional.isPresent()){
            return  optional.get();
        }
        return null;
    }

    public List<AirportEntity> getDestination(int id){
        return airportRepository.getDestinationByDepartureId(id);
    }
    public void save(AirportEntity airport){
        airportRepository.save(airport);
    }

    public void deleteAirport(int id){
        airportRepository.deleteById(id);
    }

    public Page<AirportEntity> getAircraftPages(Pageable pageable){
        return airportRepository.findAll(pageable);
    }
}
