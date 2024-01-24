package org.example.lunchdecider.controller;
// controller/LunchController.java
import org.example.lunchdecider.model.Session;
import org.example.lunchdecider.model.Restaurant;
import org.example.lunchdecider.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/lunch")
public class LunchController {

    @Autowired
    private RestaurantService restaurantService;

    private Session currentSession;

    public Session getCurrentSession() {
        return currentSession;
    }

    @GetMapping
    public String index(Model model) {

        if (currentSession == null) {
            currentSession = new Session();
        }

        model.addAttribute("code", "");
        model.addAttribute("session", currentSession);
        model.addAttribute("restaurant", new Restaurant());
        model.addAttribute("listShops", restaurantService.getRestaurantsBySessionCode(currentSession.getSessionCode()));

        return "index";
    }

    @PostMapping("/start")
    public String startSession(RedirectAttributes redirectAttributes) {
        if (currentSession != null && currentSession.isSessionActive()) {
            redirectAttributes.addFlashAttribute("message",
                    "A session is already active. End the current session before starting a new one.");
        } else {
            currentSession = new Session();
            currentSession.setSessionActive(true);
            redirectAttributes.addFlashAttribute("message",
                    "Session started. Invite others to join. Please provide this code to end session: " + currentSession.getSessionCode());
        }

        return "redirect:/lunch";
    }

    @PostMapping("/join")
    public String joinSession(RedirectAttributes redirectAttributes) {
        if (currentSession != null && !currentSession.isSessionActive()) {
             redirectAttributes.addFlashAttribute("message",
                    "No active session to join.");
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "You are in. Please submit restaurant name.");
        }
        return "redirect:/lunch";
    }

    @PostMapping("/submit")
    public String submitRestaurant(@ModelAttribute("restaurant") Restaurant newRestaurant, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            // Handle validation errors
//            return "registrationForm";
//        }

        if (currentSession != null && currentSession.isSessionActive()) {

            newRestaurant.setSessionCode(currentSession.getSessionCode());
            restaurantService.saveRestaurant(newRestaurant);

            currentSession.setRestaurants(restaurantService.getRestaurantsBySessionCode(currentSession.getSessionCode()));

        } else {
            redirectAttributes.addFlashAttribute("message",
                    "No active session to submit.");
            return "index";
        }
        return "redirect:/lunch";
    }

    @PostMapping("/end")
    public String endSession(@ModelAttribute("code") String scode, RedirectAttributes redirectAttributes) {
        if (currentSession == null || !currentSession.isSessionActive()) {
            redirectAttributes.addFlashAttribute("message",
                    "No active session to end.");
        }
        else if (scode.isEmpty() ||
                (currentSession != null && !scode.equalsIgnoreCase(currentSession.getSessionCode()))) {
            redirectAttributes.addFlashAttribute("message",
                    "Please provide valid code to end the session.");

        }
        else if (currentSession != null && currentSession.isSessionActive()) {
            List<Restaurant> restaurants = currentSession.getRestaurants();

            if (!restaurants.isEmpty()) {
                Collections.shuffle(restaurants, new Random());
                Restaurant pickedRestaurant = restaurants.get(0);
                redirectAttributes.addFlashAttribute("message",
                        "Session ended. Picked restaurant: " + pickedRestaurant.getName());
            } else {
                redirectAttributes.addFlashAttribute("message",
                        "No restaurants submitted. Session ended.");
            }

            currentSession.setSessionEnded(true);
            currentSession = null;
        }

        return "redirect:/lunch";
    }
}

