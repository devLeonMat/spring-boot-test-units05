package com.boot.bookingrestaurantapi.responses;

import java.io.Serializable;

import lombok.Data;

@Data
public class BookingResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String status;
	private String code;
	private String message;
	private T data;
	
	public BookingResponse(String status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
	
	public BookingResponse(String status, String code, String message, T data) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}


}
