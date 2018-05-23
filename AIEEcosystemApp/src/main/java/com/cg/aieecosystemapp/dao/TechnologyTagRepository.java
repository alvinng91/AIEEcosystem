package com.cg.aieecosystemapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cg.aieecosystemapp.model.TechnologyTag;

public interface TechnologyTagRepository extends JpaRepository<TechnologyTag, Long> {

	@Query("{ 'name' : ?0 }")
	List<TechnologyTag> findTechnologyTagByName(String name);

}
