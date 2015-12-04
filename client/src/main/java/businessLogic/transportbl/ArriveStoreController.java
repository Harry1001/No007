package businessLogic.transportbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogicService.transportblservice.ArriveStoreBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.StoreArrivalReceiptVO;

public class ArriveStoreController implements ArriveStoreBLService{

	ArriveStoreBL arrivestorebl=new ArriveStoreBL();
	
	public boolean verify(StoreArrivalReceiptVO vo) throws TransportBLException {
		return arrivestorebl.verify(vo);
	}

	public void submit(StoreArrivalReceiptVO vo) throws RemoteException, MalformedURLException, NotBoundException, SQLException {
		arrivestorebl.submit(vo);
	}

}
