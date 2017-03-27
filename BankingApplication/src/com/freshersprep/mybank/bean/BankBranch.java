package com.freshersprep.mybank.bean;

import java.util.List;

public class BankBranch {
	
	private String streetAddress;
	private String city;
	private String state;
	private long zipCode;
	private String country;
	private String phoneNumber;
	private List<AccountHolder> accountHolders;

	//Getters and setters omitted for brevity
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getZipCode() {
		return zipCode;
	}
	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<AccountHolder> getAccountHolders() {
		return accountHolders;
	}
	public void setAccountHolders(List<AccountHolder> accountHolders) {
		this.accountHolders = accountHolders;
	}
}
