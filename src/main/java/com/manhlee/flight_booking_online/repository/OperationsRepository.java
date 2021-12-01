package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.OperationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationsRepository extends CrudRepository<OperationEntity, Integer> {
}
