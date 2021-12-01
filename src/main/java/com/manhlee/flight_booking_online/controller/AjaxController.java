package com.manhlee.flight_booking_online.controller;

import com.manhlee.flight_booking_online.entities.*;
import com.manhlee.flight_booking_online.enums.BookingStatus;
import com.manhlee.flight_booking_online.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private CreditCardService cardService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MailSender mailSender;

    @RequestMapping("/getSeats")
    public String getSeats(@RequestParam("aircraftId") int id, Model model){
        List<SeatEntity> getSeats =  seatService.getSeats(id);
        Map<Integer, String> seats = new HashMap<>();
        for (SeatEntity seat: getSeats) {
            seats.put(seat.getId(), seat.getSeatNumber());
        }
        model.addAttribute("seats", seats);
        return "manager/ajax/load-seat";
    }

    @RequestMapping("/getDestination")
    public String getDestination(@RequestParam("departureId") int id, Model model){
        List<AirportEntity> getDestinations = airportService.getDestination(id);
        Map<Integer, String> destinations = new HashMap<>();
        for (AirportEntity airport: getDestinations){
            destinations.put(airport.getId(), airport.getCity().getCityName());
        }
        model.addAttribute("destinations", destinations);
        return "manager/ajax/load-destination";
    }

    @RequestMapping("/getFlights")
    public String getFlights(@RequestParam("aircraftId") int id, Model model){
        List<FlightEntity> getFlights =  flightService.getFlightByAircraftId(id);
        Map<Integer, FlightRouteEntity> flights = new HashMap<>();
        for (FlightEntity flight: getFlights){
            flights.put(flight.getId(), flight.getFlightRoute());
        }
        model.addAttribute("flights", flights);
        return "manager/ajax/load-flight";
    }


//    @RequestMapping(value = "/report")
//    public String report(Model model, @RequestParam("start") String strStartDate,
//                         @RequestParam("end") String strEndDate,
//                         RedirectAttributes redirectAttributes) throws ParseException {
//        List<BookingEntity> bookings= new ArrayList<>();
//        if (!strEndDate.equals("") && !strStartDate.equals("")) {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date startDate = formatter.parse(strStartDate);
//            Date endDate = formatter.parse(strEndDate);
//            bookings = bookingService.searchByBookingDate(startDate, endDate);
//        } else {
//            bookings = bookingService.getBookings();
//        }
//        redirectAttributes.addFlashAttribute("bookings", bookings);
//        return "redirect:/report";
//    }
}
