package com.cg.aieecosystemapp.aieexception;

import com.cg.aieecosystemapp.utility.HTML.AieHtmlStatusCode;

public class AieExceptionClass extends RuntimeException {
	
	public AieExceptionClass(String customErrorMessage) {
		super(customErrorMessage);
	}
}
