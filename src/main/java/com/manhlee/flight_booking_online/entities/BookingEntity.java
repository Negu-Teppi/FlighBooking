package com.manhlee.flight_booking_online.entities;

import com.manhlee.flight_booking_online.enums.BookingStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking")
@AttributeOverride(name = "email", column = @Column(name="email"))
public class BookingEntity extends Personal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bookingDate;

    @Column(unique = true)
    private int bookingNumber;

    @ManyToOne
    @JoinColumn(name = "booking_status_id")
    private BookingStatusEntity bookingStatus;

    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
    private List<BookingDetailEntity> bookingDetails;

    @OneToMany(mappedBy = "booking")
    private List<PaymentEntity> payments;

    @Column(length = 100)
    private String email;

    private double totalPrice;

    public BookingEntity() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public BookingStatusEntity getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatusEntity bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public List<BookingDetailEntity> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetailEntity> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentEntity> payments) {
        this.payments = payments;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
