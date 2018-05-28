package com.cg.aieecosystemapp.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.AieExceptionClass;
import com.cg.aieecosystemapp.dao.MemberRepository;
import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.utility.AieUtility;

@Service
public class MemberService
{
    @Autowired
    private MemberRepository repository;

    public Member createMember(String firstName, String lastName, String position, String email, String tier,
	    String password)
    {
	if (!AieUtility.isMemberEmailCorrect(email))
	{
	    throw new AieExceptionClass("Invalid email : '" + email + "'!! Try again with valid email");
	}

	Boolean memberExists = repository.existsByEmail(email);

	if (!memberExists)
	{
	    Member newMember = new Member(firstName, lastName, position, email, tier, password, false);

	    if (AieUtility.validateMemberBeforeCreation(newMember))
	    {
		newMember = repository.save(newMember);
		return newMember;
	    }
	    else
	    {
		throw new AieExceptionClass("Member has invalid data!! Verify Data and try again!!");
	    }
	}
	else
	{
	    throw new AieExceptionClass("Member with email '" + email + "' already exist!!");
	}

    }

    public List<Member> getFilteredMembers(String searchQuery, String position)
    {
	Set<Member> filteredMembers = new HashSet<>();

	boolean isSearchQueryEmptyOrNull = searchQuery == null | searchQuery.isEmpty();
	boolean isSearchPositionEmptyOrNull = position == null | position.isEmpty();

	if (isSearchQueryEmptyOrNull && isSearchPositionEmptyOrNull)
	{
	    return repository.findAll();
	}

	List<Member> searchMembersByPostionList = new ArrayList<>();
	List<Member> searchMembersBySearchQueryList = new ArrayList<>();

	if (!isSearchQueryEmptyOrNull)
	{
	    List<Member> searchResultByFirstNameList = repository.findByFirstNameIgnoreCaseContaining(searchQuery);
	    List<Member> searchResultByLastNameList = repository.findByLastNameIgnoreCaseContaining(searchQuery);
	    List<Member> searchResultByEmailList = repository.findByEmailIgnoreCaseContaining(searchQuery);

	    searchMembersBySearchQueryList.addAll(searchResultByFirstNameList);
	    searchMembersBySearchQueryList.addAll(searchResultByLastNameList);
	    searchMembersBySearchQueryList.addAll(searchResultByEmailList);

	    filteredMembers.addAll(searchMembersBySearchQueryList);
	}

	if (isSearchQueryEmptyOrNull && !isSearchPositionEmptyOrNull)
	{
	    searchMembersByPostionList = repository.findByPositionIgnoreCaseContaining(position);
	    filteredMembers.addAll(searchMembersByPostionList);
	}
	else if (!isSearchQueryEmptyOrNull && !isSearchPositionEmptyOrNull)
	{
	    List<Member> filteredMembersFromLambda = filteredMembers.stream()
		    .filter(member -> member.getPosition().toLowerCase().contains(position.toLowerCase()))
		    .collect(Collectors.toList());

	    filteredMembers.clear();
	    filteredMembers.addAll(filteredMembersFromLambda);
	}

	return new ArrayList<>(filteredMembers);
    }

    public Member updateMemberTier(String id, String tier)
    {
	try
	{
	    Member existingMember = repository.findByMemberId(Integer.parseInt(id));

	    if (existingMember != null)
	    {
		if (!AieUtility.isMemberTierCorrect(tier))
		{
		    throw new AieExceptionClass("Invalid tier '" + tier + "')!! Try Again!!");
		}

		existingMember.setTier(tier);
		existingMember = repository.save(existingMember);
		return existingMember;
	    }
	    else
	    {
		throw new AieExceptionClass("Member to update does not exist !!");
	    }
	}
	catch (NumberFormatException e)
	{
	    throw new AieExceptionClass("Id is invalid to update the member!!");
	}
    }

    public void deleteExistingMember(String id)
    {
	try
	{
	    Member existingMember = repository.findByMemberId(Integer.parseInt(id));

	    if (existingMember == null)
	    {
		throw new AieExceptionClass("Member to delete does not exists!!");
	    }
	    else
	    {
		repository.delete(existingMember);
	    }
	}
	catch (NumberFormatException e)
	{
	    throw new AieExceptionClass("Id is invalid to update the member!!");
	}
    }
}
