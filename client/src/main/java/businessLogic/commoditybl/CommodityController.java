package businessLogic.commoditybl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businessLogicService.commodityblservice.CommodityBLService;
import typeDefinition.myTime;
import vo.commodityvo.CheckResultVO;
import vo.commodityvo.CommodityVO;
import vo.receiptvo.ReceiptVO;

public class CommodityController implements CommodityBLService{
	CommodityBL commodityBL = new CommodityBL();
	
	public void submit(ReceiptVO vo) throws RemoteException{
		// TODO Auto-generated method stub
		commodityBL.submit(vo);
	}

	public ArrayList<CommodityVO> getList(String transferNum) {
		// TODO Auto-generated method stub
		ArrayList<CommodityVO> commodityVOs = commodityBL.getList(transferNum);
		return commodityVOs;
	}

	public CheckResultVO getList(String transferNum, myTime fromTime, myTime toTime) throws RemoteException {
		// TODO Auto-generated method stub
		CheckResultVO result = commodityBL.getList(transferNum, fromTime, toTime);
		return result;
	}

	public ArrayList<CommodityVO> getTotal() {
		// TODO Auto-generated method stub
		ArrayList<CommodityVO> commodityVOs = commodityBL.getTotal();
		return commodityVOs;
	}

	public void renew() {
		// TODO Auto-generated method stub
		commodityBL.renew();
	}


}
