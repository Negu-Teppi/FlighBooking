package com.manhlee.flight_booking_online.entities;

import javax.persistence.*;

@Entity
@Table(name = "service_booking")
public class ServiceBookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookingDetail_id")
    private BookingDetailEntity bookingDetail;

    private int quantity;

    public ServiceBookingEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public BookingDetailEntity getBookingDetail() {
        return bookingDetail;
    }

    public void setBookingDetail(BookingDetailEntity bookingDetail) {
        this.bookingDetail = bookingDetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
