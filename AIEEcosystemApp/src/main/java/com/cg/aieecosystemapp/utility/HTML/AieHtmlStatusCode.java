package com.cg.aieecosystemapp.utility.HTML;

public enum AieHtmlStatusCode {

	// status code for success
	STATUS_OK(200),

	// status code for failure
	ENTRY_NOT_FOUND(401);
	
	
	
	
	
	
	
	
	
	

	int statusCode;

	AieHtmlStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return Integer.toString(statusCode);
	}

}
