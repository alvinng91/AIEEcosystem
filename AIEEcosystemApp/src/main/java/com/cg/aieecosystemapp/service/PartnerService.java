package com.cg.aieecosystemapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.AieEntryNotFoundException;
import com.cg.aieecosystemapp.aieexception.AieExceptionClass;
import com.cg.aieecosystemapp.aieexception.AieInvalidFieldsException;
import com.cg.aieecosystemapp.dao.IndustryTagRepository;
import com.cg.aieecosystemapp.dao.PartnerRepository;
import com.cg.aieecosystemapp.dao.TechnologyTagRepository;
import com.cg.aieecosystemapp.model.IndustryTag;
import com.cg.aieecosystemapp.model.Partner;
import com.cg.aieecosystemapp.model.TechnologyTag;
import com.cg.aieecosystemapp.utility.AiePartnerUtility;

@Service
public class PartnerService {

	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private TechnologyTagRepository technologyTagRepository;

	@Autowired
	private IndustryTagRepository industryTagRepository;

	public Partner createPartner(Partner partner) {

		if (partner == null) {

			throw new AieInvalidFieldsException("Partner is null");
		}

		List<String> technologyTagString = partner.getTechnologyTags().stream().map(TechnologyTag::getName)
				.collect(Collectors.toList());
		List<TechnologyTag> technologyTagNames = technologyTagRepository.findByNameIn(technologyTagString);

		if (technologyTagNames.size() != partner.getTechnologyTags().size()) {

			String errMsg = new String("Invalid Tags are: ");
			technologyTagString
					.removeAll(technologyTagNames.stream().map(TechnologyTag::getName).collect(Collectors.toList()));
			for (String s : technologyTagString)
				errMsg += s + ",";
			throw new AieInvalidFieldsException(errMsg.substring(0, errMsg.length() - 1));
		}

		List<String> industryTagString = partner.getIndustryTags().stream().map(IndustryTag::getName)
				.collect(Collectors.toList());
		List<IndustryTag> industryTagNames = industryTagRepository.findByNameIn(industryTagString);

		if (industryTagNames.size() != partner.getIndustryTags().size()) {

			String errMsg = new String("Invalid Tags are: ");
			industryTagString
					.removeAll(industryTagNames.stream().map(IndustryTag::getName).collect(Collectors.toList()));
			for (String s : industryTagString)
				errMsg += s + ",";
			throw new AieInvalidFieldsException(errMsg.substring(0, errMsg.length() - 1));
		}
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

		boolean searchByTechnology = technologyTags != null && !technologyTags.isEmpty();
		boolean searchByIndustry = industryTags != null && !industryTags.isEmpty();

		if (searchByTechnology && searchByIndustry) {

			Map<Integer, Integer> intersectionMap = new HashMap<>();

			List<Partner> technogyTagPartners = partnerRepository.findByTechnologyTagsNameIn(technologyTags);
			List<Partner> industryTagPartners = partnerRepository.findByIndustryTagsNameIn(industryTags);
			List<Partner> partnersIntersection = new ArrayList<>();

			for (Partner partner : technogyTagPartners) {
				intersectionMap.put(partner.getPartnerId(), 1);
			}
			for (Partner partner : industryTagPartners) {
				if (intersectionMap.get(partner.getPartnerId()) != null) {
					partnersIntersection.add(partner);
				}
			}
			return partnersIntersection;
		}

		if (searchByIndustry) {
			return partnerRepository.findByIndustryTagsNameIn(industryTags);
		}
		if (searchByTechnology) {
			return partnerRepository.findByTechnologyTagsNameIn(technologyTags);
		}
		throw new AieInvalidFieldsException("Invalid Search fields");
	}

	public Partner updatePartner(Partner partner) {
		AiePartnerUtility.validate(partner);

		Optional<Partner> existingPartner = partnerRepository.findById(partner.getPartnerId());

		if (!existingPartner.isPresent()) {
			throw new AieEntryNotFoundException("partner does not exist");
		}			
		
		boolean nameIsSameIgnoreCase = existingPartner.get().getName().equalsIgnoreCase(partner.getName());

		if (!nameIsSameIgnoreCase && partnerRepository.existsByName(partner.getName()))
		{
			throw new AieInvalidFieldsException("Partner with this name already exists");
		}
					
		List<String> technologyTagString = partner.getTechnologyTags().stream().map(TechnologyTag::getName)
				.collect(Collectors.toList());
		List<TechnologyTag> technologyTagNames = technologyTagRepository.findByNameIn(technologyTagString);

		if (technologyTagNames.size() != partner.getTechnologyTags().size()) {

			String errMsg = new String("Invalid Tags are: ");
			technologyTagString
					.removeAll(technologyTagNames.stream().map(TechnologyTag::getName).collect(Collectors.toList()));
			for (String s : technologyTagString)
				errMsg += s + ",";
			throw new AieInvalidFieldsException(errMsg.substring(0, errMsg.length() - 1));
		}

		List<String> industryTagString = partner.getIndustryTags().stream().map(IndustryTag::getName)
				.collect(Collectors.toList());
		List<IndustryTag> industryTagNames = industryTagRepository.findByNameIn(industryTagString);

		if (industryTagNames.size() != partner.getIndustryTags().size()) {

			String errMsg = new String("Invalid Tags are: ");
			industryTagString
					.removeAll(industryTagNames.stream().map(IndustryTag::getName).collect(Collectors.toList()));
			for (String s : industryTagString)
				errMsg += s + ",";
			throw new AieInvalidFieldsException(errMsg.substring(0, errMsg.length() - 1));
		}

		partner.setTechnologyTags(technologyTagNames);
		partner.setIndustryTags(industryTagNames);
		return partnerRepository.save(partner);
	}
}