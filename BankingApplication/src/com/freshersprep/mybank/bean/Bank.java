package com.freshersprep.mybank.bean;

import java.util.List;

public class Bank {
	private String bankName;
	private String addrHO;
	private List<BankBranch> bankBranches;
	
	//Getters and setters omitted for brevity
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public List<BankBranch> getBankBranches() {
		return bankBranches;
	}
	public void setBankBranches(List<BankBranch> bankBranches) {
		this.bankBranches = bankBranches;
	}
	@Override
	public String toString() {
		return "Bank [bankName=" + bankName + ", bankBranches=" + bankBranches + "]";
	}
	public String getAddrHO() {
		return addrHO;
	}
	public void setAddrHO(String addrHO) {
		this.addrHO = addrHO;
	}
}
