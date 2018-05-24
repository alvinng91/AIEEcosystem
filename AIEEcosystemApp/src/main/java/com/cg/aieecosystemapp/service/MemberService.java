package com.cg.aieecosystemapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.AieExceptionClass;
import com.cg.aieecosystemapp.dao.MemberRepository;
import com.cg.aieecosystemapp.model.Member;

@Service
public class MemberService
{
    @Autowired
    private MemberRepository repository;

    public Member createMember(String firstName, String lastName, String position, String email, int tier,
	    String password)
    {
	Member existingMember = findMemberUsingEmail(email);

	if (existingMember == null)
	{
	    Member newMember = new Member(firstName, lastName, position, email, tier, password, false);
	    repository.save(newMember);
	    return newMember;
	}
	else
	{
	    throw new AieExceptionClass("Member with email '" + email + "' already exist!!");
	}

    }

    private Member findMemberUsingEmailAuth(String email, String password)
    {
	return repository.findByEmailAndPassword(email, password);
    }

    private Member findMemberUsingEmail(String email)
    {
	return repository.findByEmail(email);
    }
    
    public Member authenticateMember(String email, String password)
    {
	Member correctMember = repository.findByEmailAndPassword(email, password);
	
	if(correctMember != null)
	{
	    return correctMember;
	}
	else
	{
	    throw new AieExceptionClass("Username/Password is incorrect!!");
	}
    }

    public Member getExistingMember(String email)
    {
	Member existingMember = findMemberUsingEmail(email);

	if (existingMember != null)
	{
	    return existingMember;
	}
	else
	{
	    throw new AieExceptionClass("Member with email '" + email + "' does not exist!!");
	}
    }

    public Member updateMemberTier(String email, int tier)
    {
	Member existingMember = findMemberUsingEmail(email);

	if (existingMember != null)
	{
	    existingMember.setTier(tier);
	    repository.save(existingMember);
	    return existingMember;
	}
	else
	{
	    throw new AieExceptionClass("Member with email '" + email + "' does not exist to update!!");
	}
    }

    public Member deleteExistingMember(String email)
    {
	Member existingMember = findMemberUsingEmail(email);

	if (existingMember == null)
	{
	    throw new AieExceptionClass("Member with email '" + email + "' does not exist to delete!!");
	}
	else
	{
	     repository.delete(existingMember); 
	     return null;
	}
    }
}
