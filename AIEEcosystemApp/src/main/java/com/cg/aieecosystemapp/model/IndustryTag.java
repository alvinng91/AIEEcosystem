package com.cg.aieecosystemapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class IndustryTag
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int industryTagId;

	@Column(unique = true)
	private String name;

	private String displayName;

	@Column(columnDefinition = "TEXT")
	private String description;

	@ManyToMany(mappedBy = "industryTags", fetch = FetchType.EAGER)
	private List<Partner> partner;

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public int getIndustryTagId()
	{
		return industryTagId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

}
