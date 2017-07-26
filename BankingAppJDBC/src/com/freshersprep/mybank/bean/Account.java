package com.freshersprep.mybank.bean;

public class Account {
	private long accountNumber;
	private String typeOfAccount;
	private double accountBalance;
	private AccountHolder accountHolder;
	private ChequeBook chequeBook;

	public ChequeBook getChequeBook() {
		return chequeBook;
	}

	public Account(long accountNumber) {
		super();
		this.accountNumber = accountNumber;
	}

	public Account(String accType, double balance) {
		typeOfAccount = accType;
		accountBalance = balance;
		chequeBook = new ChequeBook();
	}
	
	public Account(long accNum, String accType, double balance) {
		accountNumber = accNum;
		typeOfAccount = accType;
		accountBalance = balance;
		chequeBook = new ChequeBook();
	}

	// Getters and setters omitted for brevity
	
	public Account() {
	}

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

	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", typeOfAccount=" + typeOfAccount + ", accountBalance="
				+ accountBalance + ", accountHolder=" + accountHolder + ", chequeBook=" + chequeBook + "]";
	}
}
