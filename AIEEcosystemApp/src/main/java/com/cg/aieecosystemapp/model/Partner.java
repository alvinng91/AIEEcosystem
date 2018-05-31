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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

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
	private String location;
	private String description;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(name = "partner_technologyTag", 
	joinColumns = @JoinColumn(name = "partner_id", referencedColumnName = "partnerId"), 
	inverseJoinColumns = @JoinColumn(name = "technology_Tag_Id", referencedColumnName = "technologyTagId"))
	private List<TechnologyTag> technologyTags;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(name = "partner_industryTag", 
	joinColumns = @JoinColumn(name = "partner_id", referencedColumnName = "partnerId"), 
	inverseJoinColumns = @JoinColumn(name = "industry_Tag_Id", referencedColumnName = "industryTagId"))
	private List<IndustryTag> industryTags;
	
	
	@OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	private List<PartnerUseCase> partnerUseCases;

	public List<PartnerUseCase> getPartnerUseCases() {
		return partnerUseCases;
	}

	public void setPartnerUseCases(List<PartnerUseCase> partnerUseCases) {
		this.partnerUseCases = partnerUseCases;
	}

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
	
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TechnologyTag> getTechnologyTags() {
		return technologyTags;
	}

	public void setTechnologyTags(List<TechnologyTag> technologyTags) {
		this.technologyTags = technologyTags;
	}

	public List<IndustryTag> getIndustryTags() {
		return industryTags;
	}

	public void setIndustryTags(List<IndustryTag> industries) {
		this.industryTags = industries;
	}

}
