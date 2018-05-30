package com.cg.aieecosystemapp.utility.HTML;

import java.util.Map;

public enum AieHtmlStatusCode {

	
	//register all status code here
	
	
	// status code for success
	STATUS_OK(200),

	// status code for failure
	ENTRY_NOT_FOUND(401),
	INVALID_FIELD(402),
	AUTHENTICATION_FAIL(403);

	private int statusCode;

	AieHtmlStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return name();
	}

	public int toCode() {
		return statusCode;
	}
	public String toCodeString() {
		return Integer.toString(statusCode);
	}
}
