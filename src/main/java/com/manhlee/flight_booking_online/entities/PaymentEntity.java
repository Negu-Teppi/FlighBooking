package com.manhlee.flight_booking_online.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Column(length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "creditCard_id")
    private CreditCardEntity creditCard;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CreditCardEntity getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardEntity creditCard) {
        this.creditCard = creditCard;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }


}
