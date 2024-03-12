package com.nagp.restaurantinfoservice.exception;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(String message) {
        super(message);
    }
}