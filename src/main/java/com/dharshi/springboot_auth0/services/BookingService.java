package com.dharshi.springboot_auth0.services;

import com.dharshi.springboot_auth0.dtos.BookingDTO;
import com.dharshi.springboot_auth0.mappers.BookingRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingService {

    private final JdbcTemplate jdbcTemplate;

    public BookingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createBooking(BookingDTO bookingDTO) {

        String sql = "INSERT INTO bookings (date, time, location, vehicle_no, mileage, message, username) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                bookingDTO.getDate(),
                bookingDTO.getTime(),
                bookingDTO.getLocation(),
                bookingDTO.getVehicleNo(),
                bookingDTO.getMileage(),
                bookingDTO.getMessage(),
                bookingDTO.getUsername());

    }

    public void deleteBooking() {

    }

    public List<BookingDTO> getBookingsByUser(String username) {

        String query = "SELECT * FROM bookings WHERE username = ?";
        return jdbcTemplate.query(query, new Object[]{username}, new BookingRowMapper());

    }

    public void deleteBooking(String username, int bookingId) {

        String sql = "SELECT username FROM bookings WHERE booking_id = ?";

        String bookingUsername = jdbcTemplate.queryForObject(sql, new Object[]{bookingId}, String.class);

        if (bookingUsername != null && bookingUsername.equals(username)) {
            String deleteSql = "DELETE FROM bookings WHERE booking_id = ?";
            jdbcTemplate.update(deleteSql, bookingId);
        }
    }
}
