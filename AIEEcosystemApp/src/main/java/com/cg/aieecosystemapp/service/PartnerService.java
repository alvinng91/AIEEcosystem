package com.cg.aieecosystemapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.dao.IndustryTagRepository;
import com.cg.aieecosystemapp.dao.PartnerRepository;
import com.cg.aieecosystemapp.dao.TechnologyTagRepository;
import com.cg.aieecosystemapp.model.IndustryTag;
import com.cg.aieecosystemapp.model.Partner;
import com.cg.aieecosystemapp.model.TechnologyTag;
import com.cg.aieecosystemapp.utility.AieUtility;

@Service
public class PartnerService {

	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private TechnologyTagRepository technologyTagRepository;

	@Autowired
	private IndustryTagRepository indutryTagRepository;

	public Partner createPartner(String name, String foundingDate, String foundBy, String url,String location, String description,
			List<String> technologyTagNames, List<String> industryTagNames) throws ParseException {

		Partner partner = new Partner();
		partner.setName(name);

		Date parsedFoundingDate = AieUtility.stringToDateFormatter(foundingDate);

		partner.setFoundingDate(parsedFoundingDate);
		partner.setFoundBy(foundBy);
		partner.setUrl(url);
		partner.setLocation(location);
		partner.setDescription(description);

		List<TechnologyTag> technologyTagObjects = technologyTagRepository.findByNameIn(technologyTagNames);
		List<IndustryTag> industryTagObjects = indutryTagRepository.findByNameIn(industryTagNames);

		partner.setTechnologyTags(technologyTagObjects);
		partner.setIndustries(industryTagObjects);

		partner = partnerRepository.save(partner);

		return partner;
	}

	public List<Partner> searchPartnersByTechnologyTag(List<String> tagNames) {

		return partnerRepository.findByTechnologyTagsNameIn(tagNames);
	}

	public List<Partner> searchPartnersByIndustryTag(List<String> industryTags) {

		return partnerRepository.findByIndustryTagsNameIn(industryTags);
	}

}
