package com.freshersprep.mybank.bean;

import java.util.ArrayList;
import java.util.List;

public class ChequeBook {

	private int id;
	private int startRange;
	private int endRange;
	private int accountNo;
	private ChequeBookStatus status;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStartRange() {
		return startRange;
	}

	public void setStartRange(int startRange) {
		this.startRange = startRange;
	}

	public int getEndRange() {
		return endRange;
	}

	public void setEndRange(int endRange) {
		this.endRange = endRange;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	private List<Cheque> chequeList;

	public ChequeBook() {
		chequeList = new ArrayList<Cheque>();
	}

	public List<Cheque> getChequeList() {
		return chequeList;
	}

	public ChequeBookStatus getStatus() {
		return status;
	}

	public void setStatus(ChequeBookStatus status) {
		this.status = status;
	}	
}
