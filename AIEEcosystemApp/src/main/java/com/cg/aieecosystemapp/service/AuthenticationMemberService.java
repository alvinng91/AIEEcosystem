package com.cg.aieecosystemapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.AieAuthenticationException;
import com.cg.aieecosystemapp.aieexception.AieEntryNotFoundException;
import com.cg.aieecosystemapp.aieexception.AieInvalidFieldsException;
import com.cg.aieecosystemapp.dao.MemberRepository;
import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.utility.AieMemberUtility;
import com.mysql.jdbc.StringUtils;

@Service
public class AuthenticationMemberService {
	
	@Autowired
    private MemberRepository repository;
	
	public boolean authenthicateMember(int memberId, Member member)
	{
		if(member == null)
			throw new AieInvalidFieldsException("member is null");
		
		if(StringUtils.isNullOrEmpty(member.getEmail()))
			throw new AieInvalidFieldsException("email is null or empty");
		
		if(StringUtils.isNullOrEmpty(member.getPassword()))
			throw new AieInvalidFieldsException("password is null or empty");
		
		if(StringUtils.isNullOrEmpty(member.getFirstName()))
			throw new AieInvalidFieldsException("firstname is null or empty");
		
		if(StringUtils.isNullOrEmpty(member.getLastName()))
			throw new AieInvalidFieldsException("lastname is null or empty");
		
		if(StringUtils.isNullOrEmpty(member.getPosition()))
			throw new AieInvalidFieldsException("position is null or empty");
		
		if(StringUtils.isNullOrEmpty(member.getTier()))
			throw new AieInvalidFieldsException("tier is null or empty");
		
		if (!AieMemberUtility.isMemberEmailCorrect(member.getEmail()))		
			throw new AieAuthenticationException("email is invalid");
		
		
		Member memberFound = repository.findById(memberId)
				.orElseThrow(() -> new AieEntryNotFoundException("member does not exist"));
		
		if(!memberFound.getEmail().equals(member.getEmail()))
		{
			throw new AieAuthenticationException("email is different");
		}
		if(!memberFound.getPassword().equals(member.getPassword()))
		{
			throw new AieAuthenticationException("password is different");
		}
		if(!memberFound.getFirstName().equals(member.getFirstName()))
		{
			throw new AieAuthenticationException("firstname is different");
		}
		if(!memberFound.getLastName().equals(member.getLastName()))
		{
			throw new AieAuthenticationException("lastname is different");
		}
		if(!memberFound.getPosition().equals(member.getPosition()))
		{
			throw new AieAuthenticationException("position is different");
		}
		if(!memberFound.getTier().equals(member.getTier()))
		{
			throw new AieAuthenticationException("tier is different");
		}
		
		return true;
	}
	

}
