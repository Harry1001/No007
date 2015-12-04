package businessLogic.transportbl;

import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogicService.transportblservice.EntruckBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.EntruckReceiptVO;

public class EntruckController implements EntruckBLService{

	EntruckBL entruckbl=new EntruckBL();
	
	public boolean verify(EntruckReceiptVO vo) throws TransportBLException {
		return entruckbl.verify(vo);
	}

	public void submit(EntruckReceiptVO vo) throws RemoteException, SQLException {
		entruckbl.submit(vo);
	}

	public double calFee(EntruckReceiptVO vo) throws RemoteException, SQLException {
		return entruckbl.calFee(vo);
	}

}
