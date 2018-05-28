package com.cg.aieecosystemapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.AieExceptionClass;
import com.cg.aieecosystemapp.dao.TechnologyTagRepository;
import com.cg.aieecosystemapp.model.TechnologyTag;
import com.cg.aieecosystemapp.utility.TechnologyTagUtility;

@Service
public class TechnologyTagService
{

	@Autowired
	private TechnologyTagRepository repository;

	public TechnologyTag createTechnologyTag(TechnologyTag tag)
	{
		TechnologyTagUtility.validateTag(tag);

		if (repository.existsByName(tag.getName()))
		{
			throw new AieExceptionClass("tag already existed");
		}

		return repository.save(tag);
	}

	public List<TechnologyTag> searchTechnologyTagByName(List<String> names)
	{

		List<TechnologyTag> tags = repository.findByNameIn(names);

		if (tags.isEmpty())
		{
			throw new AieExceptionClass("no tags exist");
		}

		return tags;
	}

	public List<TechnologyTag> retrieveAllTechnologyTags()
	{
		List<TechnologyTag> tags = repository.findAll();

		if (tags.isEmpty())
		{
			throw new AieExceptionClass("no tags exist");
		}

		return tags;
	}

	public TechnologyTag updateTechnologyTag(TechnologyTag tag)
	{
		TechnologyTagUtility.validateTag(tag);

		if (repository.existsById(tag.getTechnologyTagId()))
		{
			return repository.save(tag);
		}
		else
		{
			throw new AieExceptionClass("tag does not exist");
		}
	}

	public void deleteTechnologyTags(List<TechnologyTag> tags)
	{
		if (tags == null)
		{
			throw new AieExceptionClass("tags cannot be null");
		}

		repository.deleteAll(tags);
	}

	public void deleteAllTechnologyTags()
	{
		repository.deleteAll();
	}

}
