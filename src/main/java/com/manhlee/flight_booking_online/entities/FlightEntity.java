package com.manhlee.flight_booking_online.entities;

import com.manhlee.flight_booking_online.enums.FlightStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "flight")
public class FlightEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date depart;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date arrival;

    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private AircraftEntity aircraft;

    @ManyToOne
    @JoinColumn(name = "flight_route_id")
    private FlightRouteEntity flightRoute;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "flight_promotion_relationship",
            joinColumns = @JoinColumn(name = "flight_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "promotion_id",
                    referencedColumnName = "id"))
    private List<PromotionEntity> promotions;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    private OperationEntity operation;

    @OneToMany(mappedBy = "flight")
    private List<BookingDetailEntity> bookingDetails;

    @OneToMany(mappedBy = "flight")
    private List<AircraftSeatEntity> aircraftSeats;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @Transient
    private List<Integer> promotionIds;

    public FlightEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public AircraftEntity getAircraft() {
        return aircraft;
    }

    public void setAircraft(AircraftEntity aircraft) {
        this.aircraft = aircraft;
    }

    public FlightRouteEntity getFlightRoute() {
        return flightRoute;
    }

    public void setFlightRoute(FlightRouteEntity flightRoute) {
        this.flightRoute = flightRoute;
    }

    public List<PromotionEntity> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<PromotionEntity> promotions) {
        this.promotions = promotions;
    }

    public OperationEntity getOperation() {
        return operation;
    }

    public void setOperation(OperationEntity operation) {
        this.operation = operation;
    }

    public List<BookingDetailEntity> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetailEntity> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public List<AircraftSeatEntity> getAircraftSeats() {
        return aircraftSeats;
    }

    public void setAircraftSeats(List<AircraftSeatEntity> aircraftSeats) {
        this.aircraftSeats = aircraftSeats;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public List<Integer> getPromotionIds() {
        return promotionIds;
    }

    public void setPromotionIds(List<Integer> promotionIds) {
        this.promotionIds = promotionIds;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        FlightEntity flight = (FlightEntity) o;
//        return promotions.equals(flight.promotions);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(promotions);
//    }
}
