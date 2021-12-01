package com.manhlee.flight_booking_online.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, length = 100)
    private String name;

    @Column(length = 1000)
    private String description;
    private double price;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ImageEntity> images;

    @OneToMany(mappedBy = "service")
    private List<ServiceBookingEntity> serviceBookings;

    public ServiceEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public List<ServiceBookingEntity> getServiceBookings() {
        return serviceBookings;
    }

    public void setServiceBookings(List<ServiceBookingEntity> serviceBookings) {
        this.serviceBookings = serviceBookings;
    }
}
