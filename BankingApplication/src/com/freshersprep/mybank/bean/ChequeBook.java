package com.freshersprep.mybank.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.freshersprep.mybank.utils.Constants;

public class ChequeBook {

	private final int numberOfCheques = 100;
	private List<Cheque> chequeList;

	public ChequeBook() {
		chequeList = new ArrayList<Cheque>();
	}

	public int addCheque(int chkNum, double amount, String date) {
		if (chkNum > 0 && chkNum <= numberOfCheques) {
			Cheque c = new Cheque(chkNum, amount, date);
			if(!chequeList.contains(c)) {
				chequeList.add(c);
				return Constants.CHEQUE_WRITTEN;
			} else
				return Constants.DUPLICATE_CHEQUE_NUMBER;	
		}
		return Constants.INVALID_CHEQUE_NUMBER;
	}

	public void addDepositSlip(double amount, String date) {
		Cheque c = new Cheque(-1, amount, date);
		chequeList.add(c);
	}

	public List<Cheque> chequeRange(int start, int end) {
		Cheque c;
		Iterator<Cheque> it = chequeList.iterator();
		List<Cheque> cheques = new ArrayList<Cheque>();
		while (it.hasNext()) {
			c = it.next();
			int chequeNumber = c.getChequeNumber();
			if (chequeNumber >= start && chequeNumber <= end && chequeNumber != -1)
				cheques.add(c);
		}
		return cheques;
	}

	public List<Cheque> getChequeList() {
		return chequeList;
	}
}
