package com.manhlee.flight_booking_online.controller;

import com.manhlee.flight_booking_online.entities.*;
import com.manhlee.flight_booking_online.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.text.DecimalFormat;
import java.util.*;


@Controller
@RequestMapping("api/")
public class AjaxController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private Services services;

    @Autowired
    private BookingDetailService bookingDetailService;

    @Autowired
    private BookingStatusService bookingStatusService;

    @RequestMapping("/getSeats")
    public String getSeats(@RequestParam("aircraftId") int id, Model model) {
        List<SeatEntity> getSeats = seatService.getSeats(id);
        Map<Integer, String> seats = new HashMap<>();
        for (SeatEntity seat : getSeats) {
            seats.put(seat.getId(), seat.getSeatNumber());
        }
        model.addAttribute("seats", seats);
        return "manager/ajax/load-seat";
    }

    @RequestMapping("/getDestination")
    public String getDestination(@RequestParam("departureId") int id, Model model) {
        List<AirportEntity> getDestinations = airportService.getDestination(id);
        Map<Integer, String> destinations = new HashMap<>();
        for (AirportEntity airport : getDestinations) {
            destinations.put(airport.getId(), airport.getCity().getCityName());
        }
        model.addAttribute("destinations", destinations);
        return "manager/ajax/load-destination";
    }

    @RequestMapping("/getFlights")
    public String getFlights(@RequestParam("aircraftId") int id, Model model) {
        List<FlightEntity> getFlights = flightService.getFlightByAircraftId(id);
        Map<Integer, FlightRouteEntity> flights = new HashMap<>();
        for (FlightEntity flight : getFlights) {
            flights.put(flight.getId(), flight.getFlightRoute());
        }
        model.addAttribute("flights", flights);
        return "manager/ajax/load-flight";
    }

    @RequestMapping("/change-price")
    @ResponseBody
    public String priceService(Model model, @RequestParam("serviceId") int serviceId,
                               @RequestParam("quantity") int quantity,
                               @SessionAttribute("serviceBooking") HashMap<Integer, ServiceBookingEntity> serviceBookings) {
        ServiceEntity service = services.getService(serviceId);
        if (quantity > 0) {
            boolean check = true;
            if (serviceBookings.size() > 0) {
                for (Map.Entry<Integer, ServiceBookingEntity> entry : serviceBookings.entrySet()) {
                    if (entry.getKey() == serviceId) {
                        ServiceBookingEntity serviceBooking = serviceBookings.get(entry.getKey());
                        serviceBooking.setQuantity(quantity);
                        serviceBooking.setPrice(service.getPrice());
                        serviceBookings.put(entry.getKey(), serviceBooking);
                        check = true;
                        break;
                    } else {
                        check = false;
                    }
                }
                if (!check) {
                    ServiceBookingEntity serviceBookingB = new ServiceBookingEntity();
                    serviceBookingB.setService(service);
                    serviceBookingB.setQuantity(quantity);
                    serviceBookingB.setPrice(service.getPrice());
                    serviceBookings.put(serviceId, serviceBookingB);
                }
            } else {
                ServiceBookingEntity serviceBooking = new ServiceBookingEntity();
                serviceBooking.setService(service);
                serviceBooking.setQuantity(quantity);
                serviceBooking.setPrice(service.getPrice());
                serviceBookings.put(serviceId, serviceBooking);
            }
        } else {
            serviceBookings.remove(serviceId);
        }

        double totalServiceOfBookingDetail = 0;
        for (Map.Entry<Integer, ServiceBookingEntity> entry : serviceBookings.entrySet()) {
            totalServiceOfBookingDetail += entry.getValue().getPrice() * entry.getValue().getQuantity();
        }
        model.addAttribute("totalServiceOfBookingDetail", totalServiceOfBookingDetail);
        DecimalFormat formatter = new DecimalFormat("###,###,### VND");
        String price = formatter.format(totalServiceOfBookingDetail);
        return price;
    }

    @RequestMapping("/load-service")
    public String loadService(Model model, @RequestParam("bookingDetailId") int bookingDetailId,
                              @RequestParam("bookingId") int bookingId,
                              @SessionAttribute("serviceBooking") HashMap<Integer, ServiceBookingEntity> serviceBookings) {
        Collection<ServiceBookingEntity> values = serviceBookings.values();
        List<ServiceBookingEntity> serviceBookingss = new ArrayList<ServiceBookingEntity>(values);
        BookingDetailEntity bookingDetail = bookingDetailService.getBookingDetail(bookingDetailId);
        for (ServiceBookingEntity serviceBooking : serviceBookingss) {
            serviceBooking.setBookingDetail(bookingDetail);
        }

        bookingDetail.setServiceBookings(serviceBookingss);

        bookingDetailService.save(bookingDetail);

        List<BookingDetailEntity> bookingDetails = bookingDetailService.getBookingDetailsByBooking(bookingId);
        model.addAttribute("bookingDetails", bookingDetails);
        model.addAttribute("services", services.getServices());
        model.addAttribute("booking", bookingService.getBooking(bookingId));
        model.addAttribute("direction", bookingDetailService.getDirection(bookingDetails));
        model.addAttribute("bookingStatus", bookingStatusService.getBookingStatus());
        return "manager/ajax/load-service";

    }
}
