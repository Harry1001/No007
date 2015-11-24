package businessLogic.transportbl;

import java.rmi.RemoteException;

import businessLogicService.transportblservice.EntruckBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.EntruckReceiptVO;

public class EntruckController implements EntruckBLService{

	EntruckBL entruckbl=new EntruckBL();
	
	public boolean verify(EntruckReceiptVO vo) throws TransportBLException {
		return entruckbl.verify(vo);
	}

	public void submit(EntruckReceiptVO vo) throws RemoteException {
		entruckbl.submit(vo);
	}

	public double calFee(EntruckReceiptVO vo) {
		return entruckbl.calFee(vo);
	}

}