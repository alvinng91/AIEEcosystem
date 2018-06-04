package com.cg.aieecosystemapp.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cg.aieecosystemapp.aieexception.AieExceptionClass;
import com.cg.aieecosystemapp.aieexception.AieInvalidFieldsException;
import com.cg.aieecosystemapp.dao.IndustryTagRepository;
import com.cg.aieecosystemapp.dao.PartnerUseCaseRepository;
import com.cg.aieecosystemapp.dao.TechnologyTagRepository;
import com.cg.aieecosystemapp.model.IndustryTag;
import com.cg.aieecosystemapp.model.Partner;
import com.cg.aieecosystemapp.model.PartnerUseCase;
import com.cg.aieecosystemapp.model.TechnologyTag;

public class AiePartnerUtility {

	static public void validate(Partner partner) throws AieExceptionClass {
		validateNull(partner);
		validateName(partner);
		validateFoundBy(partner);
		validateUrl(partner);
		validateLocation(partner);
		validateDescription(partner);
		validateTechnologyTags(partner);
		validateIndustryTags(partner);
	}

	private static void validateIndustryTags(Partner partner) {
		if (partner.getIndustryTags() == null || partner.getIndustryTags().isEmpty()) {
			throw new AieInvalidFieldsException("industry tags cannot be null/empty");
		}

	}

	private static void validateTechnologyTags(Partner partner) {
		if (partner.getTechnologyTags() == null || partner.getTechnologyTags().isEmpty()) {
			throw new AieInvalidFieldsException("techmology tags cannot be null/empty");
		}

	}

	static public void validateNull(Partner partner) {
		if (partner == null) {
			throw new AieInvalidFieldsException("partner cannot be null!");
		}
	}

	static public void validateName(Partner partner) {
		if (partner.getName() == null || partner.getName().isEmpty()) {
			throw new AieInvalidFieldsException("name cannot be null/empty");
		}
	}

	static public void validateFoundBy(Partner partner) {
		if (partner.getFoundBy() == null || partner.getFoundBy().isEmpty()) {
			throw new AieInvalidFieldsException("found-by cannot be null/empty");
		}
	}

	static public void validateUrl(Partner partner) {
		if (partner.getUrl() == null || partner.getUrl().isEmpty()) {
			throw new AieInvalidFieldsException("url cannot be null/empty");
		}
	}

	static public void validateLocation(Partner partner) {
		if (partner.getLocation() == null || partner.getLocation().isEmpty()) {
			throw new AieInvalidFieldsException("location cannot be null/empty");
		}
	}

	static public void validateDescription(Partner partner) {
		if (partner.getDescription() == null || partner.getDescription().isEmpty()) {
			throw new AieInvalidFieldsException("description cannot be null/empty");
		}
	}

	public static List<TechnologyTag> fetchTechnologyTags(Partner partner,
			TechnologyTagRepository technologyTagRepository) {

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
		return technologyTagNames;

	}

	public static List<IndustryTag> fetchIndustryTags(Partner partner, IndustryTagRepository industryTagRepository) {

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
		return industryTagNames;
	}

	public static List<PartnerUseCase> fetchPartnerUseCases(Partner partner,
			PartnerUseCaseRepository partnerUseCaseRepository) {

		List<Integer> partnerUseCaseIds = partner.getPartnerUseCases().stream().map(PartnerUseCase::getUseCaseId)
				.collect(Collectors.toList());
		List<PartnerUseCase> partnerUseCases = partnerUseCaseRepository.findByUseCaseIdIn(partnerUseCaseIds);
		
		
		
		
		List<Integer> invalidUseCaseIds = new ArrayList<>();


		for (PartnerUseCase partnerUseCase : partnerUseCases) {
			 if (partnerUseCase.getPartner() != null || partnerUseCase.getPartner().getPartnerId() != partner.getPartnerId()) {
				invalidUseCaseIds.add(partnerUseCase.getUseCaseId());
			}
		}

		if (invalidUseCaseIds.size() != 0) {

			String errMsg = new String("Invalid partner use case ids are: ");

			for (int id : invalidUseCaseIds)
				errMsg += id + ",";
			throw new AieInvalidFieldsException(errMsg.substring(0, errMsg.length() - 1));
		}
		return partnerUseCases;
	}
}
