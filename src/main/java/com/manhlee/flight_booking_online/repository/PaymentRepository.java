package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity,Integer> {
    List<PaymentEntity> findByBooking_Id(int id);
}
