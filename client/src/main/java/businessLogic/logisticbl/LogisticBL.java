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
import vo.receiptvo.ReceiptVO;
import vo.receiptvo.ReceiveReceiptVO;
import vo.receiptvo.SendReceiptVO;

public class LogisticBL implements LogisticBLService{

	LogisticDataService logisticdata=new LogisticDataImpl();
	public ArrayList<LogisticVO> getLogistic(String orderID) throws RemoteException {
		// TODO
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
		myTime arrivaltime=new myTime();
		LogisticPO po;
		switch(type){
		case SEND:			
			logisticstate="收件";
			SendReceiptVO svo=(SendReceiptVO)vo;
			orderID=svo.getExpressNumber();
			po=new LogisticPO(orderID,arrivaltime,logisticstate);
			logisticdata.update(po);
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
			if(transportID.substring(4, 5)=="2"){
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
			else{
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
			ArrayList<String> all=evo.getOrderNum();
			for(String temp:all){
				po=new LogisticPO(temp,arrivaltime,logisticstate);
				logisticdata.update(po);
			}
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
