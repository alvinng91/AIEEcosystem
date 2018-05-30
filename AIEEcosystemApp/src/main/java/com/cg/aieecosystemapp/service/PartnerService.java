package com.cg.aieecosystemapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.AieExceptionClass;
import com.cg.aieecosystemapp.dao.IndustryTagRepository;
import com.cg.aieecosystemapp.dao.PartnerRepository;
import com.cg.aieecosystemapp.dao.TechnologyTagRepository;
import com.cg.aieecosystemapp.model.IndustryTag;
import com.cg.aieecosystemapp.model.Partner;
import com.cg.aieecosystemapp.model.TechnologyTag;

@Service
public class PartnerService {

	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private TechnologyTagRepository technologyTagRepository;

	@Autowired
	private IndustryTagRepository industryTagRepository;

	public Partner createPartner(Partner partner) {

		List<TechnologyTag> technologyTagNames = partner.getTechnologyTags();
		List<IndustryTag> industryTagNames = partner.getIndustryTags();

		List<String> technologyTagNameList = new ArrayList();
		List<String> industryTagNameList = new ArrayList();

		for (int i = 0; i < technologyTagNames.size(); i++) {
			TechnologyTag tag = technologyTagNames.get(i);
			String tagName = tag.getName();
			tag = technologyTagRepository.findByName(tagName);
			if (tag == null) {
				throw new AieExceptionClass("Technology Tag" + tagName + " does not exist");
			} else {
				technologyTagNameList.add(tagName);
			}

		}

		for (int i = 0; i < industryTagNames.size(); i++) {
			IndustryTag tag = industryTagNames.get(i);
			String tagName = tag.getName();
			tag = industryTagRepository.findByName(tagName);
			if (tag == null) {
				throw new AieExceptionClass("Industry Tag" + tagName + " does not exist");
			} else {
				industryTagNameList.add(tagName);

			}
		}

		technologyTagNames = technologyTagRepository.findByNameIn(technologyTagNameList);
		industryTagNames = industryTagRepository.findByNameIn(industryTagNameList);

		partner.setTechnologyTags(technologyTagNames);
		partner.setIndustryTags(industryTagNames);

		partner = partnerRepository.save(partner);

		return partner;
	}

	

	public boolean deletePartner(int partnerId) {
		
	
		Partner partnerToBeDeleted = partnerRepository.findByPartnerId(partnerId);
		if (partnerToBeDeleted != null) {
			partnerRepository.delete(partnerToBeDeleted);			
			return true;

		} else {

			throw new AieExceptionClass("Partner  with partner id  '" + partnerId + "' does not exist to delete!!");
		}

	}
	

	public List<Partner> searchPartnersByTags(List<String> technologyTags, List<String> industryTags) {

		boolean technology = technologyTags != null && !technologyTags.isEmpty();
		boolean industry = industryTags != null && !industryTags.isEmpty();

		if (technology && industry) {
			// search by technology tags and industry tag
			partnerRepository.findByTechnologyTagsNameInAndIndustryTagsNameIn(technologyTags, industryTags);
		}

		if (industry) {
			// search by industry tags
			return partnerRepository.findByIndustryTagsNameIn(industryTags);
		}

		if (technology) {
			// search by industry tags
			return partnerRepository.findByTechnologyTagsNameIn(technologyTags);
		}

		throw new AieExceptionClass("Invalid Search fields");

	}

	public Partner updatePartner(Partner partner) {

		int partnerId = partner.getPartnerId();

		Partner partnerToBeUpdated = partnerRepository.findByPartnerId(partnerId);

		if (partnerToBeUpdated != null) {

			String name = partner.getName();
			Date foundindgDate = partner.getFoundingDate();
			String foundBy = partner.getFoundBy();
			String url = partner.getUrl();
			String location = partner.getLocation();
			String description = partner.getDescription();

			partnerToBeUpdated.setName(name);

			partnerToBeUpdated.setFoundingDate(foundindgDate);
			partnerToBeUpdated.setFoundBy(foundBy);
			partnerToBeUpdated.setUrl(url);
			partnerToBeUpdated.setLocation(location);
			partnerToBeUpdated.setDescription(description);

			List<TechnologyTag> technologyTagNames = partner.getTechnologyTags();
			List<IndustryTag> industryTagNames = partner.getIndustryTags();

			List<String> technologyTagNameList = new ArrayList();
			List<String> industryTagNameList = new ArrayList();

			for (int i = 0; i < technologyTagNames.size(); i++) {
				TechnologyTag tag = technologyTagNames.get(i);
				String tagName = tag.getName();
				tag = technologyTagRepository.findByName(tagName);
				if (tag == null) {
					throw new AieExceptionClass("Technology Tag" + tagName + " does not exist");
				} else {
					technologyTagNameList.add(tagName);
				}

			}

			for (int i = 0; i < industryTagNames.size(); i++) {
				IndustryTag tag = industryTagNames.get(i);
				String tagName = tag.getName();
				tag = industryTagRepository.findByName(tagName);
				if (tag == null) {
					throw new AieExceptionClass("Industry Tag" + tagName + " does not exist");
				} else {
					industryTagNameList.add(tagName);

				}
			}

			technologyTagNames = technologyTagRepository.findByNameIn(technologyTagNameList);
			industryTagNames = industryTagRepository.findByNameIn(industryTagNameList);

			partnerToBeUpdated.setTechnologyTags(technologyTagNames);
			partnerToBeUpdated.setIndustryTags(industryTagNames);

			partnerToBeUpdated = partnerRepository.save(partnerToBeUpdated);

			return partnerToBeUpdated;
		} else {

			throw new AieExceptionClass("Partner  with partner id  '" + partnerId + "' does not exist to update!!");
		}

	}

}