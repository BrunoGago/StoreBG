package com.brunogago.crud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3371514517598331063L;

	public ResourceNotFoundException(String exception) {
		super(exception);
	}
}
