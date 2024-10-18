package com.dharshi.springboot_auth0.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private int bookingId;

    @NotNull(message = "Date is required!")
    @Future(message = "The service date must be in the future!")
    private LocalDate date;

    @NotBlank(message = "Time is required!")
    @Pattern(regexp = "(10:00|11:00|12:00)", message = "Time must be either 10:00, 11:00, or 12:00!")
    private String time;

    @NotBlank(message = "Location is required!")
    @Size(max = 255, message = "Location must not exceed 255 characters!")
    private String location;

    @Pattern(regexp = "[A-Z]{2,3}[-\\s]?\\d{3,4}", message = "Vehicle number format is invalid!")
    private String vehicleNo;

    @Pattern(regexp = "^\\d+$", message = "Mileage should be a number!")
    private String mileage;

    @Size(max = 500, message = "Message must not exceed 500 characters!")
    private String message;

    private String username;

    public BookingDTO(LocalDate date, String time, String location, String vehicleNo, String mileage, String message) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.vehicleNo = vehicleNo;
        this.mileage = mileage;
        this.message = message;
    }
}
