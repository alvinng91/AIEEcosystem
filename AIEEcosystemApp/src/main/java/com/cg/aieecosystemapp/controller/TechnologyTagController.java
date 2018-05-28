package com.cg.aieecosystemapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.TechnologyTag;
import com.cg.aieecosystemapp.service.TechnologyTagService;

@RestController
@RequestMapping(path = "/api/technologytag")
public class TechnologyTagController
{

	@Autowired
	TechnologyTagService service;

	@RequestMapping(method = RequestMethod.POST)
	public TechnologyTag create(@RequestBody TechnologyTag tag)
	{
		return service.createTechnologyTag(tag);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<TechnologyTag> retrieveAll()
	{
		return service.retrieveAllTechnologyTags();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public TechnologyTag update(@RequestBody TechnologyTag tag)
	{
		return service.updateTechnologyTag(tag);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@RequestBody List<TechnologyTag> tags)
	{
		service.deleteTechnologyTags(tags);
	}

}
