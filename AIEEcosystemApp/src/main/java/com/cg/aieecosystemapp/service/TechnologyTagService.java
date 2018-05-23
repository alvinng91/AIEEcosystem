package com.cg.aieecosystemapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.dao.TechnologyTagRepository;
import com.cg.aieecosystemapp.model.TechnologyTag;

@Service
public class TechnologyTagService {

	@Autowired
	private TechnologyTagRepository repository;

	public TechnologyTag createTechnologyTag(String name, String description) {

		TechnologyTag tag = new TechnologyTag();
		tag.setName(name);
		tag.setDescription(description);
		tag = repository.save(tag);

		return tag;
	}

	public List<TechnologyTag> searchTechnologyTagByName(String name) {
		
		return repository.findTechnologyTagByName(name);
	}

}
