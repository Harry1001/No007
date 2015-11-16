package businessLogic.commoditybl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businessLogic.receiptbl.ReceiptBL;
import businessLogicService.commodityblservice.CommodityBLService;
import data.CommodityDataImpl;
import dataService.CommodityDataService;
import po.commoditypo.CommodityPO;
import po.receiptpo.DepotInReceiptPO;
import typeDefinition.Location;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.commodityvo.CheckResultVO;
import vo.commodityvo.CommodityVO;
import vo.receiptvo.DepotInReceiptVO;
import vo.receiptvo.DepotOutReceiptVO;
import vo.receiptvo.ReceiptVO;

public class CommodityBL implements CommodityBLService{

	public void submit(ReceiptVO vo) {
		// TODO Auto-generated method stub
		ReceiptBL receipt = new ReceiptBL();
		receipt.createReceipt(vo);
	}

	public ArrayList<CommodityVO> getList(String transferNum) {
		// TODO Auto-generated method stub
		CommodityDataService commodityData = new CommodityDataImpl();
		ArrayList<CommodityVO> commodityVOs = new ArrayList<CommodityVO>();
		ArrayList<CommodityPO> commodityPOs = new ArrayList<CommodityPO>();
		try {
			commodityPOs = commodityData.check(transferNum);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(CommodityPO c:commodityPOs){
			commodityVOs.add(new CommodityVO(c));
		}
		return commodityVOs;
	}

	public CheckResultVO getList(String transferNum, myTime fromTime, myTime toTime) {
		// TODO Auto-generated method stub
		CheckResultVO result = new CheckResultVO();
		ReceiptBL receipt = new ReceiptBL();
		ArrayList<DepotInReceiptVO> depotInReceipts = (ArrayList<DepotInReceiptVO>)
												receipt.getListByTime(fromTime, toTime, ReceiptType.DEPOTIN);
		ArrayList<Location> locations = new ArrayList<Location>();
		for(DepotInReceiptVO depotinreceipt: depotInReceipts)
			if(depotinreceipt.getLocation().getTransferNum().equals(transferNum))
				locations.add(depotinreceipt.getLocation());
		result.setLocations(locations);
		
		int depotinnum = locations.size();
		result.setDepotinnum(depotinnum);
		
		ArrayList<DepotOutReceiptVO> depotOutReceipts = (ArrayList<DepotOutReceiptVO>)
												receipt.getListByTime(fromTime, toTime, ReceiptType.DEPOTOUT);
		int depotoutnum = 0;
		for(DepotOutReceiptVO depotOutReceipt: depotOutReceipts){
	//transID的前四位为中转中心编号
			String transID = depotOutReceipt.getTransID();
			String transNum = transID.substring(0, 3);
			if(transferNum.equals(transNum))	depotoutnum++;
		}
		result.setDepotoutnum(depotoutnum);
		return result;
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