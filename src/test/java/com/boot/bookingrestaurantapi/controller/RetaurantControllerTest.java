package com.boot.bookingrestaurantapi.controller;

import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.jsons.TurnRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.boot.bookingrestaurantapi.controllers.RestaurantController;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.services.RestaurantService;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class RetaurantControllerTest {

    private static final Long RESTAURANT_ID = 1l;
    private static final String SUCCESS_STATUS = "Succes";
    private static final String SUCCESS_CODE = "200 OK";
    private static final String OK = "OK";
    private static final String NAME = "Burguer";
    private static final String DESCRIPTION = "All burguers type";
    private static final String ADDRES = "Av. peru";
    private static final String IMG = "www.google.com";

    private static final List<TurnRest> TURN_LIST = new ArrayList<>();
    public static final RestaurantRest RESTAURANT_REST = new RestaurantRest();
    public static final List<RestaurantRest> RESTAURANT_REST_LIST = new ArrayList<>();

    @Mock
    RestaurantService restaurantService;

    @InjectMocks
    RestaurantController restaurantController;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);
        RESTAURANT_REST.setName(NAME);
        RESTAURANT_REST.setDescription(DESCRIPTION);
        RESTAURANT_REST.setAddress(ADDRES);
        RESTAURANT_REST.setId(RESTAURANT_ID);
        RESTAURANT_REST.setImage(IMG);
        RESTAURANT_REST.setTurns(TURN_LIST);

        Mockito.when(restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST);

    }

    @Test
    public void getRestaurantByIdTest() throws BookingException {
        final BookingResponse<RestaurantRest> response = restaurantController.getRestaurantById(RESTAURANT_ID);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), OK);
        Assert.assertEquals(response.getData(), RESTAURANT_REST);
    }

    @Test
    public void getRestaurantsTest() throws BookingException {
        final BookingResponse<List<RestaurantRest>> response = restaurantController.getRestaurants();
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), OK);
        Assert.assertEquals(response.getData(), RESTAURANT_REST_LIST);


    }

}
