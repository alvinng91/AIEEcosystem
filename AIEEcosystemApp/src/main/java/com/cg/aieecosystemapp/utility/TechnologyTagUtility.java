package com.cg.aieecosystemapp.utility;

import com.cg.aieecosystemapp.aieexception.AieExceptionClass;
import com.cg.aieecosystemapp.model.TechnologyTag;

public class TechnologyTagUtility
{

	static public void validateTag(TechnologyTag tag) throws AieExceptionClass
	{
		validateTagNull(tag);
		validateTagName(tag);
		validateTagDisplayName(tag);
		validateTagDescription(tag);
	}

	static public void validateTagNull(TechnologyTag tag)
	{
		if (tag == null)
		{
			throw new AieExceptionClass("tag cannot be null!");
		}
	}

	static public void validateTagName(TechnologyTag tag)
	{
		if (tag.getName() == null || tag.getName().isEmpty())
		{
			throw new AieExceptionClass("tag name cannot be null/empty");
		}
	}

	static public void validateTagDisplayName(TechnologyTag tag)
	{
		if (tag.getDisplayName() == null || tag.getDisplayName().isEmpty())
		{
			throw new AieExceptionClass("tag display name cannot be null/empty");
		}
	}

	static public void validateTagDescription(TechnologyTag tag)
	{
		if (tag.getDescription() == null || tag.getDescription().isEmpty())
		{
			throw new AieExceptionClass("tag description cannot be null/empty");
		}
	}

}
