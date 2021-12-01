package com.manhlee.flight_booking_online.view;

import com.manhlee.flight_booking_online.entities.BookingDetailEntity;
import com.manhlee.flight_booking_online.entities.BookingEntity;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelReportView extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
          Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment;filename=\"booking.xls\"");
        List<BookingEntity> bookings = (List<BookingEntity>) model.get("bookings");
        Sheet sheet = workbook.createSheet("Booking Data");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Id");
        header.createCell(1).setCellValue("Full Name");
        header.createCell(2).setCellValue("Email");
        header.createCell(3).setCellValue("Address");
        header.createCell(4).setCellValue("Gender");
        header.createCell(5).setCellValue("Phone Number");
        header.createCell(6).setCellValue("Birthday");
        header.createCell(7).setCellValue("Booking Date");
        header.createCell(8).setCellValue("Booking Number");
        header.createCell(9).setCellValue("Status");
        header.createCell(10).setCellValue("Total Price");

        int rowNum=1;
        for (BookingEntity booking: bookings){
            for (BookingDetailEntity bookingDetail:booking.getBookingDetails()) {
                String ticket = bookingDetail.getTicketType().getTicketType().name();
            }
            Row row =sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(booking.getId());
            row.createCell(1).setCellValue(booking.getFullName());
            row.createCell(2).setCellValue(booking.getEmail());
            row.createCell(3).setCellValue(booking.getAddress());
            row.createCell(4).setCellValue(booking.getGender().name());
            row.createCell(5).setCellValue(booking.getPhoneNumber());
            row.createCell(6).setCellValue(booking.getBirthDay());
            row.createCell(7).setCellValue(booking.getBookingDate());
            row.createCell(8).setCellValue(booking.getBookingNumber());
            row.createCell(9).setCellValue(booking.getBookingStatus().getStatus().name());
            row.createCell(10).setCellValue(booking.getTotalPrice());
        }
    }
}
