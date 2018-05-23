package com.cg.aieecosystemapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.aieecosystemapp.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>
{
    public Member findByEmailAndPassword(String email, String password);
}
