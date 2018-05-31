package com.cg.aieecosystemapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.service.AuthenticationMemberService;
import com.cg.aieecosystemapp.utility.html.AieHtmlReponseBody;
import com.cg.aieecosystemapp.utility.html.AieHtmlStatusCode;

@RestController
@RequestMapping(path = "/api/member/authenticate")
public class AuthenticateMemberController {

	@Autowired
	private AuthenticationMemberService service;
	
	
	@RequestMapping(value = "/{memberId}", method = RequestMethod.POST)
	public ResponseEntity<?> authenticateMember(@PathVariable(value = "memberId") int memberId,
			@RequestBody Member member) {
		
		return new ResponseEntity<>(new AieHtmlReponseBody<>(AieHtmlStatusCode.STATUS_OK.toString(),
				AieHtmlStatusCode.STATUS_OK.toCode(), service.authenthicateMember(memberId,member)),
					HttpStatus.OK);

	}
}
