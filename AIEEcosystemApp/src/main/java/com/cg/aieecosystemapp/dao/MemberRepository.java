package com.cg.aieecosystemapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.aieecosystemapp.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>
{
    public List<Member> findAll();

    public List<Member> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(
	    String firstName, String lastName, String email);

    public Member findByMemberId(int id);

    public Boolean existsById(int id);

    public Boolean existsByEmail(String email);

    public Boolean existsByEmailAndPassword(String email, String password);
}
