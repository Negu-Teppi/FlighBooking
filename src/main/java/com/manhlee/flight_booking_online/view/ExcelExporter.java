package com.manhlee.flight_booking_online.view;

import com.manhlee.flight_booking_online.entities.BookingEntity;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<BookingEntity> bookings;

    public ExcelExporter(List<BookingEntity> bookings) {
        this.bookings = bookings;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Booking Id", style);
        createCell(row, 1, "Full Name", style);
        createCell(row, 2, "Email", style);
        createCell(row, 3, "Address", style);
        createCell(row, 4, "Gender", style);
        createCell(row, 5, "Phone Number", style);
        createCell(row, 6, "Birthday", style);
        createCell(row, 7, "Booking Date", style);
        createCell(row, 8, "Booking Number", style);
        createCell(row, 9, "Status", style);
        createCell(row, 10, "Total Price", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (BookingEntity booking : bookings) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, booking.getId(), style);
            createCell(row, columnCount++, booking.getFullName(), style);
            createCell(row, columnCount++, booking.getEmail(), style);
            createCell(row, columnCount++, booking.getAddress(), style);
            createCell(row, columnCount++, booking.getGender().name(), style);
            createCell(row, columnCount++, booking.getPhoneNumber(), style);
            createCell(row, columnCount++, booking.getBirthDay(), style);
            createCell(row, columnCount++, booking.getBookingDate(), style);
            createCell(row, columnCount++, booking.getBookingNumber(), style);
            createCell(row, columnCount++, booking.getBookingStatus().getStatus(), style);
            createCell(row, columnCount++, booking.getTotalPrice(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
