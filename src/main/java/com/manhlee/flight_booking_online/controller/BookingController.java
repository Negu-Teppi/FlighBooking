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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("manager/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingDetailService bookingDetailService;

    @Autowired
    private Services service;

    @Autowired
    private ServiceBooking serviceBooking;

    @Autowired
    private CreditCardService cardService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private BookingStatusService bookingStatusService;

    @RequestMapping("/view")
    public String viewBooking(Model model) {
        List<BookingEntity> bookings = bookingService.getBookings();
        setTotal(bookings);
        model.addAttribute("bookings", setTotal(bookings));
        return "manager/manage/booking/view-booking";
    }

    @RequestMapping("/bookingDetail/view/{id}")
    public String viewBookingDetails(Model model, @PathVariable("id") int id,
                                        HttpSession session) {
        session.setAttribute("serviceBooking", new HashMap<Integer, ServiceBookingEntity>());
        List<BookingDetailEntity> bookingDetails = bookingDetailService.getBookingDetailsByBooking(id);
        model.addAttribute("booking", bookingService.getBooking(id));
        model.addAttribute("bookingDetails", bookingDetails);
        model.addAttribute("direction", bookingDetailService.getDirection(bookingDetails));
        model.addAttribute("bookingStatus", bookingStatusService.getBookingStatus());
        model.addAttribute("services", service.getServices());
        return "manager/manage/booking/booking-detail";
    }

    @RequestMapping("/bookingDetail/addService/{id}")
    public String viewService(Model model, @PathVariable("id") int id) {
        model.addAttribute("serviceBookings", serviceBooking.getServiceBookingByBookingDetail(id));
        return "manager/manage/booking/service-booking";
    }

    @RequestMapping(value = "/status")
    public String updateStatus(@RequestParam("status") String status, @RequestParam("id") int id,
                               Model model) {

        BookingEntity booking = bookingService.getBookingPaymentLazy(id);
        BookingStatusEntity statusB= bookingStatusService.getStatusEquals(status);
        List<BookingDetailEntity> bookingDetails = bookingDetailService.getBookingDetailsByBooking(id);
        if (status.equals("CANCEL")) {
            try {
                List<PaymentEntity> payments = booking.getPayments();
                Date returnDate = new Date();
                PaymentEntity payment1 = new PaymentEntity();
                for (PaymentEntity payment : payments) {
                    payment1.setAmount(payment.getAmount());
                    payment1.setCreditCard(payment.getCreditCard());
                    payment1.setDescription("Cancel booking number " + booking.getBookingNumber());
                    payment1.setPaymentDate(returnDate);
                    payment1.setBooking(booking);
                }
                String from = "lehongmanh71@gmail.com";
                String to = booking.getEmail();
                String subject = "Booking info";
                String content = "Cancel booking number " + booking.getBookingNumber() + " Date: " + new Date();
                sendEmail(from, to, subject, content);

                paymentService.save(payment1);

                CreditCardEntity creditCard = cardService.getCreditCard(payment1.getCreditCard().getId());
                double newBalance = creditCard.getBalance() + payment1.getAmount();
                creditCard.setBalance(newBalance);
                cardService.save(creditCard);

                booking.setBookingStatus(statusB);
                bookingService.save(booking);


                model.addAttribute("booking", bookingService.getBooking(id));
                model.addAttribute("bookingDetails", bookingDetailService.getBookingDetailsByBooking(id));
                model.addAttribute("bookingStatus",bookingStatusService.getStatus(status));
                model.addAttribute("direction", bookingDetailService.getDirection(bookingDetails));
                model.addAttribute("services", service.getServices());
                return "/manager/manage/booking/booking-detail";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(status.equals("CHECK_IN") || status.equals("COMPLETED")){
            List<BookingStatusEntity> listStatus =  bookingStatusService.getBookingStatusNotEquals("CANCEL");
            booking.setBookingStatus(statusB);
            bookingService.save(booking);

            model.addAttribute("booking", bookingService.getBooking(id));
            model.addAttribute("bookingDetails", bookingDetails);
            model.addAttribute("bookingStatus", bookingStatusService.getBookingStatus());
            model.addAttribute("direction", bookingDetailService.getDirection(bookingDetails));
            model.addAttribute("services", service.getServices());
            model.addAttribute("bookingStatus", listStatus);
            return "/manager/manage/booking/booking-detail";
        }else {

            List<BookingStatusEntity> listStatus =  bookingStatusService.getBookingStatus();
            booking.setBookingStatus(statusB);
            bookingService.save(booking);

            model.addAttribute("booking", bookingService.getBooking(id));
            model.addAttribute("bookingDetails", bookingDetails);
            model.addAttribute("bookingStatus", bookingStatusService.getBookingStatus());
            model.addAttribute("direction", bookingDetailService.getDirection(bookingDetails));
            model.addAttribute("services", service.getServices());
            model.addAttribute("bookingStatus", listStatus);
            return "/manager/manage/booking/booking-detail";
        }
        return null;
    }

    @RequestMapping("/search")
    public String searchBooking(@RequestParam("query") String query, Model model) {
        model.addAttribute("bookings", bookingService.searchBooking(query));
        model.addAttribute("bookings", setTotal(bookingService.searchBooking(query)));
        return "manager/manage/booking/view-booking";
    }

    @RequestMapping("/search/date")
    public String searchBookingForDate(Model model, @RequestParam("startDate") String strStartDate,
                                       @RequestParam("endDate") String strEndDate) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(strStartDate);
        Date endDate = formatter.parse(strEndDate);
        model.addAttribute("bookings", bookingService.searchByBookingDate(startDate, endDate));
        model.addAttribute("bookings", setTotal(bookingService.searchByBookingDate(startDate, endDate)));
        model.addAttribute("start", startDate);
        model.addAttribute("end",endDate);
        return "manager/manage/booking/view-booking";
    }

//    @RequestMapping("/serviceBooking/delete/{bookingId}/{id}")
//    public String deleteServiceBooking(Model model, @PathVariable("id") int id,
//                                       @PathVariable("bookingId") int bookingId){
//        serviceBooking.delete(id);
//        List<BookingDetailEntity> bookingDetails = bookingDetailService.getBookingDetailsByBooking(id);
//        model.addAttribute("booking", bookingService.getBooking(bookingId));
//        model.addAttribute("bookingDetails", bookingDetails);
//        model.addAttribute("bookingStatus", bookingStatusService.getBookingStatus());
//        model.addAttribute("direction", bookingDetailService.getDirection(bookingDetails));
//        model.addAttribute("services", service.getServices());
//
//
//        return "/manager/manage/booking/booking-detail";
//    }

    public List<BookingEntity> setTotal(List<BookingEntity> bookings) {

        for (BookingEntity booking : bookings) {
            double totalPrice = 0;
            List<BookingDetailEntity> bookingDetails = bookingDetailService.getBookingDetailsByBooking(booking.getId());
            for (BookingDetailEntity bookingDetail : bookingDetails) {
                List<ServiceBookingEntity> serviceBookings = serviceBooking.getServiceBookingByBookingDetail(bookingDetail.getId());
                double totalPriceOfService = priceServiceBooking(serviceBookings);
                totalPrice += bookingDetail.getUnitPrice() + totalPriceOfService - bookingDetail.getUnitPrice() * bookingDetail.getDiscount() / 100;
            }
            booking.setTotalPrice(totalPrice);
        }
        return bookings;
    }


    public double priceServiceBooking(List<ServiceBookingEntity> serviceBookings) {
        double totalPrice = 0;
        for (ServiceBookingEntity serviceBooking : serviceBookings) {
            totalPrice += serviceBooking.getService().getPrice() * serviceBooking.getQuantity();
        }
        return totalPrice;
    }

    public void sendEmail(String from, String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }
}
