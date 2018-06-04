package com.cg.aieecosystemapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.aieecosystemapp.model.PartnerUseCase;

public interface PartnerUseCaseRepository extends JpaRepository<PartnerUseCase, Integer>{

	

	List<PartnerUseCase> findByUseCaseIdIn(List<Integer> partnerUseCaseIds);

}
