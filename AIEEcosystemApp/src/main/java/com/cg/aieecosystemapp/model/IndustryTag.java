package com.cg.aieecosystemapp.model;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

public class IndustryTag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String industryTagId;

	private String name;

	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	private Partner partner;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
