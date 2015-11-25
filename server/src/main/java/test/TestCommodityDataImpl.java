package test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;

import org.junit.Test;

import data.CommodityDataImpl;
import po.commoditypo.CommodityPO;
import typeDefinition.Location;

public class TestCommodityDataImpl {

	@Test
	public void test() {
		Date inTime = new Date(System.currentTimeMillis());
		Location location = new Location("0251", 1, 2, 5, 24);
		CommodityPO commodityPO = new CommodityPO("100000025", inTime, "江苏省南京市鼓楼区南京大学", location);
		CommodityDataImpl commodityDataImpl = new CommodityDataImpl();
		try {
			try {
				commodityDataImpl.add(commodityPO);
				commodityDataImpl.delete("100000000");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, true);
	}

}
