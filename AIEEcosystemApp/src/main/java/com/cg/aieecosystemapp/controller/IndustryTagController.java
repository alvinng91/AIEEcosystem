package com.cg.aieecosystemapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.IndustryTag;
import com.cg.aieecosystemapp.service.IndustryTagService;
import com.cg.aieecosystemapp.utility.html.AieHtmlReponseBody;
import com.cg.aieecosystemapp.utility.html.AieHtmlStatusCode;

@RestController
@RequestMapping(path = "/api/industry")
public class IndustryTagController
{

	@Autowired
	private IndustryTagService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody IndustryTag tag)
	{
		return new ResponseEntity<>(new AieHtmlReponseBody(AieHtmlStatusCode.CREATE_OK.toString(),
				AieHtmlStatusCode.CREATE_OK.toCode(), service.createIndustryTag(tag)), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAll()
	{
		return new ResponseEntity<>(new AieHtmlReponseBody(AieHtmlStatusCode.READ_OK.toString(),
				AieHtmlStatusCode.READ_OK.toCode(), service.retrieveAllIndustryTags()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody IndustryTag tag)
	{
		return new ResponseEntity<>(new AieHtmlReponseBody(AieHtmlStatusCode.UPDATE_OK.toString(),
				AieHtmlStatusCode.UPDATE_OK.toCode(), service.updateIndustryTag(tag)), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@RequestBody List<IndustryTag> tags)
	{
		int deletedRowsCount = service.deleteIndustryTags(tags);
		return new ResponseEntity<>(new AieHtmlReponseBody(deletedRowsCount + " row(s) deleted.",
				AieHtmlStatusCode.DELETE_OK.toCode(), deletedRowsCount), HttpStatus.OK);
	}

}
