package com.cg.aieecosystemapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.AieEntryNotFoundException;
import com.cg.aieecosystemapp.aieexception.AieInvalidFieldsException;
import com.cg.aieecosystemapp.dao.IndustryTagRepository;
import com.cg.aieecosystemapp.model.IndustryTag;
import com.cg.aieecosystemapp.utility.IndustryTagUtility;

@Service
public class IndustryTagService
{

	@Autowired
	private IndustryTagRepository repository;

	public IndustryTag createIndustryTag(IndustryTag tag)
	{
		IndustryTagUtility.validateTag(tag);

		if (repository.existsByName(tag.getName()))
		{
			throw new AieInvalidFieldsException("tag already existed");
		}

		return repository.save(tag);
	}

	public List<IndustryTag> searchIndustryTagByName(List<String> names)
	{

		List<IndustryTag> tags = repository.findByNameIn(names);

		if (tags.isEmpty())
		{
			throw new AieInvalidFieldsException("no tags exist");
		}

		return tags;
	}

	public List<IndustryTag> retrieveAllIndustryTags()
	{
		List<IndustryTag> tags = repository.findAll();

		if (tags.isEmpty())
		{
			throw new AieInvalidFieldsException("no tags exist");
		}

		return tags;
	}

	public IndustryTag updateIndustryTag(IndustryTag tag)
	{
		IndustryTagUtility.validateTag(tag);

		if (!repository.existsById(tag.getIndustryTagId()))
		{
			throw new AieEntryNotFoundException("tag does not exist");
		}

		if (repository.existsByName(tag.getName()))
		{
			throw new AieInvalidFieldsException("tag name already existed");
		}

		return repository.save(tag);
	}

	public int deleteIndustryTags(List<IndustryTag> tags)
	{
		if (tags == null)
		{
			throw new AieInvalidFieldsException("tags cannot be null");
		}

		List<Integer> tagIds = new ArrayList<Integer>(tags.size());
		for (IndustryTag tag : tags)
		{
			int tagId = tag.getIndustryTagId();
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
