package com.cg.aieecosystemapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.AieEntryNotFoundException;
import com.cg.aieecosystemapp.aieexception.AieInvalidFieldsException;
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
			throw new AieInvalidFieldsException("tag already existed");
		}

		return repository.save(tag);
	}

	public List<TechnologyTag> searchTechnologyTagByName(List<String> names)
	{

		List<TechnologyTag> tags = repository.findByNameIn(names);

		if (tags.isEmpty())
		{
			throw new AieInvalidFieldsException("no tags exist");
		}

		return tags;
	}

	public List<TechnologyTag> retrieveAllTechnologyTags()
	{
		List<TechnologyTag> tags = repository.findAll();

		if (tags.isEmpty())
		{
			throw new AieInvalidFieldsException("no tags exist");
		}

		return tags;
	}

	public TechnologyTag updateTechnologyTag(TechnologyTag tag)
	{
		TechnologyTagUtility.validateTag(tag);

		Optional<TechnologyTag> existingTag = repository.findById(tag.getTechnologyTagId());

		if (!existingTag.isPresent())
		{
			throw new AieEntryNotFoundException("tag does not exist");
		}

		boolean nameIsSameIgnoreCase = existingTag.get().getName().equalsIgnoreCase(tag.getName());

		if (!nameIsSameIgnoreCase && repository.existsByName(tag.getName()))
		{
			throw new AieInvalidFieldsException("tag name already existed");
		}

		return repository.save(tag);
	}

	public int deleteTechnologyTags(List<TechnologyTag> tags)
	{
		if (tags == null)
		{
			throw new AieInvalidFieldsException("tags cannot be null");
		}

		List<Integer> tagIds = new ArrayList<Integer>(tags.size());
		for (TechnologyTag tag : tags)
		{
			int tagId = tag.getTechnologyTagId();
			if (repository.existsById(tagId))
			{
				tagIds.add(tagId);
			}
			else
			{
				throw new AieInvalidFieldsException("tag with id " + tagId + " does not exist");
			}
		}

		repository.deleteAll(tags);

		int count = 0;
		for (int id : tagIds)
		{
			if (!repository.existsById(id))
			{
				++count;
			}
		}

		return count;
	}

}
