package com.freshersprep.mybank.bean;

import java.util.Date;

public class Cheque {
	private int id;
	private int chequeNumber;
	private double chequeAmount;
	private Date chequeDate;
	private ChequeBook chequeBook;
	
	public Cheque() {
		super();
	}

	public Cheque(int chkNum, double chkAmt, Date chkDate) {
		chequeNumber = chkNum;
		chequeAmount = chkAmt;
		chequeDate = chkDate;
	}

	// Getters and setters omitted for brevity
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chequeNumber;
		return result;
	}

	public int getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(int chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public double getChequeAmount() {
		return chequeAmount;
	}

	public void setChequeAmount(double chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	public Date getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public ChequeBook getChequeBook() {
		return chequeBook;
	}

	public void setChequeBook(ChequeBook chequeBook) {
		this.chequeBook = chequeBook;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cheque other = (Cheque) obj;
		if (chequeNumber != other.chequeNumber)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
