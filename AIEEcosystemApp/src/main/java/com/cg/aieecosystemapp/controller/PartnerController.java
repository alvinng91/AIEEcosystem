package com.cg.aieecosystemapp.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.IndustryTag;
import com.cg.aieecosystemapp.model.Partner;
import com.cg.aieecosystemapp.model.TechnologyTag;
import com.cg.aieecosystemapp.service.PartnerService;

@RestController
@RequestMapping(path = "/api/partner")
public class PartnerController {

	@Autowired
	private PartnerService service;

	

	@RequestMapping(method = RequestMethod.POST)
	public Partner createPartner(@RequestBody Partner partner)
			throws ParseException, JSONException, org.springframework.boot.configurationprocessor.json.JSONException {
		
		return service.createPartner(partner);
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public List<Partner> fetch(@RequestParam List<String> technologyTags, @RequestParam List<String> industryTags) {

		return service.searchPartnersByTags(technologyTags, industryTags);

	}

	@RequestMapping(method = RequestMethod.PUT)
	public Partner updatePartnerDescription(int partnerId, String name, String foundingDate, String foundBy, String url,
			String location, String description, @RequestParam List<String> technologyTagNames,
			@RequestParam List<String> industryTagNames) throws ParseException {

		return service.updatePartner(partnerId, name, foundingDate, foundBy, url, location, description,
				technologyTagNames, industryTagNames);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deletePartner(int partnerId) throws ParseException {

		service.deletePartner(partnerId);
	}

}
