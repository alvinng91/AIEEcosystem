package com.cg.aieecosystemapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.TechnologyTag;
import com.cg.aieecosystemapp.service.TechnologyTagService;
import com.cg.aieecosystemapp.utility.html.AieHtmlReponseBody;
import com.cg.aieecosystemapp.utility.html.AieHtmlStatusCode;

@RestController
@RequestMapping(path = "/api/technologytag")
public class TechnologyTagController
{

	@Autowired
	TechnologyTagService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody TechnologyTag tag)
	{
		return new ResponseEntity<>(new AieHtmlReponseBody(AieHtmlStatusCode.CREATE_OK.toString(),
				AieHtmlStatusCode.CREATE_OK.toCode(), service.createTechnologyTag(tag)), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAll()
	{
		return new ResponseEntity<>(new AieHtmlReponseBody(AieHtmlStatusCode.READ_OK.toString(),
				AieHtmlStatusCode.READ_OK.toCode(), service.retrieveAllTechnologyTags()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody TechnologyTag tag)
	{
		return new ResponseEntity<>(new AieHtmlReponseBody(AieHtmlStatusCode.UPDATE_OK.toString(),
				AieHtmlStatusCode.UPDATE_OK.toCode(), service.updateTechnologyTag(tag)), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@RequestBody List<TechnologyTag> tags)
	{
		int deletedRowsCount = service.deleteTechnologyTags(tags);
		return new ResponseEntity<>(new AieHtmlReponseBody(deletedRowsCount + " row(s) deleted.",
				AieHtmlStatusCode.DELETE_OK.toCode(), deletedRowsCount), HttpStatus.OK);
	}

}
