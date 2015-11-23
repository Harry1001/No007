package businessLogic.transportbl;

import java.rmi.RemoteException;

import businessLogicService.transportblservice.TransferBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.TransferReceiptVO;

public class TransferController implements TransferBLService{

	TransferBL transferbl=new TransferBL();
	public boolean verify(TransferReceiptVO vo) throws TransportBLException {
		return transferbl.verify(vo);
	}

	public void submit(TransferReceiptVO vo) throws RemoteException {
		transferbl.submit(vo);
	}

	public double calFee(TransferReceiptVO vo) {
		return transferbl.calFee(vo);
	}

}
