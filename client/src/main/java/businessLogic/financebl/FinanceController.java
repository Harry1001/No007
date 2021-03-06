package businessLogic.financebl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import businessLogicService.financeblservice.FinanceBLService;
import myexceptions.InfoBLException;
import vo.financevo.AddUpResultVO;
import vo.financevo.FinanceVO;
import vo.financevo.ProfitVO;
import vo.receiptvo.ChargeReceiptVO;
import vo.receiptvo.PayReceiptVO;
import vo.receiptvo.ReceiptVO;
import vo.salaryfeevo.SalaryFeeVO;

public class FinanceController implements FinanceBLService{

	private FinanceBL financeBL;
	
	public FinanceController() throws NamingException, MalformedURLException, RemoteException, NotBoundException{
		this.financeBL = new FinanceBL();
	}

	public FinanceVO getCredit(int year) throws IOException, ClassNotFoundException {
		FinanceVO financeVO = financeBL.getCredit(year);
		return financeVO;
	}

	public ArrayList<SalaryFeeVO> calSalary() throws RemoteException, SQLException, MalformedURLException, NotBoundException {
		// TODO Auto-generated method stub
		ArrayList<SalaryFeeVO> salaryFeeVOs = financeBL.calSalary();
		return salaryFeeVOs;
	}

	public ArrayList<ReceiptVO> seeRecord(Date fromTime, Date toTime) throws RemoteException, MalformedURLException, NotBoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<ReceiptVO> receiptVOs = financeBL.seeRecord(fromTime, toTime);
		return receiptVOs;
	}

	public ArrayList<ChargeReceiptVO> checkStore(Date fromTime, Date toTime, String StoreNum) throws RemoteException, MalformedURLException, SQLException, NotBoundException{
		// TODO Auto-generated method stub
		ArrayList<ChargeReceiptVO> receiptVOs = financeBL.checkStore(fromTime, toTime, StoreNum);
		return receiptVOs;
	}

	public AddUpResultVO addUp() {
		// TODO Auto-generated method stub
		AddUpResultVO sum = financeBL.addUp();
		return sum;
	}

	public ProfitVO checkProfit() throws RemoteException {
		// TODO Auto-generated method stub
		ProfitVO profitVO = financeBL.checkProfit();
		return profitVO;
	}

	public void makeCredit(int year) throws RemoteException, NamingException, SQLException, MalformedURLException, NotBoundException {
		// TODO Auto-generated method stub
		financeBL.makeCredit(year);
	}



	public void submitIn(ChargeReceiptVO vo) throws RemoteException, SQLException, MalformedURLException, NotBoundException, InfoBLException {
		financeBL.submitIn(vo);		
	}



	public void submitOut(PayReceiptVO vo) throws RemoteException, SQLException, MalformedURLException, NotBoundException {
		financeBL.submitOut(vo);
	}
}
