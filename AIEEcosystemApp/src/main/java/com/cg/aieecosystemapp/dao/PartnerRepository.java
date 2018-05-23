package com.cg.aieecosystemapp.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cg.aieecosystemapp.model.Partner;

public interface PartnerRepository extends MongoRepository<Partner, String> {

	@Query("{ 'technologyTags' : ?0 }")
	List<Partner> findPartnersByTechnologyTags(String [] tagNames); 
}
