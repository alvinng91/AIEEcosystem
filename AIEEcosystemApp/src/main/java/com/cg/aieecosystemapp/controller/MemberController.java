package com.cg.aieecosystemapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.service.MemberService;
import com.cg.aieecosystemapp.utility.html.AieHtmlReponseBody;
import com.cg.aieecosystemapp.utility.html.AieHtmlStatusCode;

@RestController
@RequestMapping(path = "/api/member")
public class MemberController
{
    @Autowired
    private MemberService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createMember(@RequestBody Member aMember)
    {
	/*
	 * try { Member newMember = service.createMember(aMember); return new
	 * ResponseEntity<>(newMember, HttpStatus.CREATED); } catch (Exception
	 * e) { return new ResponseEntity<>("Could not create new Member : " +
	 * e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
	 */
	return new ResponseEntity<>(new AieHtmlReponseBody<>(AieHtmlStatusCode.STATUS_OK.toString(),
		AieHtmlStatusCode.STATUS_OK.toCode(), service.createMember(aMember)), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllMember(String searchQuery, String position)
    {
	List<Member> listOfExistingMembers = null;

	listOfExistingMembers = service.getFilteredMembers(searchQuery, position);

	if (listOfExistingMembers.isEmpty())
	{
	    return new ResponseEntity<>(new AieHtmlReponseBody<>("Could not Find Member(s) based on Search Criteria!!",
		    AieHtmlStatusCode.ENTRY_NOT_FOUND.toCode(), null), HttpStatus.OK);
	}
	else
	{
	    return new ResponseEntity<>(new AieHtmlReponseBody<>(AieHtmlStatusCode.STATUS_OK.toString(),
		    AieHtmlStatusCode.STATUS_OK.toCode(), listOfExistingMembers), HttpStatus.OK);
	}
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateMember(@RequestBody Member updateMember)
    {
	return new ResponseEntity<>(new AieHtmlReponseBody<>(AieHtmlStatusCode.STATUS_OK.toString(),
		AieHtmlStatusCode.STATUS_OK.toCode(), service.updateMemberTier(updateMember)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteExistingMember(@RequestBody List<Member> deleteMemberList)
    {
	return new ResponseEntity<>(new AieHtmlReponseBody<>(AieHtmlStatusCode.STATUS_OK.toString(),
		AieHtmlStatusCode.STATUS_OK.toCode(), service.deleteExistingMember(deleteMemberList)), HttpStatus.OK);
    }

}
