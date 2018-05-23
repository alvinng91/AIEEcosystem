package com.cg.aieecosystemapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.TechnologyTag;
import com.cg.aieecosystemapp.service.TechnologyTagService;


@RestController
@RequestMapping(path = "/api/technologytag")
public class TechnologyTagController {

	@Autowired
	TechnologyTagService service;

	@RequestMapping(method = RequestMethod.POST)
	public TechnologyTag createTechnologyTag( String name, String description,
			HttpServletResponse response) {

		return service.createTechnologyTag(name, description);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<TechnologyTag> retrieveTechnologyTagsFromName(String name, HttpServletResponse response) {

		return service.searchTechnologyTagByName(name);
	}

}
