package businessLogic.logisticbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businessLogicService.logisticblservice.LogisticBLService;
import data.LogisticDataImpl;
import dataService.LogisticDataService;
import po.logisticpo.LogisticPO;
import typeDefinition.ReceiptType;
import typeDefinition.myTime;
import vo.logisticvo.LogisticVO;
import vo.receiptvo.DespatchReceiptVO;
import vo.receiptvo.EntruckReceiptVO;
import vo.receiptvo.HubArrivalReceiptVO;
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.ReceiveReceiptVO;
import vo.receiptvo.SendReceiptVO;
import vo.receiptvo.StoreArrivalReceiptVO;

public class LogisticBL implements LogisticBLService{

	LogisticDataService logisticdata=new LogisticDataImpl();
	public LogisticVO getLogistic(String orderID) {
		// TODO Auto-generated method stub
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
	
//待修改！！！
	public void update(ReceiptVO vo){
		ReceiptType type=vo.getType();
		String orderID=null;
		String logisticstate=null;
		myTime arrivaltime=new myTime();
		ArrayList<String> history=new ArrayList<String>();
		LogisticPO po;
		switch(type){
		case SEND:			
			logisticstate="收件";
			SendReceiptVO svo=(SendReceiptVO)vo;
			orderID=svo.getExpressNumber();
			history.add(arrivaltime+" "+logisticstate);
			po=new LogisticPO(orderID,history,logisticstate);
			try {
				logisticdata.update(po);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case ENTRUCK:
			EntruckReceiptVO evo=(EntruckReceiptVO)vo;
			String transportID=evo.getTransportID();
			String cityID=transportID.substring(3);
			String city = null;
			if(cityID.equals("025")){
				city="南京市";
			}else if(cityID.equals("001")){
				city="北京市";
			}else{
				
			}
			if(transportID.substring(4, 5)=="0"){
				String storeID=transportID.substring(3, 6);
				String store = null;
				if(storeID.equals("000")){
					store="鼓楼营业厅";
				}else if(storeID.equals("001")){
					store="栖霞营业厅";
				}else{
					store="浦口营业厅";
				}
				logisticstate="到达"+city+store;
			}
			else{
				String hubID=transportID.substring(3, 4);
				String hub = null;
				if(hubID.equals("0")){
					hub="鼓楼中转中心";
				}else if(hubID.equals("1")){
					hub="栖霞中转中心";
				}else{
					hub="浦口中转中心";
				}
				logisticstate="到达"+city+hub;
			}
			ArrayList<String> all=evo.getOrderNum();
			LogisticPO po1 = null;
			for(String temp:all){
				try {
					po1=logisticdata.read(temp);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(po1!=null){
					history=po1.getHistory();
					history.add(arrivaltime+" "+logisticstate);
					po=new LogisticPO(temp,history,logisticstate);
					try {
						logisticdata.update(po);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			break;
		case DESPATCH:
			logisticstate="派件中";
			DespatchReceiptVO dvo=(DespatchReceiptVO)vo;
			orderID=dvo.getOrderNum();
			LogisticPO po2=null;
			try {
				po2=logisticdata.read(orderID);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(po2!=null){
				history=po2.getHistory();
				history.add(arrivaltime+" "+logisticstate);
				po=new LogisticPO(orderID,history,logisticstate);
				try {
					logisticdata.update(po);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		case RECEIVE:
			ReceiveReceiptVO rvo=(ReceiveReceiptVO)vo;
			orderID=rvo.getReceiveNum();
			remove(orderID);
			break;
		}

	}
	
	public void remove(String num){
		try {
			logisticdata.remove(num);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
