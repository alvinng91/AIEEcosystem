package com.cg.aieecosystemapp.partnerutility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.cg.aieecosystemapp.model.Member;

public class AieUtility
{
    private static String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
	    + "A-Z]{2,7}$";

    private static String emailDomain = "@capgemini.com";

    private AieUtility()
    {
	throw new IllegalStateException("Cannot Instantciate AieUtility Class");
    }

    public static Date stringToDateFormatter(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date parsedDate = formatter.parse(date);

		return parsedDate;

	}
    
    public static boolean validateMemberBeforeCreation(Member aMember)
    {
	if (aMember.getFirstName() == null || aMember.getFirstName().isEmpty())
	{
	    return false;
	}

	if (aMember.getLastName() == null || aMember.getLastName().isEmpty())
	{
	    return false;
	}

	if (aMember.getPosition() == null || aMember.getPosition().isEmpty())
	{
	    return false;
	}

	if (!isMemberEmailCorrect(aMember.getEmail()))
	{
	    return false;
	}

	if (!isMemberTierCorrect(aMember.getTier()))
	{
	    return false;
	}

	if (!isMemberPasswordCorrect(aMember.getPassword()))
	{
	    return false;
	}

	return true;
    }

    public static boolean isMemberTierCorrect(String tier)
    {
	return !(tier == null || tier.isEmpty());
    }
    
    public static boolean isMemberPasswordCorrect(String password)
    {
	return !(password == null || password.isEmpty());
    }

    public static boolean isMemberEmailCorrect(String email)
    {
	if (email == null || email.isEmpty())
	{
	    return false;
	}
	else
	{
	    Pattern pat = Pattern.compile(emailRegex);
	    boolean emailPatternMatched = pat.matcher(email).matches();

	    if (!emailPatternMatched)
	    {
		return false;
	    }

	    if (email.toLowerCase().indexOf(emailDomain) < 0)
	    {
		return false;
	    }
	}

	return true;
    }
}
