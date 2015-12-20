package businessLogic.transportbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogicService.transportblservice.ArriveHubBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.HubArrivalReceiptVO;

public class ArriveHubController implements ArriveHubBLService{
	
	ArriveHubBL arrivehubbl=new ArriveHubBL();
	
	public boolean verify(HubArrivalReceiptVO vo) throws TransportBLException {		
		return arrivehubbl.verify(vo);
	}

	public void submit(HubArrivalReceiptVO vo) throws RemoteException, MalformedURLException, NotBoundException, SQLException, TransportBLException {
		arrivehubbl.submit(vo);
	}

}
