package businessLogic.logisticbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;

import businessLogicService.logisticblservice.LogisticBLService;
import dataService.LogisticDataService;
import dataService._RMI;
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

	private LogisticDataService logisticdata;
	
	public LogisticBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_logistic";
		this.logisticdata=(LogisticDataService)Naming.lookup(url);
	}
	
	//TODO orderID not exist
	public ArrayList<LogisticVO> getLogistic(String orderID) throws RemoteException, SQLException {
		ArrayList<LogisticVO> vo=new ArrayList<LogisticVO>();
		ArrayList<LogisticPO> po=logisticdata.read(orderID);
		for(LogisticPO temppo:po){
			LogisticVO tempvo=new LogisticVO(temppo);
			vo.add(tempvo);
		}
		return vo;
	}
	
	public void update(String order,ReceiptVO vo) throws RemoteException, SQLException{
		ReceiptType type=vo.getType();
		String logisticstate=null;
		Date arrivaltime=new Date();
		LogisticPO po;
		switch(type){
		case SEND:			
			logisticstate="收件";
			SendReceiptVO svo=(SendReceiptVO)vo;
			po=new LogisticPO(order,arrivaltime,logisticstate);
			logisticdata.update(po);
			break;
		case STOREARRIVAL:
			StoreArrivalReceiptVO savo=(StoreArrivalReceiptVO)vo;
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
			}else if(cityID.equals("010")){
				city="北京市";
				if(storeID.equals("000")){
					store="朝阳营业厅";
				}else if(storeID.equals("001")){
					store="海淀营业厅";
				}else{
					store="丰台营业厅";
				}
			}else if(cityID.equals("021")){
				city="上海市";
				if(storeID.equals("000")){
					store="浦东营业厅";
				}else if(storeID.equals("001")){
					store="崇明营业厅";
				}else{
					store="宝山营业厅";
				}
			}else if(cityID.equals("020")){
				city="广州市";
				if(storeID.equals("000")){
					store="花都营业厅";
				}else if(storeID.equals("001")){
					store="增城营业厅";
				}else{
					store="从化营业厅";
				}
			}
			
			logisticstate="到达"+city+store;
			po=new LogisticPO(order,arrivaltime,logisticstate);
			logisticdata.update(po);
			break;
		case HUBARRIVAL:
			HubArrivalReceiptVO havo=(HubArrivalReceiptVO)vo;
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
			po=new LogisticPO(order,arrivaltime,logisticstate);
			logisticdata.update(po);
			break;
		case DESPATCH:
			logisticstate="派件中";
			DespatchReceiptVO dvo=(DespatchReceiptVO)vo;
			po=new LogisticPO(order,arrivaltime,logisticstate);
			logisticdata.update(po);
			break;
		case RECEIVE:
			ReceiveReceiptVO rvo=(ReceiveReceiptVO)vo;
			remove(order);
			break;
		}

	}
	
	public void remove(String num) throws RemoteException, SQLException{
		logisticdata.remove(num);
	}
	
}
