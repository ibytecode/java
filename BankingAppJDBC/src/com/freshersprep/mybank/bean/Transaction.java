package com.freshersprep.mybank.bean;

import java.util.Date;

public class Transaction {

	private int id;
	private Date transactionDate;
	private String description;
	private double amount;
	private TransactionType type;
	
	private Account account;

	
	public Transaction() {
		super();
	}

	public Transaction(Date transactionDate, String description, double amount, TransactionType type, Account account) {
		super();
		this.transactionDate = transactionDate;
		this.description = description;
		this.amount = amount;
		this.type = type;
		this.account = account;
	}

	// Getters and setters omitted for brevity
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", transactionDate=" + transactionDate + ", description=" + description
				+ ", amount=" + amount + ", type=" + type + ", account=" + account + "]";
	}
	
	
}
