package com.cg.aieecosystemapp.aieexception;

import com.cg.aieecosystemapp.utility.html.AieHtmlStatusCode;

public class AieExceptionClass extends RuntimeException {
	
	public AieExceptionClass(String customErrorMessage) {
		super(customErrorMessage);
	}
}
