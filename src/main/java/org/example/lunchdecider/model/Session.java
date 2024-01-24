package org.example.lunchdecider.model;
// model/Session.java
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.text.*;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

public class Session {
    private boolean sessionActive;
    private boolean sessionEnded;
    private List<Restaurant> restaurants;

    private String sessionCode;

    public Session() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(LETTERS, DIGITS)
                .build();
        this.sessionCode = generator.generate(10);;
        this.sessionActive = false;
        this.sessionEnded = false;
        this.restaurants = new ArrayList<>();
    }

    public Session(String code) {
        this.sessionCode = code;
        this.sessionActive = false;
        this.sessionEnded = false;
        this.restaurants = new ArrayList<>();
    }

    // Getters and setters

    public void setSessionActive(boolean sessionActive) {
        this.sessionActive = sessionActive;
    }

    public void setSessionEnded(boolean sessionEnded) {
        this.sessionEnded = sessionEnded;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public boolean isSessionActive() {
        return sessionActive;
    }

    public boolean isSessionEnded() {
        return sessionEnded;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public String getSessionCode() {
        return sessionCode;
    }
}

