package com.cg.aieecosystemapp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aieecosystemapp.model.Partner;
import com.cg.aieecosystemapp.service.PartnerService;

@RestController
@RequestMapping(path = "/AIEEcosystem")
public class PartnerController {

	@Autowired
	PartnerService service;

	@RequestMapping(method = RequestMethod.POST, value = "/partner") 
	public Partner createPartner(String name, String foundingDate, String foundBy, String url,
			String[] technologyTagNames, String[] industryNames, HttpServletResponse response) {

		LocalDate parsedFoundingDate = LocalDate.parse(foundingDate);

		List<String> technologyTagList = new ArrayList<String>();
		Collections.addAll(technologyTagList, technologyTagNames);

		List<String> industryList = new ArrayList<String>();
		Collections.addAll(industryList, industryNames);

		return service.createPartner(name, parsedFoundingDate, foundBy, url, technologyTagList, industryList);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/partner") 
	public List<Partner> retrievePartnersFromTagSearch(String[] technologyTag, HttpServletResponse response) {
    
		
		return service.searchPartnersByTag(technologyTag);
	}

}
