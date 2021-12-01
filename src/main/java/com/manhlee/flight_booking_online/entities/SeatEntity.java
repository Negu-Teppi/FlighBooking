package com.manhlee.flight_booking_online.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "seat")
public class SeatEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 5, name = "number")
    private String seatNumber;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    private Set<AircraftSeatEntity> aircraftSeats;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    private List<BookingDetailEntity> bookingDetails;

    public SeatEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Set<AircraftSeatEntity> getAircraftSeats() {
        return aircraftSeats;
    }

    public void setAircraftSeats(Set<AircraftSeatEntity> aircraftSeats) {
        this.aircraftSeats = aircraftSeats;
    }

    public List<BookingDetailEntity> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetailEntity> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }
}
