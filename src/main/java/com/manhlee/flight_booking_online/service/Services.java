package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.ServiceEntity;
import com.manhlee.flight_booking_online.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Services {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<ServiceEntity> getServices(){
        return (List<ServiceEntity>) serviceRepository.findAll();
    }

    public void save(ServiceEntity service){
        serviceRepository.save(service);
    }

    public ServiceEntity getService(int id){
        Optional<ServiceEntity> optional = serviceRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
