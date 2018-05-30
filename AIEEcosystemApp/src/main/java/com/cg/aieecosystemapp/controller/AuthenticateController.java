package com.cg.aieecosystemapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.service.AuthenticationService;
import com.cg.aieecosystemapp.utility.html.AieHtmlReponseBody;
import com.cg.aieecosystemapp.utility.html.AieHtmlStatusCode;

@RestController
@RequestMapping(path = "/api/authenticate")
public class AuthenticateController {

	@Autowired
	private AuthenticationService service;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> authenticateMember(@RequestBody Member member) {
		
		return new ResponseEntity<>(new AieHtmlReponseBody(AieHtmlStatusCode.STATUS_OK.toString(),
				AieHtmlStatusCode.STATUS_OK.toCode(), service.authenthicateMember(member)),
					HttpStatus.OK);

	}
}
