package com.manhlee.flight_booking_online.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "passenger")
public class PassengerEntity extends Personal implements Serializable {

    @Column(length = 25, unique = true)
    private String identCode;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    private List<BookingDetailEntity> bookingDetails;

    public PassengerEntity() {
    }

    public String getIdentCode() {
        return identCode;
    }

    public void setIdentCode(String identCode) {
        this.identCode = identCode;
    }

    public List<BookingDetailEntity> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetailEntity> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }
}
