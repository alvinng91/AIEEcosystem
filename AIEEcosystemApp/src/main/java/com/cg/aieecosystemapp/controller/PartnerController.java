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

import com.cg.aieecosystemapp.model.Partner;
import com.cg.aieecosystemapp.service.PartnerService;
import com.cg.aieecosystemapp.utility.html.AieHtmlReponseBody;
import com.cg.aieecosystemapp.utility.html.AieHtmlStatusCode;

@RestController
@RequestMapping(path = "/api/partner")
public class PartnerController {

	@Autowired
	private PartnerService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createPartner(@RequestBody Partner partner) {

		return new ResponseEntity<>(new AieHtmlReponseBody<>(AieHtmlStatusCode.STATUS_OK.toString(),
				AieHtmlStatusCode.STATUS_OK.toCode(), service.createPartner(partner)), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> fetch(@RequestParam List<String> technologyTags, @RequestParam List<String> industryTags) {

		return new ResponseEntity<>(new AieHtmlReponseBody<>(AieHtmlStatusCode.STATUS_OK.toString(),
				AieHtmlStatusCode.STATUS_OK.toCode(), service.searchPartnersByTags(technologyTags, industryTags)),
				HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Partner partner) {

		return new ResponseEntity<>(new AieHtmlReponseBody<>(AieHtmlStatusCode.STATUS_OK.toString(),
				AieHtmlStatusCode.STATUS_OK.toCode(), service.updatePartner(partner)), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePartner(int partnerId) {

		return new ResponseEntity<>(new AieHtmlReponseBody<>(AieHtmlStatusCode.STATUS_OK.toString(),
				AieHtmlStatusCode.STATUS_OK.toCode(), service.deletePartner(partnerId)), HttpStatus.OK);

	}

}
