package businessLogic.commoditybl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.NamingException;

import businessLogic.receiptbl.ReceiptBL;
import businessLogicService.commodityblservice.CommodityBLService;
import dataService.CommodityDataService;
import po.commoditypo.CommodityPO;
import typeDefinition.Location;
import vo.commodityvo.CheckResultVO;
import vo.commodityvo.CommodityVO;
import vo.receiptvo.DepotInReceiptVO;
import vo.receiptvo.DepotOutReceiptVO;

public class CommodityBL implements CommodityBLService{

	private CommodityDataService commodityData;
	
	/**
	 * 连接到服务器端
	 * @throws NamingException
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public CommodityBL() throws NamingException, MalformedURLException, RemoteException, NotBoundException{
		String url = "rmi://114.212.42.182:8888/central_commodity";
		this.commodityData = (CommodityDataService) Naming.lookup(url);
	}
	
	/**
	 * 输入入库单单据的时候创建单据
	 * @param vo
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws SQLException 
	 */
	public void submitIn(DepotInReceiptVO vo) throws RemoteException, SQLException{
		ReceiptBL receipt = new ReceiptBL();
		receipt.createReceipt(vo);
		String expressNumber = vo.getPackID();
		Date inTime = new Date();
		String destination = vo.getDestination();
		Location location = vo.getLocation();
		CommodityPO commodityPO = new CommodityPO(expressNumber, inTime, destination, location);
		commodityData.add(commodityPO);		
	}
	
	/**
	 * 输入出库单单据的时候创建单据
	 * @param vo
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws SQLException 
	 */
	public void submitOut(DepotOutReceiptVO vo) throws RemoteException, SQLException{
		ReceiptBL receipt = new ReceiptBL();
		receipt.createReceipt(vo);
		String expressNumber = vo.getPackID();
		commodityData.delete(expressNumber);
	}

	/**
	 * 获取指定中转中心的所有现存的数据
	 * @param transferNum
	 * @return
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws SQLException 
	 */
	public ArrayList<CommodityVO> getList(String transferNum) throws SQLException, RemoteException {
		ArrayList<CommodityVO> commodityVOs = new ArrayList<CommodityVO>();
		ArrayList<CommodityPO> commodityPOs = commodityData.check(transferNum);
		for(CommodityPO c:commodityPOs){
			commodityVOs.add(new CommodityVO(c));
		}
		return commodityVOs;
	}

	/**
	 * 获取指定中转中心在指定时间内的所有出入库单据
	 * @param transferNum
	 * @param fromTime
	 * @param toTime
	 * @return
	 * @throws RemoteException
	 * @throws NamingException
	 */
	public CheckResultVO getList(String transferNum, Date fromTime, Date toTime) throws RemoteException{
//		CheckResultVO result = new CheckResultVO();
//		ReceiptBL receipt = new ReceiptBL();
//		ArrayList<DepotInReceiptVO> depotInReceipts = (ArrayList<DepotInReceiptVO>)
//												receipt.getListByTime(fromTime, toTime, ReceiptType.DEPOTIN);
//		ArrayList<Location> locations = new ArrayList<Location>();
//		for(DepotInReceiptVO depotinreceipt: depotInReceipts)
//			if(depotinreceipt.getLocation().getTransferNum().equals(transferNum))
//				locations.add(depotinreceipt.getLocation());
//		result.setLocations(locations);
//		
//		int depotinnum = locations.size();
//		result.setDepotinnum(depotinnum);
//		
//		ArrayList<DepotOutReceiptVO> depotOutReceipts = (ArrayList<DepotOutReceiptVO>)
//												receipt.getListByTime(fromTime, toTime, ReceiptType.DEPOTOUT);
//		int depotoutnum = 0;
//		for(DepotOutReceiptVO depotOutReceipt: depotOutReceipts){
//	//transID的前四位为中转中心编号
//			String transID = depotOutReceipt.getTransID();
//			String transNum = transID.substring(0, 4);
//			if(transferNum.equals(transNum))	depotoutnum++;
//		}
//		result.setDepotoutnum(depotoutnum);
		return null;
	}

	/**
	 * 获取所有中转中心当前的库存数据
	 * @return
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws SQLException 
	 */
	public ArrayList<CommodityVO> getTotal() throws RemoteException, SQLException {
		ArrayList<CommodityVO> commodityVOs = new ArrayList<CommodityVO>();
		ArrayList<CommodityPO> commodityPOs = commodityData.getAll();
		for(CommodityPO c: commodityPOs){
			commodityVOs.add(new CommodityVO(c));
		}
		return commodityVOs;
	}

	/**
	 * 清除指定中转中心当前的库存数据
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws SQLException 
	 */
	public void renew(String transferNum) throws RemoteException, SQLException {
		commodityData.renew(transferNum);
	}

}