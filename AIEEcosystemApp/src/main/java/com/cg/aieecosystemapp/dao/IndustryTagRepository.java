package com.cg.aieecosystemapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.aieecosystemapp.model.IndustryTag;

public interface IndustryTagRepository extends JpaRepository<IndustryTag, Integer>
{

	List<IndustryTag> findByNameIn(List<String> names);
	
	IndustryTag findByName (String name);

	Boolean existsByName(String name);
}
