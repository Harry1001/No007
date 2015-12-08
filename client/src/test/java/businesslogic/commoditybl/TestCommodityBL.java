package businesslogic.commoditybl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.NamingException;

import businessLogic.commoditybl.CommodityBL;
import typeDefinition.Location;
import vo.receiptvo.DepotInReceiptVO;

public class TestCommodityBL {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NamingException, NotBoundException, SQLException {
		
		CommodityBL commodityBL = new CommodityBL();
		Date inTime = new Date();
		Location storeloc = new Location("0250", 1, 6, 8, 25);
		DepotInReceiptVO vo = new DepotInReceiptVO("100000125", inTime, "Nanjing University", storeloc);
		commodityBL.submitIn(vo);		
	}
}
