package businessLogic.commoditybl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businessLogic.receiptbl.ReceiptBL;
import businessLogicService.commodityblservice.CommodityBLService;
import data.CommodityDataImpl;
import dataService.CommodityDataService;
import po.commoditypo.CommodityPO;
import typeDefinition.myTime;
import vo.commodityvo.CommodityVO;
import vo.receiptvo.ReceiptVO;

public class CommodityController implements CommodityBLService{
	CommodityBL commodityBL = new CommodityBL();
	
	public void submit(ReceiptVO vo) {
		// TODO Auto-generated method stub
		commodityBL.submit(vo);
	}

	public ArrayList<CommodityVO> getList(String TransferNum) {
		// TODO Auto-generated method stub
		ArrayList<CommodityVO> commodityVOs = commodityBL.getList(TransferNum);
		return commodityVOs;
	}

	public ArrayList<CommodityVO> getList(String TransferNum, myTime fromTime, myTime toTime) {
		// TODO Auto-generated method stub
		ArrayList<CommodityVO> commodityVOs = commodityBL.getList(TransferNum, fromTime, toTime);
		return commodityVOs;
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
