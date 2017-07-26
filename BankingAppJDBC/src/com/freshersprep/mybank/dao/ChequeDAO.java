package com.freshersprep.mybank.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.freshersprep.mybank.bean.Cheque;
import com.freshersprep.mybank.bean.ChequeBook;
import com.freshersprep.mybank.db.ConnectionFactory;
import com.freshersprep.mybank.db.DbUtil;
import com.freshersprep.mybank.utils.AppUtility;

public class ChequeDAO {
	private Connection connection;
	private Statement statement;

	// 1. Create cheque
	public int createCheque(int chequeBookNo, Cheque cheque) {
		
		String query = "INSERT INTO cheque(chq_no,amount,chq_date,chq_book_id) " + "VALUES("
				+ cheque.getChequeNumber() + "," + cheque.getChequeAmount() + ",'" + AppUtility.getDateStr(cheque.getChequeDate()) + "'," +
				chequeBookNo + ")";
		int insertedRow = 0;
		int insertedKeyValue = 0;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			insertedRow = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			if (insertedRow == 1) {
				ResultSet rs = statement.getGeneratedKeys();
				rs.next();
				insertedKeyValue = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		return insertedKeyValue;
	}
	
	public List<Cheque> getCheques(ChequeBook chequeBook) {
		String query = "SELECT * FROM cheque WHERE chq_book_id=" + chequeBook.getId() 
						+ " AND chq_no BETWEEN " + chequeBook.getStartRange() 
						+ " AND " + chequeBook.getEndRange();
		
		List<Cheque> list = new ArrayList<Cheque>();
		Cheque cheque = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				cheque = new Cheque();
				cheque.setId(rs.getInt("id"));
				cheque.setChequeNumber(rs.getInt("chq_no"));
				cheque.setChequeAmount(rs.getDouble("amount"));
				cheque.setChequeDate(rs.getDate("chq_date"));
				
				list.add(cheque);
			}
		} catch (SQLException e) {
			list = null;
			//System.out.println("ERROR: Database Error has occurred");
		} finally {
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		//System.out.println(list);
		return list;
	}
}
