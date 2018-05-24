package com.cg.aieecosystemapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.IndustryTag;
import com.cg.aieecosystemapp.model.TechnologyTag;
import com.cg.aieecosystemapp.service.IndustryTagService;

@RestController
@RequestMapping(path = "/api/industry")
public class IndustryTagController {

	@Autowired
	private IndustryTagService service;

	@RequestMapping(method = RequestMethod.POST)
	public IndustryTag createIndustryTag(String name, String description, HttpServletResponse response) {

		return service.createIndustryTag(name, description);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<IndustryTag> retrieveIndustryTagsFromName(@RequestParam List<String> names, HttpServletResponse response) {

		return service.searchIndustryTagByName(names);
	}

}
