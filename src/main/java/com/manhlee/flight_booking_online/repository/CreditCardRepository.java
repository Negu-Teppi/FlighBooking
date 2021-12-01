package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.CreditCardEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCardEntity, Integer> {
}
