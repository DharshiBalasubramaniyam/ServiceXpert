package com.dharshi.springboot_auth0.controllers;

import com.dharshi.springboot_auth0.dtos.BookingDTO;
import com.dharshi.springboot_auth0.services.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/booking")
    public String showBookingForm(Model model, @AuthenticationPrincipal OidcUser oidcUser) {

        model.addAttribute("booking", new BookingDTO());
        model.addAttribute("profile", oidcUser.getClaims());
        return "bookingForm";
    }

    @PostMapping("/book")
    public String submitBooking(
            Model model,
            @Valid @ModelAttribute BookingDTO bookingDTO,
            BindingResult result,
            @AuthenticationPrincipal OidcUser oidcUser) {

        model.addAttribute("profile", oidcUser.getClaims());

        if (result.hasErrors()) {
            // If validation fails, return the form view with error messages
            System.out.println(result.getModel());
            System.out.println(result.getFieldErrors());
            System.out.println(result.getAllErrors());
            model.addAttribute("booking", bookingDTO);
            return "bookingForm";
        }

        String username = oidcUser.getEmail();
        bookingDTO.setUsername(username);

        bookingService.createBooking(bookingDTO);

        List<BookingDTO> bookings = bookingService.getBookingsByUser(oidcUser.getEmail());
        model.addAttribute("bookings", bookings);
        return "myBookings";
    }

    @GetMapping("/mybookings")
    public String getBookingByUser(Model model,  @AuthenticationPrincipal OidcUser oidcUser) {

        model.addAttribute("profile", oidcUser.getClaims());

        List<BookingDTO> bookings = bookingService.getBookingsByUser(oidcUser.getEmail());
        model.addAttribute("bookings", bookings);
        return "myBookings";
    }

    @PostMapping("/deleteBooking/{id}")
    public String deleteBooking(@PathVariable("id") int bookingId, @AuthenticationPrincipal OidcUser oidcUser, Model model) {

        model.addAttribute("profile", oidcUser.getClaims());

        bookingService.deleteBooking(oidcUser.getEmail(), bookingId);

        List<BookingDTO> bookings = bookingService.getBookingsByUser(oidcUser.getEmail());
        model.addAttribute("bookings", bookings);

        return "myBookings";  // Return the view for listing bookings
    }
}
