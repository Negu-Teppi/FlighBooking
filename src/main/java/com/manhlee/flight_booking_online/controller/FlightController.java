package com.manhlee.flight_booking_online.controller;

import com.manhlee.flight_booking_online.entities.FlightEntity;
import com.manhlee.flight_booking_online.entities.PromotionEntity;
import com.manhlee.flight_booking_online.enums.FlightStatus;
import com.manhlee.flight_booking_online.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manager/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private OperationsService operationsService;

    @Autowired
    private FlightRouteService flightRouteService;
    @Autowired
    private PromotionService promotionService;

    @Autowired
    private AircraftService aircraftService;

    @RequestMapping("/view")
    public String viewFlight(Model model) {
        model.addAttribute("flights", flightService.getFlightsPromotion());
        return "manager/setup/flight/view-flight";
    }

    @RequestMapping(value = "/add")
    public String addFlight(Model model) {
        model.addAttribute("flight", new FlightEntity());
        model.addAttribute("aircrafts", aircraftService.getAircrafts());
        model.addAttribute("flightRoutes", flightRouteService.getFlightRoutes());
        model.addAttribute("promotions", promotionService.getPromotions());
        model.addAttribute("operations", operationsService.getOperations());
        model.addAttribute("status", FlightStatus.values());
        model.addAttribute("action", "add");
        return "manager/setup/flight/edit-flight";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String resultFlight(@ModelAttribute("flight") FlightEntity flight, @RequestParam("departs") String depart,
                               @RequestParam("arrivalS") String arrival) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date departDate = formatter.parse(depart);
        Date arrivalDate= formatter.parse(arrival);
        flight.setDepart(departDate);
        flight.setArrival(arrivalDate);
        List<Integer> promotionIds  = flight.getPromotionIds();
        List<PromotionEntity> promotions = new ArrayList<>();
        for(Integer promotionId : promotionIds){
            PromotionEntity promotion = promotionService.getPromotion(promotionId);
            promotions.add(promotion);
        }
        flight.setPromotions(promotions);
        flightService.save(flight);
        return "redirect:/manager/flight/view";

    }

    @RequestMapping("/edit/{id}")
    public String updateFlight(Model model, @PathVariable("id") int id) {
        model.addAttribute("flight", flightService.getFlightPromotion(id));
        model.addAttribute("aircrafts", aircraftService.getAircrafts());
        model.addAttribute("flightRoutes", flightRouteService.getFlightRoutes());
        model.addAttribute("promotions", promotionService.getPromotions());
        model.addAttribute("operations", operationsService.getOperations());
        model.addAttribute("status", FlightStatus.values());
        model.addAttribute("action", "update");
        return "manager/setup/flight/edit-flight";
    }
}
