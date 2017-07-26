package com.freshersprep.mybank.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.freshersprep.mybank.bean.Cheque;
import com.freshersprep.mybank.bean.ChequeBook;
import com.freshersprep.mybank.bean.ChequeBookStatus;
import com.freshersprep.mybank.db.ConnectionFactory;
import com.freshersprep.mybank.db.DbUtil;
import com.freshersprep.mybank.exception.ApplicationException;
import com.freshersprep.mybank.utils.Constants;

public class ChequebookDAO {
	public static final int START_RANGE = 1;
	public static final int END_RANGE = 100;
	
	private Connection connection;
	private Statement statement;
	
	// 1. Create checkbook
	public int createChequebook(int accNo) {
		String query = "INSERT INTO chequebook(start_range,end_range,acc_id,status) " + "VALUES("
				+ START_RANGE + "," + END_RANGE + "," + accNo + ",'" + ChequeBookStatus.ACTIVE + "')";
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
	
	// 2. Write Cheque
	public int writeCheque(int accNo, Cheque cheque) throws ApplicationException {
		/*List<ChequeBook> chequebooks = getChequebooks(accNo);
		ChequeBook book = null;
		for(ChequeBook chequeBook : chequebooks) {
			if(chequeBook.getStartRange() == START_RANGE && chequeBook.getEndRange() == END_RANGE) {
				book = chequeBook;
				break;
			}
		}*/
		ChequeBook book = getChequebook(accNo);
		if(book != null) {
			if (cheque.getChequeNumber() >= book.getStartRange() && cheque.getChequeNumber() <= book.getEndRange()) {
				ChequeDAO chequeDAO = new ChequeDAO();
				int result = chequeDAO.createCheque(book.getId(), cheque);
				if(result > 0) {
					return Constants.CHEQUE_WRITTEN;
				}
			} else {
				return Constants.INVALID_CHEQUE_NUMBER;
			}
		} else {
			return Constants.INVALID_CHEQUEBOOK_NUMBER;
		}
		return Constants.CHEQUE_NOT_WRITTEN;
	}
	
	// 3. Get Chequebook
	public ChequeBook getChequebook(int accountNo) {
		String query = "SELECT * FROM chequebook WHERE acc_id=" + accountNo + " AND status='ACTIVE'";
		ChequeBook chequeBook = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				chequeBook = new ChequeBook();
				/*Retrieve one checkbook details 
				and store it in chequebook object*/								
				chequeBook.setId(rs.getInt("id"));
				chequeBook.setStartRange(rs.getInt("start_range"));
				chequeBook.setEndRange(rs.getInt("end_range"));
				chequeBook.setAccountNo(rs.getInt("acc_id"));
				chequeBook.setStatus(ChequeBookStatus.valueOf(ChequeBookStatus.class, rs.getString("status")));
			}
		} catch (SQLException e) {
			//System.out.println("ERROR: Database Error has occurred");
		} finally {
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		//System.out.println(chequeBook);
		return chequeBook;
	}
	
	// 4. Get cheques in a chequebook
	public List<Cheque> getCheques(int accountNo, int startRange, int endRange) {
		ChequeBook book = getChequebook(accountNo);
		List<Cheque> cheques = null;
		if (book != null) {
			ChequeDAO chequeDAO = new ChequeDAO();
			book.setStartRange(startRange);
			book.setEndRange(endRange);

			cheques = chequeDAO.getCheques(book);
		}
		return cheques;
	}
	
	
	// 5. Update Chequebook
	// 6. Delete Chequebook
	// 7. Get list of Checkbooks for an account 

	/*public List<ChequeBook> getChequebooks(int accountNo) {
		String query = "SELECT * FROM chequebook WHERE acc_id=" + accountNo;
		List<ChequeBook> list = new ArrayList<ChequeBook>();
		ChequeBook chequeBook = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				chequeBook = getChequebook(rs);//new ChequeBook();
				Retrieve one checkbook details 
				and store it in chequebook object								
				chequeBook.setId(rs.getInt("id"));
				chequeBook.setStartRange(rs.getInt("start_range"));
				chequeBook.setEndRange(rs.getInt("end_range"));
				chequeBook.setAccountNo(rs.getInt("acc_id"));
				
				//add each chequebook to the list
				list.add(chequeBook);
			}
		} catch (SQLException e) {
			System.out.println("ERROR: Database Error has occurred");
		} finally {
			DbUtil.close(statement);
			DbUtil.close(connection);
		}
		System.out.println(list);
		return list;
	}*/
	
	/*private ChequeBook getChequebook(ResultSet rs) throws SQLException {
		ChequeBook chequeBook = new ChequeBook();
		chequeBook.setId(rs.getInt("id"));
		chequeBook.setStartRange(rs.getInt("start_range"));
		chequeBook.setEndRange(rs.getInt("end_range"));
		chequeBook.setAccountNo(rs.getInt("acc_id"));
	
		return chequeBook;
	}*/
}
