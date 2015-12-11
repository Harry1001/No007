package businessLogic.logisticbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;

import businessLogicService.infoblservice.AgencyBLService;
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
	private AgencyBLService agencyBLService;
	
	public LogisticBL() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+_RMI.getIP()+"/central_logistic";
		this.logisticdata=(LogisticDataService)Naming.lookup(url);
	}
	
	public ArrayList<LogisticVO> getLogistic(String orderID) throws RemoteException, SQLException {
		ArrayList<LogisticVO> vo=new ArrayList<LogisticVO>();
		ArrayList<LogisticPO> po=logisticdata.read(orderID);
		for(LogisticPO temppo:po){
			LogisticVO tempvo=new LogisticVO(temppo);
			vo.add(tempvo);
		}
		return vo;
	}
	
	public void update(String userID,ReceiptVO vo) throws RemoteException, SQLException{
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
			logisticstate="到达"+agencyBLService.getAgengcy(userID);
			po=new LogisticPO(orderID,arrivaltime,logisticstate);
			logisticdata.update(po);
			break;
		case HUBARRIVAL:
			HubArrivalReceiptVO havo=(HubArrivalReceiptVO)vo;
			orderID=havo.getOrderID();
			arrivaltime=havo.getArriveTime();
			logisticstate="到达"+agencyBLService.getAgengcy(userID);
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
	
	public void remove(String num) throws RemoteException, SQLException{
		logisticdata.remove(num);
	}
	
}
