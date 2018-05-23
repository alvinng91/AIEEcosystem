package com.cg.aieecosystemapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.aieexception.AieExceptionClass;
import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.service.MemberService;

@RestController
@RequestMapping(path = "/api/member")
public class MemberController
{
    @Autowired
    private MemberService service;

    @RequestMapping(method = RequestMethod.POST)
    public Member createMember(String firstName, String lastName, String position, String email, int tier,
	    String password)
    {
	return service.createMember(firstName, lastName, position, email, tier, password);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Member updateMember(String email, int tier)
    {
	return service.updateMemberTier(email, tier);
    }
}
