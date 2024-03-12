package com.nagp.restaurantinfoservice.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with ID: " + id));
    }

    public List<Restaurant> getAllRestaurants() {
        List<RestaurantInfo> allRestaurants = restaurantInfoRepository.findAll();
        // Move favorite restaurant to the beginning of the list
        return allRestaurants.stream()
                .sorted((r1, r2) -> Boolean.compare(r2.isFavorite(), r1.isFavorite()))
                .collect(Collectors.toList());
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long id, Restaurant updatedRestaurant) {
        return restaurantRepository.findById(id)
                .map(existingRestaurant -> {
                    existingRestaurant.setName(updatedRestaurant.getName());
                    existingRestaurant.setCuisine(updatedRestaurant.getCuisine());
                    return restaurantRepository.save(existingRestaurant);
                })
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with ID: " + id));
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}

