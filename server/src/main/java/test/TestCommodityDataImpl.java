package test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import data.CommodityDataImpl;
import po.commoditypo.CommodityPO;
import typeDefinition.Location;
import typeDefinition.myTime;

public class TestCommodityDataImpl {

	@Test
	public void test() {
		myTime inTime = new myTime(2015, 11, 20);
		Location location = new Location("0251", 1, 2, 5, 24);
		CommodityPO commodityPO = new CommodityPO("100000005", inTime, "江苏省南京市鼓楼区南京大学", location);
		CommodityDataImpl commodityDataImpl = new CommodityDataImpl();
		try {
			commodityDataImpl.delete("100000005");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, true);
	}

}
