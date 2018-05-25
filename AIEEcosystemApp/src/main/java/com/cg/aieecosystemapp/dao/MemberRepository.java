package com.cg.aieecosystemapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.aieecosystemapp.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>
{
	public Member findByEmailAndPassword(String email, String password);

	public Member findByEmail(String email);

	public Boolean existsByEmail(String email);

	public Boolean existsByEmailAndPassword(String email, String password);
}
