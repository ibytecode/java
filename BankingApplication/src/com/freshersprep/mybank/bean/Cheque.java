package com.freshersprep.mybank.bean;

public class Cheque {
	private int chequeNumber;
	private double chequeAmount;
	private String chequeDate;

	public Cheque(int chkNum, double chkAmt, String chkDate) {
		chequeNumber = chkNum;
		chequeAmount = chkAmt;
		chequeDate = chkDate;
	}

	// Getters and setters omitted for brevity
	public int getChequeNumber() {
		return chequeNumber;
	}

	public double getChequeAmount() {
		return chequeAmount;
	}

	public String getChequeDate() {
		return chequeDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chequeNumber;
		return result;
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
	
	
}
