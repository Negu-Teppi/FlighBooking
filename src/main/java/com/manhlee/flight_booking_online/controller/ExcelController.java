package com.manhlee.flight_booking_online.controller;

import com.manhlee.flight_booking_online.entities.BookingEntity;
import com.manhlee.flight_booking_online.service.BookingService;
import com.manhlee.flight_booking_online.view.ExcelReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/report")
public class ExcelController {

    @Autowired
    private BookingService bookingService;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getExcel(@RequestParam("start") String strStartDate,
            @RequestParam("end") String strEndDate) throws ParseException {

        List<BookingEntity> bookings= new ArrayList<>();
        if (!strEndDate.equals("") && !strStartDate.equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = formatter.parse(strStartDate);
            Date endDate = formatter.parse(strEndDate);
            bookings = bookingService.searchByBookingDate(startDate, endDate);
        } else {
            bookings = bookingService.getBookings();
        }
        return new ModelAndView(new ExcelReportView(), "bookings", bookings);
    }
//
//    @RequestMapping("/booking/export/excel")
//    public void exportToExcel(HttpServletResponse response) throws IOException {
//        response.setContentType("application/octet-stream");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//
//        List<BookingEntity> bookings = bookingService.getBookings();
//
//        ExcelExporter excelExporter = new ExcelExporter(bookings);
//
//        excelExporter.export(response);
//    }
//
//    @RequestMapping("/call")
//    @ResponseBody
//    public String getLink(){
//        String url="report/booking/export/excel";
//        return url;
//    }
}
