package com.cg.aieecosystemapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
public class Partner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int partnerId;

	@Column(unique = true)
	private String name;

	@Temporal(TemporalType.DATE)
	private Date foundingDate;

	private String foundBy;
	private String url;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = { CascadeType.ALL })
	private List<TechnologyTag> technologyTags;
	
	
	@ManyToMany(cascade = { CascadeType.ALL })
	private List<IndustryTag> industryTags;

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFoundingDate() {
		return foundingDate;
	}

	public void setFoundingDate(Date foundingDate) {
		this.foundingDate = foundingDate;
	}

	public String getFoundBy() {
		return foundBy;
	}

	public void setFoundBy(String foundBy) {
		this.foundBy = foundBy;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<TechnologyTag> getTechnologyTags() {
		return technologyTags;
	}

	public void setTechnologyTags(List<TechnologyTag> technologyTags) {
		this.technologyTags = technologyTags;
	}

	public List<IndustryTag> getIndustries() {
		return industryTags;
	}

	public void setIndustries(List<IndustryTag> industries) {
		this.industryTags = industries;
	}

}
