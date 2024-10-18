package com.dharshi.springboot_auth0.mappers;

import com.dharshi.springboot_auth0.dtos.BookingDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRowMapper implements RowMapper<BookingDTO> {

    @Override
    public BookingDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookingDTO booking = new BookingDTO();
        booking.setBookingId(rs.getInt("booking_id"));
        booking.setDate(rs.getObject("date", LocalDate.class));
        booking.setTime(String.valueOf(rs.getObject("time", LocalTime.class)));
        booking.setLocation(rs.getString("location"));
        booking.setVehicleNo(rs.getString("vehicle_no"));
        booking.setMileage(rs.getInt("mileage"));
        booking.setMessage(rs.getString("message"));
        booking.setUsername(rs.getString("username"));
        return booking;
    }
}

