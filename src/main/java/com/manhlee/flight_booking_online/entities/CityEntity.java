package com.manhlee.flight_booking_online.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "city")
public class CityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false, name = "city_name")
    private String cityName;

    @Column(length = 25, name = "post_code")
    private String postCode;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AirportEntity> airports;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    public CityEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public List<AirportEntity> getAirports() {
        return airports;
    }

    public void setAirports(List<AirportEntity> airports) {
        this.airports = airports;
    }
}
