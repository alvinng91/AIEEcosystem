package com.cg.aieecosystemapp.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aieecosystemapp.aieexception.*;
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
	if (newMember == null)
	{
	    throw new AieInvalidFieldsException("Error: New Member to create cannot be null!!");
	}

	AieMemberUtility.validateMemberObject(newMember);

	Boolean memberExists = repository.existsByEmail(newMember.getEmail());

	if (!memberExists)
	{
	    newMember = repository.save(newMember);
	    return newMember;
	}
	else
	{
	    throw new AieInvalidFieldsException("Member with email '" + newMember.getEmail() + "' already exist!!");
	}

    }

    public List<Member> getFilteredMembers(String searchQuery, String position)
    {
	Set<Member> filteredMembers = new HashSet<>();

	boolean isSearchQueryEmptyOrNull = searchQuery == null || searchQuery.isEmpty();
	boolean isSearchPositionEmptyOrNull = position == null || position.isEmpty();

	if (isSearchQueryEmptyOrNull && isSearchPositionEmptyOrNull)
	{
	    return repository.findAll();
	}

	if (!isSearchQueryEmptyOrNull)
	{
	    List<Member> searchMembersBySearchQueryList = new ArrayList<>();
	    List<Member> searchResultByFirstNameList = repository.findByFirstNameIgnoreCaseContaining(searchQuery);
	    List<Member> searchResultByLastNameList = repository.findByLastNameIgnoreCaseContaining(searchQuery);
	    List<Member> searchResultByEmailList = repository.findByEmailIgnoreCaseContaining(searchQuery);

	    searchMembersBySearchQueryList.addAll(searchResultByFirstNameList);
	    searchMembersBySearchQueryList.addAll(searchResultByLastNameList);
	    searchMembersBySearchQueryList.addAll(searchResultByEmailList);

	    filteredMembers.addAll(searchMembersBySearchQueryList);

	    if (!isSearchPositionEmptyOrNull)
	    {
		List<Member> filteredMembersFromLambda = filteredMembers.stream()
			.filter(member -> member.getPosition().toLowerCase().contains(position.toLowerCase()))
			.collect(Collectors.toList());

		filteredMembers.clear();
		filteredMembers.addAll(filteredMembersFromLambda);
	    }
	}
	else// Search by only Position
	{
	    List<Member> searchMembersByPostionList = repository.findByPositionIgnoreCaseContaining(position);
	    filteredMembers.addAll(searchMembersByPostionList);
	}

	return new ArrayList<>(filteredMembers);
    }

    public Member updateMember(Member member)
    {
	if(member == null)
	{
	    throw new AieInvalidFieldsException("Error: Input Member cannot be null!!");
	}
	
	List<Integer> updateMemberId = new ArrayList<>();
	updateMemberId.add(member.getMemberId());

	List<Member> members = repository.findByMemberIdIn(updateMemberId);
	Member existingMember = null;

	if (!members.isEmpty())
	{
	    if (members.size() > 1)
	    {
		throw new AieInvalidFieldsException(
			"Error : Member Update returns multiple records for '" + member.getEmail() + "'");
	    }
	    else
	    {
		existingMember = members.get(0);
	    }
	}

	if (existingMember != null)
	{
	    AieMemberUtility.validateMemberObject(member);

	    Member existingMemberByEmail = repository.findByEmailIgnoreCase(member.getEmail());

	    if (existingMemberByEmail.getMemberId() == member.getMemberId())
	    {
		member = repository.save(member);
		return repository.findByMemberId(member.getMemberId());
	    }
	    else
	    {
		throw new AieInvalidFieldsException(
			"Error: Member to update has email ' " + member.getEmail() + " ' which already exists!!");
	    }
	}
	else
	{
	    throw new AieInvalidFieldsException(
		    "Error: Member ' " + member.getEmail() + " ' to update does not exist !!");
	}
    }

    public int deleteExistingMember(List<Member> deleteMemberList)
    {
	if (deleteMemberList == null)
	{
	    throw new AieInvalidFieldsException("Error: List of Members to delete cannot be null!");
	}

	for (Member member : deleteMemberList)
	{
	    if (!repository.existsById(member.getMemberId()))
	    {
		throw new AieEntryNotFoundException("Error : Member '" + member.getLastName() + ", "
			+ member.getFirstName() + "' does not exist!!");
	    }
	}

	repository.deleteAll(deleteMemberList);

	List<Member> membersNotDeleted = new ArrayList<>();

	for (Member member : deleteMemberList)
	{
	    if (repository.existsById(member.getMemberId()))
	    {
		membersNotDeleted.add(member);
	    }
	}

	if (membersNotDeleted.isEmpty())
	{
	    return deleteMemberList.size();
	}
	else
	{
	    StringBuilder errorMessage = new StringBuilder(
		    "Error : " + (deleteMemberList.size() - membersNotDeleted.size())
			    + " user(s) did not get deleted : " + System.lineSeparator());

	    for (Member member : membersNotDeleted)
	    {
		errorMessage.append(member.getLastName() + ", " + member.getFirstName() + " (" + member.getMemberId()
			+ ")" + System.lineSeparator());
	    }

	    throw new AieEntryActionException(errorMessage.toString());
	}
    }
}
