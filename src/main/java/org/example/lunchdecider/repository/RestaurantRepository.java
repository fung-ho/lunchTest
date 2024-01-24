package org.example.lunchdecider.repository;
// repository/RestaurantRepository.java
import org.example.lunchdecider.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findBySessionCode(String sessionCode);
}
