package com.cg.aieecosystemapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.aieecosystemapp.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>
{
    public List<Member> findAll();

    public List<Member> findByMemberIdIn(List<Integer> memberId);

    public List<Member> findByFirstNameIgnoreCaseContaining(String firstName);

    public List<Member> findByLastNameIgnoreCaseContaining(String lastName);

    public List<Member> findByEmailIgnoreCaseContaining(String email);

    public List<Member> findByPositionIgnoreCaseContaining(String position);

    public Member findByEmailIgnoreCase(String email);

    public Boolean existsById(int id);

    public Boolean existsByEmail(String email);

    public Boolean existsByEmailAndPassword(String email, String password);
}
