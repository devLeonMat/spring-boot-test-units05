package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.entities.Board;
import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.services.impl.RestaurantServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.fail;

public class RestaurantServiceImplTest {

    private static final Long RESTAURANT_ID = 1l;
    private static final String SUCCESS_STATUS = "Succes";
    private static final String SUCCESS_CODE = "200 OK";
    private static final String OK = "OK";
    private static final String NAME = "Burguer";
    private static final String DESCRIPTION = "All burguers type";
    private static final String ADDRES = "Av. peru";
    private static final String IMG = "www.google.com";

    public static final Restaurant RESTAURANT = new Restaurant();
    private static final List<Turn> TURN_LIST = new ArrayList<>();
    private static final List<Board> BOARDS_LIST = new ArrayList<>();
    private static final List<Reservation> RESERVATIONS_LIST = new ArrayList<>();


    @Mock
    RestaurantRepository restaurantRepository;

    @InjectMocks
    RestaurantServiceImpl restaurantServiceImpl;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);
        RESTAURANT.setName(NAME);
        RESTAURANT.setDescription(DESCRIPTION);
        RESTAURANT.setAddress(ADDRES);
        RESTAURANT.setId(RESTAURANT_ID);
        RESTAURANT.setImage(IMG);
        RESTAURANT.setTurns(TURN_LIST);
        RESTAURANT.setBoards(BOARDS_LIST);
        RESTAURANT.setReservations(RESERVATIONS_LIST);

    }

    @Test
    public void getRestaurantByIdTest() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID))
                .thenReturn(Optional.of(RESTAURANT));
        restaurantServiceImpl.getRestaurantById(RESTAURANT_ID);
    }

    @Test(expected = BookingException.class)
    public void getRestaurantByIdTestError() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID))
                .thenReturn(Optional.empty());
        restaurantServiceImpl.getRestaurantById(RESTAURANT_ID);
        fail();
    }

    @Test
    public void getRestaurantsTest() throws BookingException {
        final Restaurant restaurant = new Restaurant();
        Mockito.when(restaurantRepository.findAll()).thenReturn(Collections.singletonList(restaurant));
//        Mockito.when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant));

        final List<RestaurantRest> response = restaurantServiceImpl.getRestaurants();
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
        Assert.assertEquals(response.size(), 1);
    }

}
