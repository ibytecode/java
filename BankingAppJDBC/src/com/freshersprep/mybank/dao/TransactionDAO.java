package com.freshersprep.mybank.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.freshersprep.mybank.bean.Account;
import com.freshersprep.mybank.bean.Transaction;
import com.freshersprep.mybank.bean.TransactionType;
import com.freshersprep.mybank.db.ConnectionFactory;
import com.freshersprep.mybank.db.DbUtil;
import com.freshersprep.mybank.exception.ApplicationException;
import com.freshersprep.mybank.utils.AppUtility;

public class TransactionDAO {
	private Connection connection;
	private Statement statement;

	// 1. Create transaction
	public int createTransaction(Transaction transaction) {
		String query = "INSERT INTO transaction(tnc_date,description,amount,type,acc_id) " + "VALUES('"
				+ AppUtility.getDateStr(transaction.getTransactionDate()) + "','" 
				+ transaction.getDescription() + "'," + transaction.getAmount() + ",'" 
				+ transaction.getType().name() + "'," + transaction.getAccount().getAccountNumber()
				+ ")";
		
		int insertedRow = 0;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			insertedRow = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return insertedRow;
	}
	
	// 2. Get account transactions
	public List<Transaction> getTransactions(int accountNo) throws ApplicationException {
		String query = "SELECT * FROM transaction WHERE acc_id=" + accountNo;
		List<Transaction> list = new ArrayList<Transaction>();
		Transaction transaction = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				transaction = new Transaction();
				/*Retrieve one transaction details 
				and store it in transaction object*/								
				transaction.setId(rs.getInt("id"));
				transaction.setTransactionDate(rs.getDate("tnc_date"));
				transaction.setDescription(rs.getString("description"));
				transaction.setAmount(rs.getDouble("amount"));
				transaction.setType(TransactionType.valueOf(TransactionType.class, rs.getString("type")));
				transaction.setAccount(new Account(rs.getInt("acc_id")));
				//add each transaction to the list
				list.add(transaction);
			}
		} catch (SQLException e) {
			ApplicationException exception = new ApplicationException(e.getMessage());
			throw exception;
		} finally {
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		//System.out.println(list);
		return list;
	}
}
