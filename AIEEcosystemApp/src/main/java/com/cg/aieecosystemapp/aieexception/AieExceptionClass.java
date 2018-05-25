package com.cg.aieecosystemapp.aieexception;

public class AieExceptionClass extends RuntimeException
{
    public AieExceptionClass(String customErrorMessage)
    {
	super(customErrorMessage);
    }
}
