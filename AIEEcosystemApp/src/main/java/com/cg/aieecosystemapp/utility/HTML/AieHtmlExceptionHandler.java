package com.cg.aieecosystemapp.utility.HTML;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.aieecosystemapp.aieexception.AieAuthenticationException;
import com.cg.aieecosystemapp.aieexception.AieInvalidFieldsException;
import com.cg.aieecosystemapp.utility.HTML.AieHtmlStatusCode;

@ControllerAdvice
public class AieHtmlExceptionHandler {

	
	@ExceptionHandler(AieInvalidFieldsException.class)
	public ResponseEntity<AieHtmlReponseBody> invalidFields(AieInvalidFieldsException ex) {
		AieHtmlReponseBody body = new AieHtmlReponseBody(ex.getMessage(),AieHtmlStatusCode.INVALID_FIELD.toCode(),null);
		
		return new ResponseEntity<>(body, HttpStatus.FAILED_DEPENDENCY);
	}
	
	@ExceptionHandler(AieAuthenticationException.class)
	public ResponseEntity<AieHtmlReponseBody> invalidFields(AieAuthenticationException ex) {
		AieHtmlReponseBody body = new AieHtmlReponseBody(ex.getMessage(),AieHtmlStatusCode.AUTHENTICATION_FAIL.toCode(),null);
		
		return new ResponseEntity<>(body, HttpStatus.FAILED_DEPENDENCY);
	}
}
