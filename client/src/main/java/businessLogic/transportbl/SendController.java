package businessLogic.transportbl;

import java.rmi.RemoteException;

import businessLogicService.transportblservice.SendBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.SendReceiptVO;

public class SendController implements SendBLService{

	SendBL sendbl=new SendBL();
	
	public boolean verify(SendReceiptVO vo) throws TransportBLException {
		return sendbl.verify(vo);
	}

	public void submit(SendReceiptVO vo) throws RemoteException {
		sendbl.submit(vo);
	}

	public double calFee(SendReceiptVO vo) {
		return sendbl.calFee(vo);
	}

}
