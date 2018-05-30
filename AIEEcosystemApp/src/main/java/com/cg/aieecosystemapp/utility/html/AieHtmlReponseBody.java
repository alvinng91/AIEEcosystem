package com.cg.aieecosystemapp.utility.html;


public class AieHtmlReponseBody<T>{

	private T data;
	private int code;
    private String message;
    
 
	public AieHtmlReponseBody(String message, int code, T data) {
	
		this.message = message;
		this.code = code;
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
}
