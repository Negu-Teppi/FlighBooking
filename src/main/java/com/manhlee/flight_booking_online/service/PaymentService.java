package com.manhlee.flight_booking_online.service;

import com.manhlee.flight_booking_online.entities.PaymentEntity;
import com.manhlee.flight_booking_online.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<PaymentEntity> getPaymentsByBookingId(int id){
        return paymentRepository.findByBooking_Id(id);
    }

    public void save(PaymentEntity payment){
        paymentRepository.save(payment);
    }
}
