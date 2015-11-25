package businessLogicService.commodityblservice;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import vo.commodityvo.CheckResultVO;
import vo.commodityvo.CommodityVO;
import vo.receiptvo.ReceiptVO;

public interface CommodityBLService {

	public void submit(ReceiptVO vo) throws RemoteException;
	
	public ArrayList<CommodityVO> getList(String transferNum);
	
	public CheckResultVO getList(String transferNum,Date fromTime,Date toTime) throws RemoteException;
	
	public ArrayList<CommodityVO> getTotal();
	
	public void renew();
	
}
