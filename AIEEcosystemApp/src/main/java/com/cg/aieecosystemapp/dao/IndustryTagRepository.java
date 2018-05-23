package com.cg.aieecosystemapp.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cg.aieecosystemapp.model.IndustryTag;
import com.cg.aieecosystemapp.model.TechnologyTag;

public interface IndustryTagRepository extends MongoRepository<IndustryTag, String> {

	@Query("{ 'name' : ?0 }")
	List<IndustryTag> findIndustryTagByName(String name);

}
