package com.freshersprep.mybank.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import com.freshersprep.mybank.bean.Account;
import com.freshersprep.mybank.bean.AccountHolder;
import com.freshersprep.mybank.bean.Cheque;
import com.freshersprep.mybank.bean.Transaction;
import com.freshersprep.mybank.dao.AccountDAO;
import com.freshersprep.mybank.dao.AccountHolderDAO;
import com.freshersprep.mybank.dao.ChequebookDAO;
import com.freshersprep.mybank.dao.TransactionDAO;
import com.freshersprep.mybank.exception.ApplicationException;
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
		/* 1. Create a new account object to do account related operations */
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String currentDate = dateFormat.format(Calendar.getInstance().getTime());

		AccountHolderDAO holderDAO = new AccountHolderDAO();
		AccountDAO accountDAO = new AccountDAO();
		TransactionDAO transactionDAO = new TransactionDAO();

		System.out.println(Constants.LOG_CREATE);
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
				if (input.length == 5) {
					String name = input[1];
					String phone = input[2];
					String accType = input[3];
					double minAmt = Double.parseDouble(input[4]);
					account = new Account(accType, minAmt);
					accountHolder = new AccountHolder(name, phone, account);
					int result = holderDAO.createAccountHolder(accountHolder);

					System.out.println(
							String.format(Constants.LOG_ACCOUNT_CREATED, result, Constants.formatter.format(minAmt)));

				} else
					System.out.println(String.format(Constants.LOG_COMMAND_ERROR, Constants.COMMAND_CREATE));
				break;
			}
			case BALANCE: {
				if (input.length == 2) {
					int accNum = Integer.parseInt(input[1]);
					System.out.println(String.format(Constants.LOG_BALANCE,
							Constants.formatter.format(accountDAO.getBalance(accNum))));
				} else
					System.out.println(String.format(Constants.LOG_COMMAND_ERROR, Constants.COMMAND_BALANCE));
				break;
			}
			case DEPOSIT: {
				if (input.length == 3) {
					int accNum = 0;
					double amt = 0;
					try {
						accNum = Integer.parseInt(input[1]);
					} catch (NumberFormatException e) {
						System.out.println(Constants.LOG_VALID_ACC_NO);
					}
					try {
						amt = Double.parseDouble(input[2]);
					} catch (NumberFormatException e) {
						System.out.println(Constants.LOG_VALID_AMOUNT);
					}

					try {
						if (accNum > 0 && amt > 0) {
							int result = accountDAO.deposit(accNum, amt);
							switch (result) {
							case Constants.AMOUNT_DEPOSITED:
								System.out.println(String.format(Constants.LOG_DEPOSITED,
										Constants.formatter.format(amt), currentDate));
								break;
							case Constants.INVALID_ACCOUNT_NO:
								System.out.println(Constants.LOG_DEPOSIT_FAILED);
								break;
							default:
								break;
							}
						} else {
							System.out.println(Constants.LOG_DEPOSIT_ERROR);
						}
					} catch (ApplicationException e) {
						System.out.println(e.getMessage());
					}
				} else
					System.out.println(String.format(Constants.LOG_COMMAND_ERROR, Constants.COMMAND_DEPOSIT));
				break;
			}
			case WRITE: {
				if (input.length == 4) {
					int accNum = Integer.parseInt(input[1]);
					chkNum = Integer.parseInt(input[2]);
					chkAmount = Double.parseDouble(input[3]);
					Cheque cheque = new Cheque(chkNum, chkAmount, Calendar.getInstance().getTime());
					myBank.processWriteCommand(accNum, cheque, accountDAO);
				} else
					System.out.println(String.format(Constants.LOG_COMMAND_ERROR, Constants.COMMAND_WRITE));
				break;
			}
			case REGISTER: {
				if (input.length == 2) {
					int accNum = Integer.parseInt(input[1]);
					List<Transaction> transactions;
					try {
						transactions = transactionDAO.getTransactions(accNum);
						myBank.printTransactions(transactions);
					} catch (ApplicationException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}

					// System.out.println("\t\t\t Balance " +
					// account.getAccountBalance());
				} else
					System.out.println(String.format(Constants.LOG_COMMAND_ERROR, Constants.COMMAND_REGISTER));
				break;
			}
			case CHEQUES: {
				if (input.length == 4) {
					int accNum = Integer.parseInt(input[1]);
					int startRange = Integer.parseInt(input[2]);
					int endRange = Integer.parseInt(input[3]);

					List<Cheque> cheques = new ChequebookDAO().getCheques(accNum, startRange, endRange);
					if (cheques != null)
						myBank.printCheques(cheques);
					else
						System.out.println(Constants.LOG_CHEQUEBOOK_ERROR);
				} else
					System.out.println(String.format(Constants.LOG_COMMAND_ERROR, Constants.COMMAND_CHEQUES));
				break;
			}
			case INVALID_COMMAND: {
				System.out.println(Constants.LOG_COMMANDS);
				break;
			}
			}
		}
	}

	public void processWriteCommand(int accNo, Cheque cheque, AccountDAO accountDAO) {
		int result;
		try {
			result = accountDAO.writeCheque(accNo, cheque);
			switch (result) {
			case Constants.CHEQUE_WRITTEN:
				System.out.println(
						"Cheque #" + cheque.getChequeNumber() + " for $" + cheque.getChequeAmount() + " written");
				break;
			case Constants.NOT_ENOUGH_BALANCE:
				System.out.println("You do not have sufficient balance");
				break;
			case Constants.INVALID_CHEQUE_NUMBER:
				System.out.println("Invalid Cheque Number.");
				break;
			case Constants.INVALID_CHEQUEBOOK_NUMBER:
				System.out.println("Invalid Chequebook Number");
				break;
			case Constants.CHEQUE_NOT_WRITTEN:
				System.out.println("Cheque transaction failed");
				break;
			case Constants.INVALID_ACCOUNT_NO:
				System.out.println("Invalid Account Number");
				break;
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public void printTransactions(List<Transaction> transactions) {
		if (transactions.isEmpty())
			System.out.println("No Transactions");
		else {
			System.out.println("Date\t\t Description\t\t Type\t\t Amount");
			for (Transaction transaction : transactions) {
				// System.out.print(transaction.getId() + "\t");
				System.out.print(transaction.getTransactionDate() + "\t");
				System.out.print(transaction.getDescription() + "\t\t");
				System.out.print(transaction.getType().name() + "\t\t");
				System.out.println(transaction.getAmount());

			}
		}
	}

	public void printCheques(List<Cheque> cheques) {
		if (cheques.isEmpty())
			System.out.println("No Cheques");
		else {
			System.out.println("Cheque#\t\t Date\t\t Amount");
			for (Cheque cheque : cheques) {
				System.out.print(cheque.getChequeNumber() + "\t\t");
				System.out.print(cheque.getChequeDate() + "\t");
				System.out.println(cheque.getChequeAmount());
			}
		}
	}
}
