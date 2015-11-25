package businessLogic.logisticbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import businessLogicService.logisticblservice.LogisticBLService;
import data.LogisticDataImpl;
import dataService.LogisticDataService;
import po.logisticpo.LogisticPO;
import typeDefinition.ReceiptType;
import vo.logisticvo.LogisticVO;
import vo.receiptvo.DespatchReceiptVO;
import vo.receiptvo.HubArrivalReceiptVO;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.ReceiveReceiptVO;
import vo.receiptvo.SendReceiptVO;
import vo.receiptvo.StoreArrivalReceiptVO;

public class LogisticBL implements LogisticBLService{

	LogisticDataService logisticdata=new LogisticDataImpl();
	public ArrayList<LogisticVO> getLogistic(String orderID) throws RemoteException {
		ArrayList<LogisticVO> vo=new ArrayList<LogisticVO>();
		ArrayList<LogisticPO> po=logisticdata.read(orderID);
		for(LogisticPO temppo:po){
			LogisticVO tempvo=new LogisticVO(temppo);
			vo.add(tempvo);
		}
		return vo;
	}
	
	public void update(ReceiptVO vo) throws RemoteException{
		ReceiptType type=vo.getType();
		String orderID=null;
		String logisticstate=null;
		Date arrivaltime=new Date();
		LogisticPO po;
		switch(type){
		case SEND:			
			logisticstate="收件";
			SendReceiptVO svo=(SendReceiptVO)vo;
			orderID=svo.getExpressNumber();
			po=new LogisticPO(orderID,arrivaltime,logisticstate);
			logisticdata.update(po);
			break;
		case STOREARRIVAL:
			StoreArrivalReceiptVO savo=(StoreArrivalReceiptVO)vo;
			orderID=savo.getOrderID();
			arrivaltime=savo.getArriveTime();
			String transID=savo.getTransReceiptID();
			String cityID=transID.substring(3);
			String city = null;
			String storeID=transID.substring(3, 6);
			String store = null;
			if(cityID.equals("025")){
				city="南京市";
				if(storeID.equals("000")){
					store="鼓楼营业厅";
				}else if(storeID.equals("001")){
					store="栖霞营业厅";
				}else{
					store="浦口营业厅";
				}
			}else if(cityID.equals("001")){
				city="北京市";
				if(storeID.equals("000")){
					store="**营业厅";
				}else if(storeID.equals("001")){
					store="**营业厅";
				}else{
					store="**营业厅";
				}
			}else{
				
			}
			logisticstate="到达"+city+store;
			po=new LogisticPO(orderID,arrivaltime,logisticstate);
			logisticdata.update(po);
			break;
		case HUBARRIVAL:
			HubArrivalReceiptVO havo=(HubArrivalReceiptVO)vo;
			orderID=havo.getOrderID();
			arrivaltime=havo.getArriveTime();
			String transID1=havo.getTransReceiptID();
			String cityID1=transID1.substring(3);
			String city1 = null;
			String hubID=transID1.substring(3, 4);
			String hub = null;
			if(cityID1.equals("025")){
				city1="南京市";
				if(hubID.equals("0")){
					hub="鼓楼中转中心";
				}else if(hubID.equals("1")){
					hub="栖霞中转中心";
				}else{
					hub="浦口中转中心";
				}
			}else if(cityID1.equals("001")){
				city1="北京市";
				if(hubID.equals("0")){
					hub="**中转中心";
				}else if(hubID.equals("1")){
					hub="**中转中心";
				}else{
					hub="**中转中心";
				}
			}else{
				
			}			
			logisticstate="到达"+city1+hub;
			po=new LogisticPO(orderID,arrivaltime,logisticstate);
			logisticdata.update(po);
			break;
		case DESPATCH:
			logisticstate="派件中";
			DespatchReceiptVO dvo=(DespatchReceiptVO)vo;
			orderID=dvo.getOrderNum();
			po=new LogisticPO(orderID,arrivaltime,logisticstate);
			logisticdata.update(po);
			break;
		case RECEIVE:
			ReceiveReceiptVO rvo=(ReceiveReceiptVO)vo;
			orderID=rvo.getReceiveNum();
			remove(orderID);
			break;
		}

	}
	
	public void remove(String num) throws RemoteException{
		logisticdata.remove(num);
	}
	
}
