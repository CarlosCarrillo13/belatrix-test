package com.carrillo.belatrixapplication.configuration.exceptions;

public class MessageContextException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageContextException(String message, Exception exception) {
		super(message, exception);
	}
}
