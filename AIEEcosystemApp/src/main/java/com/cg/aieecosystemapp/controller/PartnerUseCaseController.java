package com.cg.aieecosystemapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.model.PartnerUseCase;
import com.cg.aieecosystemapp.service.PartnerUseCaseService;
import com.cg.aieecosystemapp.utility.html.AieHtmlReponseBody;
import com.cg.aieecosystemapp.utility.html.AieHtmlStatusCode;

@RestController
@RequestMapping(path = "/api/partner/partnerUseCase")
public class PartnerUseCaseController {

	@Autowired
	private PartnerUseCaseService service;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createUseCase(@RequestBody PartnerUseCase useCase) {
		
		return new ResponseEntity<>(new AieHtmlReponseBody<>(AieHtmlStatusCode.STATUS_OK.toString(),
				AieHtmlStatusCode.STATUS_OK.toCode(), service.createUseCase(useCase)),
					HttpStatus.OK);

	}
	@RequestMapping(value = "/{useCaseId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePartnerUseCase(@PathVariable(value = "useCaseId") int useCaseId,
			@RequestBody PartnerUseCase useCase) {
		
		return new ResponseEntity<>(new AieHtmlReponseBody<>(AieHtmlStatusCode.STATUS_OK.toString(),
				AieHtmlStatusCode.STATUS_OK.toCode(), service.updatePartnerUseCase(useCase)),
					HttpStatus.OK);

	}
	@RequestMapping(value = "/{useCaseId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePartnerUseCase(@PathVariable(value = "useCaseId") int useCaseId) {
		
		return new ResponseEntity<>(new AieHtmlReponseBody<>(AieHtmlStatusCode.STATUS_OK.toString(),
				AieHtmlStatusCode.STATUS_OK.toCode(), service.deletePartnerUseCase(useCaseId)),
					HttpStatus.OK);

	}
}
