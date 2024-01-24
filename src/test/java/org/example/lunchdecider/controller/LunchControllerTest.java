package org.example.lunchdecider.controller;

// test/com/example/lunchdecider/controller/LunchControllerTest.java
import org.example.lunchdecider.model.Restaurant;
import org.example.lunchdecider.model.Session;
import org.example.lunchdecider.service.RestaurantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = LunchController.class)
//@WebMvcTest(LunchController.class)
//@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class LunchControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
//    @Autowired
    private LunchController lunchController;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

//    @Mock
//    @Autowired
//    private BindingResult bindingResult;

    @Before
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSubmitRestaurant_WhenSessionInActive() {

        Restaurant shop1 = new Restaurant();
        shop1.setName("Test Restaurant");

        String result = lunchController.submitRestaurant(shop1, redirectAttributes);
        assertEquals("index", result);
    }

    @Test
    public void testSubmitRestaurant_WhenSessionActive() {
        lunchController.startSession(redirectAttributes);
        String sessionCode = "test2";
        Restaurant shop1 = new Restaurant();
        shop1.setName("Test Restaurant");
        shop1.setSessionCode(sessionCode);
        Restaurant shop2 = new Restaurant();
        shop2.setName("Extra Restaurant");
        shop2.setSessionCode(sessionCode);
        //lunchController = new LunchController();
        String result = lunchController.submitRestaurant(shop1, redirectAttributes);
        assertEquals("redirect:/lunch", result);
        result = lunchController.submitRestaurant(shop2, redirectAttributes);
        assertEquals("redirect:/lunch", result);
    }

    // Add more test cases for different scenarios
}
