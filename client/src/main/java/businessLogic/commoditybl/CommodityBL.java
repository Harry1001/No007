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

public class CommodityBL implements CommodityBLService{

	public void submit(ReceiptVO vo) {
		// TODO Auto-generated method stub
		ReceiptBL receipt = new ReceiptBL();
		receipt.createReceipt(vo);
	}

	public ArrayList<CommodityVO> getList(String TransferNum) {
		// TODO Auto-generated method stub
		CommodityDataService commodityData = new CommodityDataImpl();
		ArrayList<CommodityVO> commodityVOs = new ArrayList<CommodityVO>();
		ArrayList<CommodityPO> commodityPOs = new ArrayList<CommodityPO>();
		try {
			commodityPOs = commodityData.check(TransferNum);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(CommodityPO c:commodityPOs){
			commodityVOs.add(new CommodityVO(c));
		}
		return commodityVOs;
	}

	public ArrayList<CommodityVO> getList(String TransferNum, myTime fromTime, myTime toTime) {
		// TODO Auto-generated method stub
		ReceiptBL receipt = new ReceiptBL();
		ArrayList<CommodityVO> commodityVOs = new ArrayList<CommodityVO>();
		return null;
	}

	public ArrayList<CommodityVO> getTotal() {
		// TODO Auto-generated method stub
		CommodityDataService commodityData = new CommodityDataImpl();
		ArrayList<CommodityVO> commodityVOs = new ArrayList<CommodityVO>();
		ArrayList<CommodityPO> commodityPOs = new ArrayList<CommodityPO>();
		for(CommodityPO c: commodityPOs){
			commodityVOs.add(new CommodityVO(c));
		}
		return commodityVOs;
	}

	public void renew() {
		// TODO Auto-generated method stub
		CommodityDataService commodityData = new CommodityDataImpl();
		try {
			commodityData.renew();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}