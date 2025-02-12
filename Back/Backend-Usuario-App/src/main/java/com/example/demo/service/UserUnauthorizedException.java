package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UserUnauthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4449783187199016982L;

	public UserUnauthorizedException() {
	}

	public UserUnauthorizedException(String message) {
		super(message);
	}

	public UserUnauthorizedException(Throwable cause) {
		super(cause);
	}

	public UserUnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserUnauthorizedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
