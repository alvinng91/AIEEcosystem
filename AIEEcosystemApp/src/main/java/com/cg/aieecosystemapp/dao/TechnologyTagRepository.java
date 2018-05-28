package com.cg.aieecosystemapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.aieecosystemapp.model.TechnologyTag;

public interface TechnologyTagRepository extends JpaRepository<TechnologyTag, Integer> {

	List<TechnologyTag> findByNameIn(List<String> names);
	
	TechnologyTag findByName(String name);

}
