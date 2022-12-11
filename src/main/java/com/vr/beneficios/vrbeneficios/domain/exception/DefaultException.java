package com.vr.beneficios.vrbeneficios.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class DefaultException extends RuntimeException implements Supplier<DefaultException> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public HttpStatus httpStatus;
    public String message;
    public JsonNode json;

    public DefaultException(HttpStatus httpStatus, JsonNode json) {
        this.httpStatus = httpStatus;
        this.json = json;
    }

    public DefaultException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
    
    public DefaultException() {}

    @Override
    public DefaultException get() {
        return this;
    }
    
    public ErrorResponse getErrorResponse() {
    	ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(this.message);
        errorResponse.setBody(this.json);
        return errorResponse;
    }
   
}