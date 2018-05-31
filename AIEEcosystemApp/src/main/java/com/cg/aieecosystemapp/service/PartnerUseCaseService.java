package com.cg.aieecosystemapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.AieEntryNotFoundException;
import com.cg.aieecosystemapp.aieexception.AieInvalidFieldsException;
import com.cg.aieecosystemapp.aieexception.AieJpaCrudException;
import com.cg.aieecosystemapp.dao.PartnerUseCaseRepository;
import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.model.PartnerUseCase;
import com.cg.aieecosystemapp.utility.AieMemberUtility;
import com.mysql.jdbc.StringUtils;

@Service
public class PartnerUseCaseService {

	@Autowired
	PartnerUseCaseRepository repository;

	public PartnerUseCase createUseCase(PartnerUseCase useCase) {

		if (useCase == null) {
			throw new AieInvalidFieldsException("Error: New PartnerUseCase to create cannot be null!!");
		}

		if (StringUtils.isNullOrEmpty(useCase.getDescription()))
			throw new AieInvalidFieldsException("description is null or empty");

		if (useCase.getDateCreated() == null)
			throw new AieInvalidFieldsException("date created is null or empty");

		if(repository.findById(useCase.getUseCaseId()).isPresent())
			throw new AieInvalidFieldsException("duplicate use case already exists");
		
		return Optional.of(repository.save(useCase)).orElseThrow(() -> new AieJpaCrudException("create failed"));
	}

	public PartnerUseCase updatePartnerUseCase(int useCaseId, PartnerUseCase useCase) {
		repository.findById(useCaseId)
				.orElseThrow(() -> new AieEntryNotFoundException("partner use case not found"));
		return Optional.of(repository.save(useCase)).orElseThrow(() -> new AieJpaCrudException("create failed"));
	}

	public boolean deletePartnerUseCase(int useCaseId) {
		repository.deleteById(useCaseId);
		return true;
	}

}
