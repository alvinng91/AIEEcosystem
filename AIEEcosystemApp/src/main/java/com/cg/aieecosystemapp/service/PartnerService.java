package com.cg.aieecosystemapp.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cg.aieecosystemapp.dao.PartnerRepository;
import com.cg.aieecosystemapp.model.Partner;

@Service
public class PartnerService {

	@Autowired
	private PartnerRepository repository;
	
	public Partner createPartner(String name, LocalDate foundingDate, String foundBy, String url,
			List<String> technologyTagList, List<String> industryList) {

		Partner partner = new Partner();
		partner.setName(name);
		partner.setFoundingDate(Date.from(foundingDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		partner.setFoundBy(foundBy);
		partner.setUrl(url);
		
		partner.setTechnologyTags(technologyTagList.get(0));
		partner.setIndustries(industryList.get(0));
		
		return partner;
	}

	public List<Partner> searchPartnersByTag(String [] tagNames) {

		return repository.findPartnersByTechnologyTags(tagNames);
	}

}
