package com.nagp.restaurantinfoservice.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class RestaurantRepository {

    private final Map<Long, Restaurant> restaurantMap = new HashMap<>();
    private final List<Restaurant> restaurantList = new ArrayList<>();
    private Long currentId = 1L;

    public Optional<Restaurant> findById(Long id) {
        return Optional.ofNullable(restaurantMap.get(id));
    }

    public Restaurant save(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            restaurant.setId(currentId);
            currentId++;
        }
        restaurantMap.put(restaurant.getId(), restaurant);
        restaurantList.add(restaurant);
        return restaurant;
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurantList);
    }

    public void deleteById(Long id) {
        Restaurant restaurant = restaurantMap.remove(id);
        if (restaurant != null) {
            restaurantList.remove(restaurant);
        }
    }
}

