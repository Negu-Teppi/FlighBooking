package com.manhlee.flight_booking_online.entities;

import com.manhlee.flight_booking_online.enums.CommonStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "airport")
public class AirportEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false, name = "airport_name")
    private String airportName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @OneToMany(mappedBy = "airport", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ImageEntity> images;

    @OneToMany(mappedBy = "departure")
    private List<FlightRouteEntity> departureFlightRoutes;

    @OneToMany(mappedBy = "destination")
    private List<FlightRouteEntity> destinationFlightRoutes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonStatus status;

    public AirportEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public List<FlightRouteEntity> getDepartureFlightRoutes() {
        return departureFlightRoutes;
    }

    public void setDepartureFlightRoutes(List<FlightRouteEntity> departureFlightRoutes) {
        this.departureFlightRoutes = departureFlightRoutes;
    }

    public List<FlightRouteEntity> getDestinationFlightRoutes() {
        return destinationFlightRoutes;
    }

    public void setDestinationFlightRoutes(List<FlightRouteEntity> destinationFlightRoutes) {
        this.destinationFlightRoutes = destinationFlightRoutes;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }
}
