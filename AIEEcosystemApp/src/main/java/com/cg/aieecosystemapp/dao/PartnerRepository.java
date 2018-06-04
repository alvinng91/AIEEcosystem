package com.cg.aieecosystemapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.aieecosystemapp.model.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Integer> {

	List<Partner> findByTechnologyTagsNameIn(List<String> names);

	List<Partner> findByIndustryTagsNameIn(List<String> names);

	Partner findByPartnerId(int partnerId);

	List<Partner> findByTechnologyTagsNameInAndIndustryTagsNameIn(List<String> technologyTags, List<String> industryTags); 
	
	boolean existsByName(String name);
	
}
