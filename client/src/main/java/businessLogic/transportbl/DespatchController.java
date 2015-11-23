package businessLogic.transportbl;

import java.rmi.RemoteException;

import businessLogicService.transportblservice.DespatchBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.DespatchReceiptVO;

public class DespatchController implements DespatchBLService{

	DespatchBL despatchbl=new DespatchBL();
	public boolean verify(DespatchReceiptVO vo) throws TransportBLException {
		return despatchbl.verify(vo);
	}

	public void submit(DespatchReceiptVO vo) throws RemoteException {
		despatchbl.submit(vo);
	}

}
