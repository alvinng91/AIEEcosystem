package com.cg.aieecosystemapp.service;

import java.text.ParseException;
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
import com.cg.aieecosystemapp.partnerutility.AieUtility;

@Service
public class PartnerService {

	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private TechnologyTagRepository technologyTagRepository;

	@Autowired
	private IndustryTagRepository industryTagRepository;

	public Partner createPartner(String name, Date foundingDate, String foundBy, String url, String location,
			String description, List<TechnologyTag> technologyTags, List<IndustryTag> industryTags)
			throws ParseException {

		List<String> technologyTagNames = new ArrayList<String>();
		List<String> industryTagNames = new ArrayList<String>();

		Partner partner = new Partner();
		partner.setName(name);

		partner.setFoundingDate(foundingDate);
		partner.setFoundBy(foundBy);
		partner.setUrl(url);
		partner.setLocation(location);
		partner.setDescription(description);

		for (int i = 0; i < technologyTags.size(); i++) {
			TechnologyTag tag = technologyTags.get(i);
			String tagName = tag.getName();
			tag = technologyTagRepository.findByName(tagName);
			if (tag == null) {
				throw new AieExceptionClass("Technology Tag" + tagName + " does not exist");
			} else {
				technologyTagNames.add(tagName);
			}

		}

		for (int i = 0; i < industryTags.size(); i++) {
			IndustryTag tag = industryTags.get(i);
			String tagName = tag.getName();
			tag = industryTagRepository.findByName(tagName);
			if (tag == null) {
				throw new AieExceptionClass("Industry Tag" + tagName + " does not exist");
			} else {
				industryTagNames.add(tagName);

			}
		}

		List<TechnologyTag> technologyTagObjects = technologyTagRepository.findByNameIn(technologyTagNames);
		List<IndustryTag> industryTagsObjects = industryTagRepository.findByNameIn(industryTagNames);

		partner.setTechnologyTags(technologyTagObjects);
		partner.setIndustryTags(industryTagsObjects);

		partner = partnerRepository.save(partner);

		return partner;
	}

	public List<Partner> searchPartnersByTechnologyTag(List<String> tagNames) {

		return partnerRepository.findByTechnologyTagsNameIn(tagNames);
	}

	public List<Partner> searchPartnersByIndustryTag(List<String> industryTags) {

		return partnerRepository.findByIndustryTagsNameIn(industryTags);
	}

	public Partner updatePartner(int partnerId, String name, String foundingDate, String foundBy, String url,
			String location, String description, List<String> technologyTagNames, List<String> industryTagNames)
			throws ParseException {

		Partner partnerToBeUpdated = partnerRepository.findByPartnerId(partnerId);

		if (partnerToBeUpdated != null) {

			partnerToBeUpdated.setName(name);

			Date parsedFoundingDate = AieUtility.stringToDateFormatter(foundingDate);

			partnerToBeUpdated.setFoundingDate(parsedFoundingDate);
			partnerToBeUpdated.setFoundBy(foundBy);
			partnerToBeUpdated.setUrl(url);
			partnerToBeUpdated.setLocation(location);
			partnerToBeUpdated.setDescription(description);

			List<TechnologyTag> technologyTagObjects = technologyTagRepository.findByNameIn(technologyTagNames);
			List<IndustryTag> industryTagObjects = industryTagRepository.findByNameIn(industryTagNames);

			partnerToBeUpdated.setTechnologyTags(technologyTagObjects);
			partnerToBeUpdated.setIndustryTags(industryTagObjects);

			partnerToBeUpdated = partnerRepository.save(partnerToBeUpdated);

			return partnerToBeUpdated;
		} else {

			throw new AieExceptionClass("Partner  with partner id  '" + partnerId + "' does not exist to update!!");
		}
	}

	public void deletePartner(int partnerId) {

		Partner partnerToBeDeleted = partnerRepository.findByPartnerId(partnerId);
		if (partnerToBeDeleted != null) {
			partnerRepository.delete(partnerToBeDeleted);

		} else {

			throw new AieExceptionClass("Partner  with partner id  '" + partnerId + "' does not exist to delete!!");
		}

	}

	public List<Partner> searchPartnersByTechnologyTagsAndIndustryTags(List<String> technologyTags,
			List<String> industryTags) {
		return partnerRepository.findByTechnologyTagsNameInAndIndustryTagsNameIn(technologyTags, industryTags);

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

	public Partner createPartner(Partner partner) {
		// TODO Auto-generated method stub

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

}
