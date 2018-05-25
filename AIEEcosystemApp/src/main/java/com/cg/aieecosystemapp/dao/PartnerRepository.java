package com.cg.aieecosystemapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.aieecosystemapp.model.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Integer>
{
    List<Partner> findPartnersByTechnologyTags(String[] tagNames);
}
