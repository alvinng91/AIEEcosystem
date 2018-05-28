package com.cg.aieecosystemapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<?> createMember(@RequestBody Member aMember)
    {
	try
	{
	    Member newMember = service.createMember(aMember);
	    return new ResponseEntity<>(newMember, HttpStatus.CREATED);
	}
	catch (Exception e)
	{
	    return new ResponseEntity<>("Could not create new Member : " + e.getMessage(),
		    HttpStatus.INTERNAL_SERVER_ERROR);
	}

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllMember(String searchQuery, String position)
    {
	List<Member> listOfExistingMembers = null;

	listOfExistingMembers = service.getFilteredMembers(searchQuery, position);

	if (listOfExistingMembers.isEmpty())
	{
	    return new ResponseEntity<>("Could not Find Member(s) based on Search Criteria!!", HttpStatus.NOT_FOUND);
	}
	else
	{
	    return new ResponseEntity<>(listOfExistingMembers, HttpStatus.FOUND);
	}
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateMember(@RequestBody Member updateMember)
    {
	try
	{
	    Member existingMember = service.updateMemberTier(updateMember);
	    return new ResponseEntity<>(existingMember, HttpStatus.OK);
	}
	catch (Exception e)
	{
	    return new ResponseEntity<>("Could not Update Member : " + e.getMessage(),
		    HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteExistingMember(@RequestParam List<Integer> listOfIds)
    {
	try
	{
	    service.deleteExistingMember(listOfIds);
	    return new ResponseEntity<>(null, HttpStatus.OK);
	}
	catch (Exception e)
	{
	    return new ResponseEntity<>("Could not Delete Member : " + e.getMessage(),
		    HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

}
