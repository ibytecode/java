package com.freshersprep.mybank.bean;

import java.util.List;
import com.freshersprep.mybank.utils.Constants;

public class Account {

	private long accountNumber;
	private String typeOfAccount;
	private double accountBalance;
	
	private ChequeBook chequeBook;

	public ChequeBook getChequeBook() {
		return chequeBook;
	}

	public Account(long accNum, String accType, double balance) {
		accountNumber = accNum;
		typeOfAccount = accType;
		accountBalance = balance;
		chequeBook = new ChequeBook();
	}

	// Getters and setters omitted for brevity

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTypeOfAccount() {
		return typeOfAccount;
	}

	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int writeCheque(int chkNum, double amount, String date) {
		if (amount > 0) {
			if (amount <= accountBalance) {
				int isAdded = chequeBook.addCheque(chkNum, amount, date); 
				if (isAdded == Constants.CHEQUE_WRITTEN) {
					accountBalance = accountBalance - amount;
					return Constants.CHEQUE_WRITTEN;
				} else
					return isAdded;
			}
			return Constants.NOT_ENOUGH_BALANCE;	
		}
		return Constants.INVALID_AMOUNT;
	}

	public int deposit(double amount, String date) {
		if(amount > 0) {
			accountBalance = accountBalance + amount;
			chequeBook.addDepositSlip(amount, date);
			return Constants.AMOUNT_DEPOSITED;
		} else
			return Constants.INVALID_AMOUNT;
	}

	public List<Cheque> getRegister() {
		return chequeBook.getChequeList();
	}
}
