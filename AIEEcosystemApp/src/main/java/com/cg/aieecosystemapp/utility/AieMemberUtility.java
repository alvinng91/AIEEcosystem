package com.cg.aieecosystemapp.utility;

import java.util.regex.Pattern;

import com.cg.aieecosystemapp.aieexception.AieInvalidFieldsException;
import com.cg.aieecosystemapp.model.Member;

public class AieMemberUtility
{
    private static String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
	    + "A-Z]{2,7}$";

    private static String emailDomain = "@capgemini.com";

    private AieMemberUtility()
    {
	throw new IllegalStateException("Cannot Instantiate AieMemberUtility Class");
    }

    public static boolean validateMemberObject(Member aMember)
    {
	if (aMember.getFirstName() == null || aMember.getFirstName().isEmpty())
	{
	    throw new AieInvalidFieldsException("Error : First name is invalid : " + aMember.getFirstName());
	}

	if (aMember.getLastName() == null || aMember.getLastName().isEmpty())
	{
	    throw new AieInvalidFieldsException("Error : Last name is invalid : " + aMember.getLastName());
	}

	if (aMember.getPosition() == null || aMember.getPosition().isEmpty())
	{
	    throw new AieInvalidFieldsException("Error : Position is invalid : " + aMember.getPosition());
	}

	if (!isMemberEmailCorrect(aMember.getEmail()))
	{
	    throw new AieInvalidFieldsException("Error : Email is invalid : " + aMember.getEmail());
	}

	if (!isMemberTierCorrect(aMember.getTier()))
	{
	    throw new AieInvalidFieldsException("Error : Tier is invalid : " + aMember.getTier());
	}

	if (!isMemberPasswordCorrect(aMember.getPassword()))
	{
	    throw new AieInvalidFieldsException("Error : Password is invalid : " + aMember.getPassword());
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
