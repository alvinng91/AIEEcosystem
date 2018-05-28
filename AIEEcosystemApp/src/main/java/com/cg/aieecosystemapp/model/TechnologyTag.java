package com.cg.aieecosystemapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class TechnologyTag
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int technologyTagId;

	@Column(unique = true)
	private String name;

	private String displayName;

	private String description;

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@ManyToMany(mappedBy = "technologyTags")
	private List<Partner> partner;

	public int getTechnologyTagId()
	{
		return technologyTagId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

}
