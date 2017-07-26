package com.freshersprep.mybank.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import com.freshersprep.mybank.bean.AccountHolder;
import com.freshersprep.mybank.db.ConnectionFactory;
import com.freshersprep.mybank.db.DbUtil;
import com.freshersprep.mybank.utils.AppUtility;

public class AccountHolderDAO {
	private Connection connection;
	private Statement statement;
	
	// 1. Create Account holder
	public int createAccountHolder(AccountHolder accountHolder) {

		String date = AppUtility.getDateStr(Calendar.getInstance().getTime());
		String query = "INSERT INTO account_holder(fname,phone,date) "
				+ "VALUES('" + accountHolder.getFirstName() + "','" + accountHolder.getPhoneNumber() + "','"
			    + date
				+ "')";
		int insertedRow = 0;
		int insertedKeyValue = 0;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			insertedRow = statement.executeUpdate(query,
					Statement.RETURN_GENERATED_KEYS);
			if (insertedRow == 1) {
				ResultSet rs = statement.getGeneratedKeys();
				rs.next();
				insertedKeyValue = rs.getInt(1);
				accountHolder.setId(insertedKeyValue);
				AccountDAO accountDAO = new AccountDAO();
				insertedKeyValue = accountDAO.createAccount(accountHolder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return insertedKeyValue;
	}
	
	
	// 2. Update Account holder
	// 3. Delete Account holder
	// 4. Get account holder details
	// 5. Get list of Account holder in a particular Branch 

}
