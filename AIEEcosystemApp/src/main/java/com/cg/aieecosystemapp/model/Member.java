package com.cg.aieecosystemapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int memberId;
    private String firstName;
    private String lastName;
    private String position;
    private String email;
    private String tier;
    private String password;
    private boolean accountLocked;

    public Member()
    {
    }

    public Member(String firstName, String lastName, String position, String email, String tier, String password,
	    boolean accountLocked)
    {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.position = position;
	this.email = email;
	this.tier = tier;
	this.password = password;
	this.accountLocked = accountLocked;
    }

    public int getMemberId()
    {
	return memberId;
    }

    public void setMemberId(int memberId)
    {
	this.memberId = memberId;
    }

    public String getFirstName()
    {
	return firstName;
    }

    public void setFirstName(String firstName)
    {
	this.firstName = firstName;
    }

    public String getLastName()
    {
	return lastName;
    }

    public void setLastName(String lastName)
    {
	this.lastName = lastName;
    }

    public String getPosition()
    {
	return position;
    }

    public void setPosition(String position)
    {
	this.position = position;
    }

    public String getEmail()
    {
	return email;
    }

    public void setEmail(String email)
    {
	this.email = email;
    }

    public String getTier()
    {
	return tier;
    }

    public void setTier(String tier)
    {
	this.tier = tier;
    }

    public String getPassword()
    {
	return password;
    }

    public void setPassword(String password)
    {
	this.password = password;
    }

    public boolean isAccountLocked()
    {
	return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked)
    {
	this.accountLocked = accountLocked;
    }

    @Override
    public String toString()
    {
	return "[" + this.firstName + ", " + this.lastName + " - " + this.email + "]";
    }
}
