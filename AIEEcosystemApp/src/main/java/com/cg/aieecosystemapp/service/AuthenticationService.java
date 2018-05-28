package com.cg.aieecosystemapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.AieAuthenticationException;
import com.cg.aieecosystemapp.aieexception.AieInvalidFieldsException;
import com.cg.aieecosystemapp.dao.MemberRepository;
import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.utility.AieMemberUtility;
import com.mysql.jdbc.StringUtils;

@Service
public class AuthenticationService {
	
	@Autowired
    private MemberRepository repository;
	
	public boolean authenthicateMember(Member member)
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
		
		return true;
	}
	

}
