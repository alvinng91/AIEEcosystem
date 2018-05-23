package com.cg.aieecosystemapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.dao.PartnerRepository;
import com.cg.aieecosystemapp.model.IndustryTag;
import com.cg.aieecosystemapp.model.Partner;
import com.cg.aieecosystemapp.model.TechnologyTag;

@Service
public class PartnerService {

	@Autowired
	private PartnerRepository repository;
	
	public Partner createPartner(String name, LocalDate foundingDate, String foundBy, String url,
			List<String> technologyTagNames, List<String> industryTagNames) {

		Partner partner = new Partner();
		partner.setName(name);
		partner.setFoundingDate(foundingDate);
		partner.setFoundBy(foundBy);
		partner.setUrl(url);
		
		List<TechnologyTag> technologyTagObjects = new ArrayList<>();
		List<IndustryTag> industryTagObjects = new ArrayList<>();
		
		for(int i=0;i<technologyTagNames.size();i++) {
			String tag = technologyTagNames.get(i);
			TechnologyTag tagObject = new TechnologyTag();
			tagObject.setName(name);
			technologyTagObjects.add(tagObject);
			
		}
		
		for(int i=0;i<industryTagNames.size();i++) {
			String tag = industryTagNames.get(i);
			IndustryTag tagObject = new IndustryTag();
			tagObject.setName(name);
			industryTagObjects.add(tagObject);
			
		}
		
		partner.setTechnologyTags(technologyTagObjects);
		partner.setIndustries(industryTagObjects);
		
		partner = repository.save(partner);
		
		return partner;
	}

	public List<Partner> searchPartnersByTag(String [] tagNames) {

		return repository.findPartnersByTechnologyTags(tagNames);
	}

}
