package com.cg.aieecosystemapp.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.aieecosystemapp.aieexception.AieExceptionClass;
import com.cg.aieecosystemapp.dao.MemberRepository;
import com.cg.aieecosystemapp.model.Member;
import com.cg.aieecosystemapp.utility.AieMemberUtility;

@Service
public class MemberService
{
    @Autowired
    private MemberRepository repository;

    public Member createMember(Member newMember)
    {
	AieMemberUtility.validateMemberObject

	(newMember);

	Boolean memberExists = repository.existsByEmail(newMember.getEmail());

	if (!memberExists)
	{
	    newMember = repository.save(newMember);
	    return newMember;
	}
	else
	{
	    throw new AieExceptionClass("Member with email '" + newMember.getEmail() + "' already exist!!");
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

    public Member updateMemberTier(Member updateMember)
    {
	List<Integer> updateMemberId = new ArrayList<>();
	updateMemberId.add(updateMember.getMemberId());

	List<Member> members = repository.findByMemberIdIn(updateMemberId);
	Member existingMember = null;

	if (!members.isEmpty())
	{
	    if (members.size() > 1)
	    {
		throw new AieExceptionClass("Error : Member Update returns multiple records!");
	    }
	    else
	    {
		existingMember = members.get(0);
	    }
	}

	if (existingMember != null)
	{
	    AieMemberUtility.validateMemberObject(updateMember);
	    updateMember = repository.save(updateMember);
	    return updateMember;
	}
	else
	{
	    throw new AieExceptionClass("Error: Member to update does not exist !!");
	}
    }

    public void deleteExistingMember(List<Integer> listOfId)
    {
	try
	{
	    List<Member> existingMember = repository.findByMemberIdIn(listOfId);

	    if (existingMember.isEmpty())
	    {
		throw new AieExceptionClass("Error: Member(s) to delete does not exists!!");
	    }
	    else
	    {
		repository.deleteAll(existingMember);
	    }
	}
	catch (NumberFormatException e)
	{
	    throw new AieExceptionClass("Id is invalid to update the member!!");
	}
    }
}
