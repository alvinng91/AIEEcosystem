package com.cg.aieecosystemapp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Partner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String partnerId;

	private String name;

	@Temporal(TemporalType.DATE)
	private Date foundingDate;

	private String foundBy;
	private String url;

//	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
//	private List<String> technologyTags;

//	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
//	private List<String> industries;

	private String technologyTags;
	private String  industries;
	
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

	public String getTechnologyTags() {
		return technologyTags;
	}

	public void setTechnologyTags(String technologyTags) {
		this.technologyTags = technologyTags;
	}

	public String getIndustries() {
		return industries;
	}

	public void setIndustries(String industries) {
		this.industries = industries;
	}

}
