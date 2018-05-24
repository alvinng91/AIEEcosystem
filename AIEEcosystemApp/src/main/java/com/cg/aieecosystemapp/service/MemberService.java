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
		Boolean memberExisted = repository.existsByEmail(email);

		if (!memberExisted)
		{
			Member newMember = new Member(firstName, lastName, position, email, tier, password, false);
			newMember = repository.save(newMember);
			return newMember;
		}
		else
		{
			throw new AieExceptionClass("Member with email '" + email + "' already exist!!");
		}

	}

	public Member authenticateMember(String email, String password)
	{
		Member correctMember = repository.findByEmailAndPassword(email, password);

		if (correctMember != null)
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

	public Member updateMemberTier(String email, int tier)
	{
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
