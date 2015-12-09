package businesslogic.commoditybl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import businessLogic.commoditybl.CommodityBL;
import typeDefinition.Location;
import vo.commodityvo.CommodityVO;
import vo.receiptvo.DepotInReceiptVO;

public class TestCommodityBL {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NamingException, NotBoundException, SQLException {
		
		CommodityBL commodityBL = new CommodityBL();
		Date inTime = new Date();
		Location storeloc = new Location("0250", 1, 6, 8, 25);
		DepotInReceiptVO vo = new DepotInReceiptVO("100000130", inTime, "南京大学", storeloc);
		ArrayList<CommodityVO> vos = commodityBL.getList("0250");
		for(CommodityVO commodityVO: vos) {
			System.out.println(commodityVO.getExpressNumber()+" "+commodityVO.getInTime()+" "+commodityVO.getDestination()+" "+commodityVO.getStoreloc().getTransferNum()+" "+commodityVO.getDestination()+" "+commodityVO.getStoreloc().getRowID());
		}
		commodityBL.submitIn(vo);		
	}
}
