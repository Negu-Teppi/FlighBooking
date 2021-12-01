package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.CreditCardEntity;
import com.manhlee.flight_booking_online.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCardEntity getCreditCard(int id){
        Optional<CreditCardEntity> optional = creditCardRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public void save(CreditCardEntity creditCard){
        creditCardRepository.save(creditCard);
    }

}
