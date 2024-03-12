package com.nagp.bookingservice.controller;
// BookingController.java

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final RestaurantInfoServiceClient restaurantInfoServiceClient;

    public BookingController(BookingService bookingService, RestaurantInfoServiceClient restaurantInfoServiceClient) {
        this.bookingService = bookingService;
        this.restaurantInfoServiceClient = restaurantInfoServiceClient;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest) {
        // Check if seats are available in the restaurant
        RestaurantInfo restaurantInfo = restaurantInfoServiceClient.getRestaurantInfo(bookingRequest.getRestaurantId());
        if (restaurantInfo.getAvailableSeats() < bookingRequest.getNumOfSeats()) {
            throw new SeatsUnavailableException("Seats not available for booking.");
        }

        // Proceed with booking
        Booking createdBooking = bookingService.createBooking(bookingRequest);
        return ResponseEntity.status(201).body(createdBooking);
    }

    // Other APIs...
}

