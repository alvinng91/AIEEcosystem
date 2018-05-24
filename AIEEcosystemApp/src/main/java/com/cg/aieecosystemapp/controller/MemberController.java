package com.cg.aieecosystemapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.service.MemberService;

@RestController
@RequestMapping(path = "/api/member")
public class MemberController
{
    @Autowired
    private MemberService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createMember(String firstName, String lastName, String position, String email, String tier,
	    String password)
    {

	try
	{
	    Member m = service.createMember(firstName, lastName, position, email, tier, password);
	    return new ResponseEntity<>(m, HttpStatus.CREATED);
	}
	catch (Exception e)
	{
	    return new ResponseEntity<>("Could not create new Member : " + e.getMessage(),
		    HttpStatus.INTERNAL_SERVER_ERROR);
	}

    }

    @RequestMapping(method = RequestMethod.GET, value = "/authenticate")
    public ResponseEntity<?> authMember(String email, String password)
    {

	try
	{
	    Member m = service.authenticateMember(email, password);
	    return new ResponseEntity<>(m, HttpStatus.OK);
	}
	catch (Exception e)
	{
	    return new ResponseEntity<>("Could not Authenticate Member : " + e.getMessage(), HttpStatus.FORBIDDEN);
	}
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getMember(String email)
    {
	try
	{
	    Member m = service.getExistingMember(email);
	    return new ResponseEntity<>(m, HttpStatus.FOUND);
	}
	catch (Exception e)
	{
	    return new ResponseEntity<>("Could not Get Member : " + e.getMessage(), HttpStatus.NOT_FOUND);
	}

    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateMember(String email, String tier)
    {

	try
	{
	    Member m = service.updateMemberTier(email, tier);
	    return new ResponseEntity<>(m, HttpStatus.OK);
	}
	catch (Exception e)
	{
	    return new ResponseEntity<>("Could not Update Member : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteExistingMember(String email)
    {
	try
	{
	    service.deleteExistingMember(email);
	    return new ResponseEntity<>(null, HttpStatus.OK);
	}
	catch (Exception e)
	{
	    return new ResponseEntity<>("Could not Delete Member : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

}
