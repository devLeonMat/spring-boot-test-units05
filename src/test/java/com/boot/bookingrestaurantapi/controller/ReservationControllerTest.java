package com.boot.bookingrestaurantapi.controller;

import com.boot.bookingrestaurantapi.controllers.ReservationController;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.ReservationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

public class ReservationControllerTest {

    private static final Long RESTAURANT_ID = 1L;
    private static final Date DATE = new Date();
    private static final Long PERSON = 1L;
    private static final Long TURN_ID = 1L;

    private static final String SUCCESS_STATUS = "Succes";
    private static final String SUCCESS_CODE = "200 OK";
    private static final String OK = "OK";
    private static final String LOCATOR = "BURGUER 2";


    CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();

    @Mock
    ReservationService reservationService;

    @InjectMocks
    ReservationController reservationController;


    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);
        CREATE_RESERVATION_REST.setDate(DATE);
        CREATE_RESERVATION_REST.setPerson(PERSON);
        CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
        CREATE_RESERVATION_REST.setTurnId(TURN_ID);

        Mockito.when(reservationService.createReservation(CREATE_RESERVATION_REST))
                .thenReturn(LOCATOR);

    }

    @Test
    public void createReservationTest() throws BookingException {
        final BookingResponse<String> response = reservationController.createReservation(CREATE_RESERVATION_REST);
        Assert.assertEquals(response.getStatus(), SUCCESS_STATUS);
        Assert.assertEquals(response.getCode(), SUCCESS_CODE);
        Assert.assertEquals(response.getMessage(), OK);
        Assert.assertEquals(response.getData(), LOCATOR);

    }

}
