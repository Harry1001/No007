package businessLogic.logisticbl;

import java.rmi.RemoteException;

import businessLogicService.logisticblservice.LogisticBLService;
import data.LogisticDataImpl;
import dataService.LogisticDataService;
import po.logisticpo.LogisticPO;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.logisticvo.LogisticVO;
import vo.receiptvo.HubArrivalReceiptVO;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.StoreArrivalReceiptVO;

public class LogisticBL implements LogisticBLService{

	public LogisticVO getLogistic(String orderID) {
		// TODO Auto-generated method stub
		LogisticDataService logisticdata=new LogisticDataImpl();
		LogisticPO po=null;
		try {
			po=logisticdata.read(orderID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LogisticVO vo=new LogisticVO(po);
		return vo;
	}

	public void update(ReceiptVO vo){
		ReceiptType type=vo.getType();
		String logisticstate=null;
		myTime arrivaltime=new myTime();
		switch(type){
		case SEND:			
			logisticstate="收件";
			break;
		case STOREARRIVAL:
			StoreArrivalReceiptVO savo=(StoreArrivalReceiptVO)vo;
			String transreceiptID1=savo.getTransReceiptID();
			String cityID1=transreceiptID1.substring(3);
			String city1 = null;
			String storeID=transreceiptID1.substring(3, 6);
			String store = null;
			if(cityID1.equals("025")){
				city1="南京市";
			}else if(cityID1.equals("001")){
				
			}else{
				
			}
			if(storeID.equals("000")){
				store="鼓楼营业厅";
			}else if(storeID.equals("001")){
				
			}else{
				
			}
			logisticstate="到达"+city1+store;
			break;
		case HUBARRIVAL:
			HubArrivalReceiptVO havo=(HubArrivalReceiptVO)vo;
			String transreceiptID2=havo.getTransReceiptID();
			String cityID2=transreceiptID2.substring(3);
			String city2 = null;
			String hubID=transreceiptID2.substring(3, 4);
			String hub = null;
			if(cityID2.equals("025")){
				city2="南京市";
			}else if(cityID2.equals("001")){
				
			}else{
				
			}
			if(hubID.equals("0")){
				hub="鼓楼中转中心";
			}else if(hubID.equals("1")){
				
			}else{
				
			}
			logisticstate="到达"+city2+hub;
		case DESPATCH:
			logisticstate="派件中";
			break;
		case RECEIVE:
			//remove();
			break;
		}

	}
	
	public void remove(String num){
		LogisticDataService logisticdata=new LogisticDataImpl();
		try {
			logisticdata.remove(num);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
