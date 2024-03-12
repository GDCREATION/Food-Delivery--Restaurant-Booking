package com.nagp.bookingservice.service;

// BookingService.java

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + id));
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking createBooking(BookingRequest bookingRequest) {
        // Additional logic for creating a booking
        // ...

        // For simplicity, let's assume the booking is directly saved to the repository
        Booking booking = new Booking();
        booking.setRestaurantId(bookingRequest.getRestaurantId());
        booking.setNumOfSeats(bookingRequest.getNumOfSeats());
        return bookingRepository.save(booking);
    }

    // Other methods...
}

