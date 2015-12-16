package businessLogic.transportbl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
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

	public void submit(EntruckReceiptVO vo) throws RemoteException, SQLException, MalformedURLException, NotBoundException {
		entruckbl.submit(vo);
	}

	public double calFee(EntruckReceiptVO vo) throws SQLException, NotBoundException, FileNotFoundException, ClassNotFoundException, IOException {
		return entruckbl.calFee(vo);
	}

}
