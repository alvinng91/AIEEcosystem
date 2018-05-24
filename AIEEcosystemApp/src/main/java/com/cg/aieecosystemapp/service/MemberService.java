package com.cg.aieecosystemapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.AieExceptionClass;
import com.cg.aieecosystemapp.dao.MemberRepository;
import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.utility.AieUtility;

@Service
public class MemberService
{
    @Autowired
    private MemberRepository repository;

    public Member createMember(String firstName, String lastName, String position, String email, String tier,
	    String password)
    {
	if (!AieUtility.isMemberEmailCorrect(email))
	{
	    throw new AieExceptionClass("Invalid email : '" + email + "'!! Try again with valid email");
	}

	Boolean memberExists = repository.existsByEmail(email);

	if (!memberExists)
	{
	    Member newMember = new Member(firstName, lastName, position, email, tier, password, false);

	    if (AieUtility.validateMemberBeforeCreation(newMember))
	    {
		newMember = repository.save(newMember);
		return newMember;
	    }
	    else
	    {
		throw new AieExceptionClass("Member has invalid data!! Verify Data and try again!!");
	    }
	}
	else
	{
	    throw new AieExceptionClass("Member with email '" + email + "' already exist!!");
	}

    }

    public Member authenticateMember(String email, String password)
    {
	if (!(AieUtility.isMemberEmailCorrect(email) && AieUtility.isMemberPasswordCorrect(password)))
	{
	    throw new AieExceptionClass("Invalid Email or Password!! Try Again!!");
	}

	Member correctMember = repository.findByEmailAndPassword(email, password);

	if (correctMember != null)
	{
	    return correctMember;
	}
	else
	{
	    throw new AieExceptionClass("Email/Password is incorrect!!");
	}
    }

    public Member getExistingMember(String email)
    {
	if (!AieUtility.isMemberEmailCorrect(email))
	{
	    throw new AieExceptionClass("Invalid email : '" + email + "'!! Try again with valid email");
	}

	Member existingMember = repository.findByEmail(email);

	if (existingMember != null)
	{
	    return existingMember;
	}
	else
	{
	    throw new AieExceptionClass("Member with email '" + email + "' does not exist!!");
	}
    }

    public Member updateMemberTier(String email, String tier)
    {
	if (!(AieUtility.isMemberEmailCorrect(email) && AieUtility.isMemberTierCorrect(tier)))
	{
	    throw new AieExceptionClass("Invalid email ('" + email + "') or tier '" + tier + "')!! Try Again!!");
	}

	Member existingMember = repository.findByEmail(email);

	if (existingMember != null)
	{
	    existingMember.setTier(tier);
	    existingMember = repository.save(existingMember);
	    return existingMember;
	}
	else
	{
	    throw new AieExceptionClass("Member with email '" + email + "' does not exist to update!!");
	}
    }

    public void deleteExistingMember(String email)
    {
	if (!AieUtility.isMemberEmailCorrect(email))
	{
	    throw new AieExceptionClass("Invalid email : '" + email + "'!! Try again with valid email");
	}

	Member existingMember = repository.findByEmail(email);

	if (existingMember == null)
	{
	    throw new AieExceptionClass("Member with email '" + email + "' does not exist to delete!!");
	}
	else
	{
	    repository.delete(existingMember);
	}
    }
}
