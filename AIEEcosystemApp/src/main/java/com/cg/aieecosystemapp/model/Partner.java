package com.cg.aieecosystemapp.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Id;

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
	private List<String> technologyTags;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<String> industries;

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

	public List<String> getTechnologyTags() {
		return technologyTags;
	}

	public void setTechnologyTags(List<String> technologyTags) {
		this.technologyTags = technologyTags;
	}

	public List<String> getIndustries() {
		return industries;
	}

	public void setIndustries(List<String> industries) {
		this.industries = industries;
	}

}
