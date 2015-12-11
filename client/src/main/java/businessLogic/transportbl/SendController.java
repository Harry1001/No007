package businessLogic.transportbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import businessLogicService.transportblservice.SendBLService;
import myexceptions.TransportBLException;
import vo.receiptvo.SendReceiptVO;

public class SendController implements SendBLService{

	SendBL sendbl=new SendBL();
	
	public boolean verify(SendReceiptVO vo) throws TransportBLException {
		return sendbl.verify(vo);
	}

	public void submit(SendReceiptVO vo) throws RemoteException, MalformedURLException, NotBoundException, SQLException {
		sendbl.submit(vo);
	}

	public double calFee(SendReceiptVO vo) throws RemoteException, SQLException, MalformedURLException, NotBoundException {
		return sendbl.calFee(vo);
	}

	public SendReceiptVO getSendReceipt(String orderID) throws RemoteException, SQLException {
		return sendbl.getSendReceipt(orderID);
	}
}
