package com.manhlee.flight_booking_online.repository;

import com.manhlee.flight_booking_online.entities.AirportEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends CrudRepository<AirportEntity, Integer> {

    @Query(nativeQuery = true, value = "select * from airport a\n" +
            "inner join \n" +
            "(select c.id, c.name 'City', c.post_code 'Postal Code of City', ct.name 'Country', ct.postCode 'Postal code of Country' from city c\n" +
            "inner join country ct on c.country_id =  ct.id\n" +
            "order by c.id asc) as T\n" +
            "on a.city_id=T.id\n" +
            "order by a.id asc")
    List<AirportEntity> allAirport();

    @Query(nativeQuery = true, value = "SELECT * FROM airport A\n" +
            "WHERE airport_name NOT IN (SELECT A.airport_name FROM airport A\n" +
            "INNER JOIN flight_route F ON F.departure_id = A.id\n" +
            "where F.departure_id=?1 \n" +
            "group by A.airport_name)")
    List<AirportEntity> getDestinationByDepartureId(int id);
}
