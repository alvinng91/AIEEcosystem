package com.cg.aieecosystemapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class IndustryTag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int industryTagId;

	@Column(unique = true)
	private String name;

	private String description;


	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(mappedBy = "industryTags")
	private List<Partner> partner;

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
