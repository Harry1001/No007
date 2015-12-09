package test;

import java.sql.SQLException;
import java.util.Date;

import database.CommodityDBManager;
import po.commoditypo.CommodityPO;
import typeDefinition.Location;

public class RMITest {

	public static void main(String[] args) throws SQLException{
		CommodityDBManager cdb=new CommodityDBManager();
		Date inTime=new Date();
		Location storeloc = new Location("0250",1,6,6,25);
		CommodityPO po=new CommodityPO("10001006", inTime, "江苏省南京市", storeloc);
		cdb.addCommodity(po);
	}
}
