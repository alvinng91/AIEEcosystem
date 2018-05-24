package com.cg.aieecosystemapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.service.MemberService;

@RestController
@RequestMapping(path = "/api/member")
public class MemberController {
	@Autowired
	private MemberService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createMember(String firstName, String lastName, String position, String email, int tier,
			String password) {
		
		try{
			Member m = service.createMember(firstName, lastName, position, email, tier, password);
			return new ResponseEntity<>(m, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>("create fail" ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/authenticate")
	public ResponseEntity<?> authMember(String email, String password) {
		
		try{
			Member m = service.authenticateMember(email, password);
			return new ResponseEntity<>(m, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>("auth member fail" ,HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getMember(String email) {
		
		try{
			Member m = service.getExistingMember(email);
			return new ResponseEntity<>(m, HttpStatus.FOUND);
		}
		catch(Exception e) {
			return new ResponseEntity<>("get member fail",HttpStatus.NOT_FOUND);
		}
		
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateMember(String email, int tier) {
		
		try{
			Member m = service.updateMemberTier(email, tier);
			return new ResponseEntity<>(m, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>("update fail",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteExistingMember(String email) {
		try{
			Member m = service.deleteExistingMember(email);
			return new ResponseEntity<>(m, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>("delete fail",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
