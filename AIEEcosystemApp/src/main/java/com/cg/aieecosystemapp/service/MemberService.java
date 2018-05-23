package com.cg.aieecosystemapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	Member newMember = new Member(firstName, lastName, position, email, tier, password, false);
	repository.save(newMember);
	
	return newMember;
    }
    
    public Member findMember(String email, String password)
    {
	return repository.findByEmailAndPassword(email, password);
    }
}
