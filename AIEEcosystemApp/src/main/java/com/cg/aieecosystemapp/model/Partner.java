package com.cg.aieecosystemapp.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Partner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String partnerId;

	private String name;

	@Temporal(TemporalType.DATE)
	private LocalDate foundingDate;

	private String foundBy;
	private String url;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<TechnologyTag> technologyTags;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<IndustryTag> industries;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getFoundingDate() {
		return foundingDate;
	}

	public void setFoundingDate(LocalDate foundingDate) {
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
		return industries;
	}

	public void setIndustries(List<IndustryTag> industries) {
		this.industries = industries;
	}

}
