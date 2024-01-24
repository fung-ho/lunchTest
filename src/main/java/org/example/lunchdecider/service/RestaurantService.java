package org.example.lunchdecider.service;

// service/RestaurantService.java
import org.example.lunchdecider.model.Restaurant;
import org.example.lunchdecider.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public List<Restaurant> getRestaurantsBySessionCode(String code) {
        return restaurantRepository.findBySessionCode(code);
    }

    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }
}

