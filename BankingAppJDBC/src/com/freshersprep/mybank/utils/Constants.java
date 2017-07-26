package com.freshersprep.mybank.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Constants {
	public static NumberFormat formatter = new DecimalFormat("#0.00");
	
	public static final int CHEQUE_WRITTEN = 0;
	public static final int NOT_ENOUGH_BALANCE = 1;
	public static final int INVALID_CHEQUE_NUMBER = 2;
	public static final int AMOUNT_DEPOSITED = 3;
	public static final int INVALID_AMOUNT = 4;
	public static final int DUPLICATE_CHEQUE_NUMBER = 5;
	public static final int INVALID_CHEQUEBOOK_NUMBER = 6;
	public static final int CHEQUE_NOT_WRITTEN = 7;
	public static final int INVALID_ACCOUNT_NO = 8;
	
	public static final String COMMAND_CREATE = "Create [Name] [phone] [Account Type] [Minimum Amount]";
	public static final String COMMAND_WRITE = "write [Account no] [Cheque Number] [Amount]";
	public static final String COMMAND_REGISTER = "Register [Account No]";
	public static final String COMMAND_CHEQUES = "Cheques [Account No] [Start range] [End range]";
	public static final String COMMAND_DEPOSIT = "Deposit [Account No] [Amount]";
	public static final String COMMAND_BALANCE = "Balance [Account No]";
	
	//Log Statements
	public static final String LOG_CREATE = "Create a new account using the command '" + Constants.COMMAND_CREATE + "' to do transactions";
	public static final String LOG_ACCOUNT_CREATED = "Account created with account number #%d and minimum balance is $%s";
	public static final String LOG_BALANCE = "Your Balance is $%s";
	public static final String LOG_VALID_ACC_NO = "Please Enter a valid Account Number";
	public static final String LOG_VALID_AMOUNT = "Please Enter a valid Amount";
	public static final String LOG_DEPOSITED = "Deposited $%s on %s";
	public static final String LOG_DEPOSIT_FAILED = "Unable to deposit amount, please check your account number";
	public static final String LOG_DEPOSIT_ERROR = "Enter a valid account no and amount";
	public static final String LOG_CHEQUEBOOK_ERROR = "ChequeBook unavailable";
	
	public static final String LOG_COMMAND_ERROR = "Command Error: PLEASE USE the command %s";
	public static final String LOG_COMMANDS = "Invalid Command. Commands - CREATE, WRITE, DEPOSIT, BALANCE, CHEQUES, REGISTER";
	
	
	//tags - mysql enum, jdbc enum column, string format with placeholders
	
}
