package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.entities.Board;
import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.repositories.ReservationRespository;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.repositories.TurnRepository;
import com.boot.bookingrestaurantapi.services.impl.ReservationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ReservationServiceImplTest {

    private static final Date DATE = new Date();
    private static final Long PERSON = 30L;
    private static final Long RESTAURANT_ID = 5L;
    private static final Long TURN_ID = 5L;

    private static final Long ID = 1L;
    private static final String NAME = "BURGUER";
    private static final String ADDRESS = "CALLE LOS DIOSES";
    private static final String DESCRIPTION = "BURGUER CENTER";
    private static final String IMAGE = "WWW.GOOGLE.COM.PE";

    private static final List<Reservation> RESERVATION_LIST = new ArrayList<>();
    private static final List<Board> BOARD_LIST = new ArrayList<>();
    private static final List<Turn> TURN_LIST = new ArrayList<>();

    CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();
    private static final Restaurant RESTAURANT = new Restaurant();
    private static final Turn TURN = new Turn();
    private static final Optional<Restaurant> OPTIONAL_RESTAURANT = Optional.of(RESTAURANT);
    private static final Optional<Turn> OPTIONAL_TURN = Optional.of(TURN);

    private static final Optional<Reservation> OPTIONAL_RESERVATION_EMPTY = Optional.empty();

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private TurnRepository turnRepository;

    @Mock
    private ReservationRespository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationServiceImpl;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);
        RESTAURANT.setId(ID);
        RESTAURANT.setName(NAME);
        RESTAURANT.setAddress(ADDRESS);
        RESTAURANT.setDescription(DESCRIPTION);
        RESTAURANT.setImage(IMAGE);
        RESTAURANT.setReservations(RESERVATION_LIST);
        RESTAURANT.setBoards(BOARD_LIST);
        RESTAURANT.setTurns(TURN_LIST);

        TURN.setId(TURN_ID);
        TURN.setName(NAME);
        TURN.setRestaurant(RESTAURANT);

        CREATE_RESERVATION_REST.setDate(DATE);
        CREATE_RESERVATION_REST.setTurnId(TURN_ID);
        CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
        CREATE_RESERVATION_REST.setPerson(PERSON);
    }

    @Test
    public void createReservationTest() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
        Mockito.when(reservationRepository.findByTurnAndRestaurantId(TURN.getName(),
                RESTAURANT.getId())).thenReturn(OPTIONAL_RESERVATION_EMPTY);


        reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);

    }

    @Test
    public void createReservationTestError() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
        Mockito.when(reservationRepository.findByTurnAndRestaurantId(TURN.getName(),
                RESTAURANT.getId())).thenReturn(OPTIONAL_RESERVATION_EMPTY);

        Mockito.when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(new Reservation());
        reservationServiceImpl.createReservation(CREATE_RESERVATION_REST);

    }


}
