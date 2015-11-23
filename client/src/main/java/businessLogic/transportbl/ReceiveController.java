package businessLogic.transportbl;

import java.rmi.RemoteException;

import businessLogicService.transportblservice.ReceiveBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.ReceiveReceiptVO;

public class ReceiveController implements ReceiveBLService{

	ReceiveBL receivebl=new ReceiveBL();
	
	public boolean verify(ReceiveReceiptVO vo) throws TransportBLException {
		return receivebl.verify(vo);
	}

	public void submit(ReceiveReceiptVO vo) throws RemoteException {
		receivebl.submit(vo);
	}

}
