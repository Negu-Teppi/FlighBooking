package com.manhlee.flight_booking_online.entities;

import com.manhlee.flight_booking_online.enums.CommonStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("checkstyle:LineLength")
@Entity
@Table(name = "aircraft")
public class AircraftEntity implements Serializable {

    @SuppressWarnings("checkstyle:JavadocVariable")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:JavadocVariable"})
    @Column(length = 100, nullable = false)
    private String name;

    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:JavadocVariable"})
    @Column(length = 25)
    private String number;

    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:JavadocVariable"})
    @Column(length = 10)
    private String rowNumber;

    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:JavadocVariable"})
    @Column(length = 10)
    private String colNumber;

    @SuppressWarnings("checkstyle:JavadocVariable")
    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ImageEntity> images;

    @SuppressWarnings("checkstyle:JavadocVariable")
    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL)
    private List<FlightEntity> flights;

    @SuppressWarnings("checkstyle:JavadocVariable")
    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL)
    private List<AircraftSeatEntity> aircraftSeats;

    @SuppressWarnings("checkstyle:JavadocVariable")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonStatus status;

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public AircraftEntity() {
    }

    @SuppressWarnings({"checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public int getId() {
        return id;
    }

    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:FinalParameters", "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public void setId(int id) {
        this.id = id;
    }

    @SuppressWarnings({"checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public String getName() {
        return name;
    }

    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:FinalParameters", "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings({"checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public String getNumber() {
        return number;
    }

    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:FinalParameters", "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public void setNumber(String number) {
        this.number = number;
    }

    @SuppressWarnings({"checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public String getRowNumber() {
        return rowNumber;
    }

    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:FinalParameters", "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }

    @SuppressWarnings({"checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public String getColNumber() {
        return colNumber;
    }

    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:FinalParameters", "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public void setColNumber(String colNumber) {
        this.colNumber = colNumber;
    }

    @SuppressWarnings({"checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public List<ImageEntity> getImages() {
        return images;
    }

    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:FinalParameters", "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    @SuppressWarnings({"checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public List<FlightEntity> getFlights() {
        return flights;
    }

    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:FinalParameters", "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public void setFlights(List<FlightEntity> flights) {
        this.flights = flights;
    }

    @SuppressWarnings({"checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public List<AircraftSeatEntity> getAircraftSeats() {
        return aircraftSeats;
    }

    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:FinalParameters", "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public void setAircraftSeats(List<AircraftSeatEntity> aircraftSeats) {
        this.aircraftSeats = aircraftSeats;
    }

    @SuppressWarnings({"checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public CommonStatus getStatus() {
        return status;
    }

    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:FinalParameters", "checkstyle:DesignForExtension", "checkstyle:MissingJavadocMethod"})
    public void setStatus(CommonStatus status) {
        this.status = status;
    }
}
