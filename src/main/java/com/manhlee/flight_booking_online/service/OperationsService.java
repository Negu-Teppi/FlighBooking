package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.OperationEntity;
import com.manhlee.flight_booking_online.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationsService {

    @Autowired
    private OperationsRepository operationsRepository;

    public List<OperationEntity> getOperations(){
        return (List<OperationEntity>) operationsRepository.findAll();
    }


}
