package com.cg.aieecosystemapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.AieExceptionClass;
import com.cg.aieecosystemapp.aieexception.AieInvalidFieldsException;
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

		if (partner == null) {

			throw new AieInvalidFieldsException("Partner is null");
		}

		int partnerId = partner.getPartnerId();

		Partner partnerToBeUpdated = partnerRepository.findByPartnerId(partnerId);

		if (partnerToBeUpdated != null) {

			String name = partner.getName();
			if (name != null && !partnerToBeUpdated.getName().equals(name) && partnerRepository.existsByName(name)) {
				// if name exists and the name of the partner id to be updated is different from
				// the name that already exists
				new AieInvalidFieldsException("Partner with this name already exists");
			}
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

			List<String> technologyTagString = partner.getTechnologyTags().stream().map(TechnologyTag::getName)
					.collect(Collectors.toList());
			List<TechnologyTag> technologyTagNames = technologyTagRepository.findByNameIn(technologyTagString);

			if (technologyTagNames.size() != partner.getTechnologyTags().size()) {

				String errMsg = new String("Invalid Tags are: ");
				technologyTagString.removeAll(
						technologyTagNames.stream().map(TechnologyTag::getName).collect(Collectors.toList()));
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

			partnerToBeUpdated.setTechnologyTags(technologyTagNames);
			partnerToBeUpdated.setIndustryTags(industryTagNames);

			partnerToBeUpdated = partnerRepository.save(partnerToBeUpdated);

			return partnerToBeUpdated;
		} else {

			throw new AieExceptionClass("Partner  with partner id  '" + partnerId + "' does not exist to update!!");
		}

	}

}