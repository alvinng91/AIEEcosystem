package com.cg.aieecosystemapp.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AieUtility
{
    private AieUtility()
    {
	throw new IllegalStateException("Cannot Instantiate AieUtility Class");
    }

    public static Date stringToDateFormatter(String date) throws ParseException
    {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	Date parsedDate = formatter.parse(date);

	return parsedDate;
    }
}
