package com.manhlee.flight_booking_online.entities;

import com.manhlee.flight_booking_online.enums.BookingStatus;
import com.manhlee.flight_booking_online.enums.SeatStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "booking_status")
public class BookingStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @OneToMany(mappedBy = "bookingStatus")
    private List<BookingEntity> bookings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public List<BookingEntity> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingEntity> bookings) {
        this.bookings = bookings;
    }
}
