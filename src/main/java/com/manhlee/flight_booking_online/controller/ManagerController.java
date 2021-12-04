package com.manhlee.flight_booking_online.controller;

import com.manhlee.flight_booking_online.entities.*;
import com.manhlee.flight_booking_online.enums.CommonStatus;
import com.manhlee.flight_booking_online.enums.SeatStatus;
import com.manhlee.flight_booking_online.enums.SeatType;
import com.manhlee.flight_booking_online.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private CityService cityService;

    @Autowired
    private FlightRouteService flightRouteService;

    @Autowired
    private SeatStatusService seatStatusService;

    @Autowired
    private SeatTypeService seatTypeService;

    @Autowired
    private AircraftSeatService aircraftSeatService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private ImageService imageService;


    @RequestMapping("/home")
    public String viewHome(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        model.addAttribute("message", "Hello Manager: " + username);
        return "redirect:/manager/view/aircraft";
    }

    @RequestMapping("/view/aircraft")
    public String viewAircraft(Model model,
                               @RequestParam("page") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        model.addAttribute("aircrafts", aircraftService.getAircraftPages(pageable));
//        model.addAttribute("aircrafts", aircraftService.getAircrafts());
        return "manager/setup/aircraft/view-aircraft";
    }

    @RequestMapping("/aircraft/add-aircraft")
    public String addAircraft(Model model) {
        model.addAttribute("aircraft", new AircraftEntity());
        model.addAttribute("status", CommonStatus.values());
        model.addAttribute("action", "add");
        return "manager/setup/aircraft/edit-aircraft";
    }

    @RequestMapping(value = "/aircraft/result", method = RequestMethod.POST)
    public String resultAircraft(@ModelAttribute("aircraft") AircraftEntity aircraft,
                                 MultipartFile[] files, HttpServletRequest request) {

        List<ImageEntity> images = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            try {
                byte[] bytes = file.getBytes();

                ServletContext context = request.getServletContext();
                String pathUrl = context.getRealPath("/images");

                int index = pathUrl.indexOf("target");
                String pathFolder = pathUrl.substring(0, index) + "src\\main\\webapp\\resources-management\\image\\aircraft\\";
                Path path = Paths.get(pathFolder + file.getOriginalFilename());
                Files.write(path, bytes);

                // get file name
                String name = file.getOriginalFilename();

                if (name == null) {
                    name = "new-image" + name;
                }

                ImageEntity image = new ImageEntity();
                image.setName(name);
                image.setAircraft(aircraft);
                images.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        aircraft.setImages(images);
        aircraftService.save(aircraft);

        return "redirect:/manager/view/aircraft";
    }

    @RequestMapping(value = "/aircraft/edit/{id}")
    public String editAircraft(Model model, @PathVariable("id") int id) {

        model.addAttribute("aircraft", aircraftService.getAircraft(id));
        model.addAttribute("images", imageService.getImagesAircraft(id));
        model.addAttribute("status", CommonStatus.values());
        model.addAttribute("action", "update");
        return "manager/setup/aircraft/edit-aircraft";
    }

    @RequestMapping("/view/airport")
    public String viewAirport(Model model,
                              @RequestParam("page") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        model.addAttribute("airports", airportService.getAircraftPages(pageable));
//        model.addAttribute("airports", airportService.getAirports());
        return "manager/setup/airport/view-airport";
    }

    @RequestMapping("/airport/add-airport")
    public String addAirport(Model model) {
        model.addAttribute("airport", new AirportEntity());
        model.addAttribute("cities", cityService.getCities());
        model.addAttribute("status", CommonStatus.values());
        model.addAttribute("action", "add");
        return "manager/setup/airport/edit-airport";
    }

    @RequestMapping(value = "/airport/result", method = RequestMethod.POST)
    public String resultAirport(@ModelAttribute("airport") AirportEntity airport,
                                MultipartFile[] files, HttpServletRequest request, @RequestParam("action") String action) {

        List<ImageEntity> images = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            try {
                byte[] bytes = file.getBytes();

                ServletContext context = request.getServletContext();
                String pathUrl = context.getRealPath("/images");

                int index = pathUrl.indexOf("target");
                String pathFolder = pathUrl.substring(0, index) + "src\\main\\webapp\\resources-management\\image\\airport\\";
                Path path = Paths.get(pathFolder + file.getOriginalFilename());
                Files.write(path, bytes);

                // get file name
                String name = file.getOriginalFilename();

                if (name == null) {
                    name = "new-image" + name;
                }

                ImageEntity image = new ImageEntity();
                image.setName(name);
                image.setAirport(airport);
                images.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            airport.setImages(images);
            airportService.save(airport);
        }
        return "redirect:/manager/view/airport";
    }

    @RequestMapping(value = "/airport/edit/{id}")
    public String editAirport(Model model, @PathVariable("id") int id) {

        model.addAttribute("airport", airportService.getAirport(id));
        model.addAttribute("cities", cityService.getCities());
        model.addAttribute("images", imageService.getImagesAirport(id));
        model.addAttribute("status", CommonStatus.values());
        model.addAttribute("action", "update");
        return "manager/setup/airport/edit-airport";
    }


    @RequestMapping("/flight-route/view")
    public String viewFlightRoute(Model model,
                                  @RequestParam("page") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        model.addAttribute("flightRoutes", flightRouteService.getFlightRoutePages(pageable));
//        model.addAttribute("flightRoutes", flightRouteService.getFlightRoutes());
        return "manager/setup/flight-route/view-flightRoute";
    }

    @RequestMapping(value = "/flight-route/add")
    public String addFlightRoute(Model model) {
        model.addAttribute("flightRoute", new FlightRouteEntity());
        model.addAttribute("airports", airportService.getAirports());
        model.addAttribute("status", CommonStatus.values());
        model.addAttribute("action", "add");
        return "manager/setup/flight-route/edit-flight-route";
    }

    @RequestMapping(value = "/flight-route/result", method = RequestMethod.POST)
    public String resultFlightRoute(@ModelAttribute("flightRoute") FlightRouteEntity flightRoute) {
        flightRouteService.save(flightRoute);
        return "redirect:/manager/flight-route/view";
    }

    @RequestMapping("/flight-route/edit/{id}")
    public String updateFlightRoute(Model model, @PathVariable("id") int id) {
        model.addAttribute("flightRoute", flightRouteService.getFlightRoute(id));
        model.addAttribute("airports", airportService.getAirports());
        model.addAttribute("status", CommonStatus.values());
        model.addAttribute("action", "update");
        return "manager/setup/flight-route/edit-flight-route";
    }

    @RequestMapping("/aircraft-seat/view")
    public String viewAircraftSeat(Model model) {
        model.addAttribute("aircraftSeats", aircraftSeatService.getAircraftSeats());
        return "manager/setup/aircraft/view-aircraft-seat";
    }

    @RequestMapping(value = "/aircraft-seat/add")
    public String addAircraftSeat(Model model) {
        model.addAttribute("aircraftSeat", new AircraftSeatEntity());
        model.addAttribute("seatTypes", SeatType.values());
        model.addAttribute("seatStatus", SeatStatus.values());
        model.addAttribute("aircrafts", aircraftService.getAircrafts());
        model.addAttribute("flights", flightService.getFlights());
        model.addAttribute("action", "add");
        return "manager/setup/aircraft/set-seat";
    }

    @RequestMapping(value = "/aircraft-seat/result", method = RequestMethod.POST)
    public String resultAircraftSeat(@ModelAttribute("aircraftSeat") AircraftSeatEntity aircraftSeat) {

        SeatStatusEntity seatStatus = seatStatusService.getSeatStatus(aircraftSeat.getSeatStatus().getStatus().name()).get(0);
        aircraftSeat.setSeatStatus(seatStatus);

        SeatTypeEntity seatType = seatTypeService.getSeatType(aircraftSeat.getSeatType().getSeatType().name()).get(0);
        aircraftSeat.setSeatType(seatType);
        aircraftSeatService.save(aircraftSeat);
        return "redirect:/manager/flight-route/view";
    }

    @RequestMapping("/aircraft-seat/edit/{id}")
    public String updateAircraftSeat(Model model, @PathVariable("id") int id) {
        model.addAttribute("aircraft", aircraftSeatService.getAircraftSeat(id));
        model.addAttribute("seatTypes", SeatType.values());
        model.addAttribute("seatStatus", SeatStatus.values());
        model.addAttribute("aircrafts", aircraftService.getAircrafts());
        model.addAttribute("flights", flightService.getFlights());
        model.addAttribute("action", "update");
        return "redirect:/manager/view/aircraft";
    }

    @RequestMapping("/airport/image/delete/{airportId}/{id}")
    public String deleteImage(Model model, @PathVariable("id") int id,
                              @PathVariable("airportId") int airportId) {
        imageService.delete(id);
        model.addAttribute("airport", airportService.getAirport(id));
        model.addAttribute("images", imageService.getImagesAirport(airportId));
        model.addAttribute("cities", cityService.getCities());
        model.addAttribute("status", CommonStatus.values());
        model.addAttribute("action", "update");
        return "redirect:/manager/view/airport";
    }
}
