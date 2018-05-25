package com.cg.aieecosystemapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.Partner;
import com.cg.aieecosystemapp.service.PartnerService;

@RestController
@RequestMapping(path = "/api/partner")
public class PartnerController {

	@Autowired
	private PartnerService service;

	@RequestMapping(method = RequestMethod.POST)
	public Partner createPartner(String name, String foundingDate, String foundBy, String url, String location,
			String description, @RequestParam List<String> technologyTagNames, @RequestParam List<String> industryNames
			) throws ParseException {

		return service.createPartner(name, foundingDate, foundBy, url,location,description, technologyTagNames, industryNames);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/technologytag")
	public List<Partner> retrievePartnersFromTechnologyTag(@RequestParam List<String> technologyTags
			) {

		return service.searchPartnersByTechnologyTag(technologyTags);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/industrytag")
	public List<Partner> retrievePartnersFromIndustryTag(@RequestParam List<String> industryTags
			) {

		return service.searchPartnersByIndustryTag(industryTags);
	}

}
