package com.cg.aieecosystemapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.dao.IndustryTagRepository;
import com.cg.aieecosystemapp.model.IndustryTag;

@Service
public class IndustryTagService {

	@Autowired
	private IndustryTagRepository repository;

	public IndustryTag createIndustryTag(String name, String description) {

		IndustryTag tag = new IndustryTag();
		tag.setName(name);
		tag.setDescription(description);
		tag = repository.save(tag);

		return tag;
	}

	public List<IndustryTag> searchIndustryTagByName(String name) {
		
		

		return repository.findIndustryTagByName(name);
	}

}
