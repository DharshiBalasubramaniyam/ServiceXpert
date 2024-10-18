package com.dharshi.springboot_auth0.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private int bookingId;

    @NotNull(message = "Date cannot be null")
    @Future(message = "The service date must be in the future")
    private LocalDate date;

    @NotBlank(message = "Time cannot be empty")
    @Pattern(regexp = "(10:00|11:00|12:00)", message = "Time must be either 10:00, 11:00, or 12:00")
    private String time;

    @NotBlank(message = "Location cannot be empty")
    @Size(max = 255, message = "Location must not exceed 255 characters")
    private String location;

    @NotBlank(message = "Vehicle number cannot be empty")
    @Pattern(regexp = "[A-Z]{2}\\d{3,4}", message = "Vehicle number format is invalid")
    private String vehicleNo;

    @Min(value = 0, message = "Mileage must be a positive number")
    private int mileage;

    @Size(max = 500, message = "Message must not exceed 500 characters")
    private String message;
    private String username;

    public BookingDTO(LocalDate date, String time, String location, String vehicleNo, int mileage, String message) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.vehicleNo = vehicleNo;
        this.mileage = mileage;
        this.message = message;
    }
}
