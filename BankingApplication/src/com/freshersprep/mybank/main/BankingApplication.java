package com.freshersprep.mybank.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.freshersprep.mybank.bean.Account;
import com.freshersprep.mybank.bean.AccountHolder;
import com.freshersprep.mybank.bean.Cheque;
import com.freshersprep.mybank.utils.Constants;

public class BankingApplication {

	/**
	 * @param args
	 */
	public enum Command {
		CREATE, WRITE, BALANCE, REGISTER, CHEQUES, DEPOSIT, INVALID_COMMAND;
		public static Command toStr(String str) {
			try {
				return valueOf(str);
			} catch (Exception ex) {
				return INVALID_COMMAND;
			}
		}
	}

	public static void main(String[] args) {
		/* 1. Create a new account object to do app operations */
		AccountHolder accountHolder = null;
		Account account = null;
		BankingApplication myBank = new BankingApplication();

		// 2. To read input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 3. Tokenize the input
		StringTokenizer st;
		String query = "";
		String[] input;
		int chkNum;
		double chkAmount;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String currentDate = dateFormat.format(Calendar.getInstance().getTime());

		List list;

		System.out.println("Create a new account using the command 'Create [Name] [Account Type] [Minimum Amount]' to do transactions");
		// 6. Repeat until user enters quit
		while (true) {
			try {
				// 2. To read input
				query = br.readLine();
				if (query.equalsIgnoreCase("quit"))
					break;
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 3. Tokenize the input
			st = new StringTokenizer(query);
			input = new String[st.countTokens()];
			int i = 0;
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				input[i] = token;
				i++;
			}

			// 5. Find the command
			switch (/* 4. Get the command */ Command.toStr(input[0].toUpperCase())) {
			case CREATE: {
				if (input.length == 4) {
					String name = input[1];
					String accType = input[2];
					double minAmt = Double.parseDouble(input[3]);
					account = new Account(12006, accType, minAmt);
					accountHolder = new AccountHolder(name, account);
					System.out.println(
							"Account created with minimum balance #" + minAmt);
				} else
					System.out.println(
							"Please use the Command 'Create [Name] [Account Type] [Minimum Amount]'");
				break;
			}
			case WRITE: {
				if (input.length == 3) {
					chkNum = Integer.parseInt(input[1]);
					chkAmount = Double.parseDouble(input[2]);
					myBank.processWriteCommand(chkNum, chkAmount, currentDate, account);
				} else
					System.out.println(
							"Cheque Number or Amount is missing. Please use the Command 'write [Cheque Number] [Amount]'");
				break;
			}
			case BALANCE: {
				if (input.length == 1)
					System.out.println("Your Balance is " + account.getAccountBalance());
				else
					System.out.println("Command Error: PLEASE USE the command '[Balance]'");
				break;
			}
			case REGISTER: {
				if (input.length == 1) {
					list = account.getRegister();
					myBank.printList(list);
					System.out.println("\t\t\t         Balance " + account.getAccountBalance());
				} else
					System.out.println("Command Error: PLEASE USE the command '[Register]'");
				break;
			}
			case CHEQUES: {
				if (input.length == 3) {
					list = account.getChequeBook().chequeRange(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
					myBank.printList(list);
				} else
					System.out.println("Please Enter the Start and End Range");
				break;
			}
			case DEPOSIT: {
				if (input.length == 2) {
					try {
						double amt = Double.parseDouble(input[1]);
						int isValid = account.deposit(amt, currentDate);
						if (isValid == Constants.AMOUNT_DEPOSITED)
							System.out.println("Deposited $" + input[1] + " on " + currentDate);
						else if (isValid == Constants.INVALID_AMOUNT)
							System.out.println("Please enter a valid Amount");
					} catch (NumberFormatException e) {
						System.out.println("Please Enter a Number");
					}
				} else
					System.out.println("Please Enter the Deposit Amount.");
				break;
			}
			case INVALID_COMMAND: {
				System.out.println("Invalid Command. Commands - CREATE, WRITE, DEPOSIT, BALANCE, CHEQUES, REGISTER");
				break;
			}
			}
		}
	}

	public void processWriteCommand(int chkNum, double chkAmount, String date, Account account) {
		int isValid = account.writeCheque(chkNum, chkAmount, date);
		switch (isValid) {
		case Constants.CHEQUE_WRITTEN:
			System.out.println("Cheque #" + chkNum + " for $" + chkAmount + " written");
			break;
		case Constants.NOT_ENOUGH_BALANCE:
			System.out.println("You do not have sufficient balance");
			break;
		case Constants.INVALID_CHEQUE_NUMBER:
			System.out.println("Invalid Cheque Number.");
			break;
		case Constants.DUPLICATE_CHEQUE_NUMBER:
			System.out.println("Cheque number #" + chkNum + " is already used");
			break;
		case Constants.INVALID_AMOUNT:
			System.out.println("Please enter a valid Amount");
			break;
		}
	}

	public void printList(List<Cheque> list) {
		Iterator<Cheque> it = list.iterator();
		if (list.isEmpty())
			System.out.println("No Transactions");
		else {
			System.out.println("Date\t\t Transaction Type\t Amount");
			while (it.hasNext()) {
				Cheque c = it.next();
				int chkNum = c.getChequeNumber();
				double chkAmount = c.getChequeAmount();
				String date = c.getChequeDate();
				if (chkNum == -1) {
					System.out.print(date);
					System.out.print("\t     Deposit \t\t ");
					System.out.println(chkAmount);
				} else {
					System.out.print(date);
					System.out.print("\t     CHEQUE #" + chkNum + " \t\t ");
					System.out.println(chkAmount);
				}
			}
		}
	}
}
