package businessLogic.commoditybl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import businessLogicService.commodityblservice.CommodityBLService;
import vo.commodityvo.CheckResultVO;
import vo.commodityvo.CommodityVO;
import vo.receiptvo.DepotInReceiptVO;
import vo.receiptvo.DepotOutReceiptVO;

public class CommodityController implements CommodityBLService{
	private CommodityBL commodityBL;
	
	public CommodityController() throws NamingException, MalformedURLException, RemoteException, NotBoundException{
		this.commodityBL = new CommodityBL();
	}

	public ArrayList<CommodityVO> getList(String transferNum) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<CommodityVO> commodityVOs = commodityBL.getList(transferNum);
		return commodityVOs;
	}

	public CheckResultVO getList(String transferNum, Date fromTime, Date toTime) throws RemoteException {
		// TODO Auto-generated method stub
		CheckResultVO result = commodityBL.getList(transferNum, fromTime, toTime);
		return result;
	}

	public ArrayList<CommodityVO> getTotal() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<CommodityVO> commodityVOs = commodityBL.getTotal();
		return commodityVOs;
	}

	public void renew(String transferNum) throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		commodityBL.renew(transferNum);
	}

	public void submitIn(DepotInReceiptVO vo) throws RemoteException, NamingException, SQLException {
		// TODO Auto-generated method stub
		commodityBL.submitIn(vo);
	}

	public void submitOut(DepotOutReceiptVO vo) throws RemoteException, NamingException, SQLException {
		// TODO Auto-generated method stub
		commodityBL.submitOut(vo);
	}


}
