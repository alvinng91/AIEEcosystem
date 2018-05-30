package com.cg.aieecosystemapp.utility.html;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.aieecosystemapp.aieexception.AieAuthenticationException;
import com.cg.aieecosystemapp.aieexception.AieEntryActionException;
import com.cg.aieecosystemapp.aieexception.AieEntryNotFoundException;
import com.cg.aieecosystemapp.aieexception.AieInvalidFieldsException;
import com.cg.aieecosystemapp.aieexception.AieJpaCrudException;

@ControllerAdvice
public class AieHtmlExceptionHandler
{

    @ExceptionHandler(AieInvalidFieldsException.class)
    public ResponseEntity<AieHtmlReponseBody> invalidFields(AieInvalidFieldsException ex)
    {
	return new ResponseEntity<>(
		new AieHtmlReponseBody<>(ex.getMessage(), AieHtmlStatusCode.INVALID_FIELD.toCode(), null),
		HttpStatus.FAILED_DEPENDENCY);
    }

    @ExceptionHandler(AieAuthenticationException.class)
    public ResponseEntity<AieHtmlReponseBody> authenticateFail(AieAuthenticationException ex)
    {
	return new ResponseEntity<>(
		new AieHtmlReponseBody<>(ex.getMessage(), AieHtmlStatusCode.AUTHENTICATION_FAIL.toCode(), null),
		HttpStatus.FAILED_DEPENDENCY);
    }

    @ExceptionHandler(AieEntryNotFoundException.class)
    public ResponseEntity<AieHtmlReponseBody> entryNotFound(AieEntryNotFoundException ex)
    {

	return new ResponseEntity<>(
		new AieHtmlReponseBody<>(ex.getMessage(), AieHtmlStatusCode.ENTRY_NOT_FOUND.toCode(), null),
		HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AieEntryActionException.class)
    public ResponseEntity<AieHtmlReponseBody> entryActionIssue(AieEntryActionException ex)
    {

	return new ResponseEntity<>(
		new AieHtmlReponseBody<>(ex.getMessage(), AieHtmlStatusCode.ACTION_ERROR.toCode(), null),
		HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(AieJpaCrudException.class)
    public ResponseEntity<AieHtmlReponseBody> jpaCrudError(AieJpaCrudException ex)
    {

	return new ResponseEntity<>(
		new AieHtmlReponseBody<>(ex.getMessage(), AieHtmlStatusCode.JPA_ERROR.toCode(), null),
		HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
