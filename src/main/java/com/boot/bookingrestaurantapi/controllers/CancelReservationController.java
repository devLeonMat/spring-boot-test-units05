package com.boot.bookingrestaurantapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.CancelReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking-restaurant" + "/v1")
public class CancelReservationController {

	CancelReservationService cancelReservationService;

	public CancelReservationController(CancelReservationService cancelReservationService) {
		this.cancelReservationService = cancelReservationService;
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/deleteReservation/{locator}")
	public BookingResponse<String> deleteReservation(@PathVariable String locator) throws BookingException {
		return new BookingResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				cancelReservationService.deleteReservation(locator));
	}

}
