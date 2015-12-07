package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.EntruckReceiptPO;

public class EntruckReceiptDBManager extends DBManager{

	public ArrayList<EntruckReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		Timestamp fromTimestamp = new Timestamp(fromtime.getTime());
		Timestamp toTimestamp = new Timestamp(toTime.getTime());
		String find = "SELECT * FROM Entruckreceipt WHERE entruckDate > " + fromTimestamp.toString()
					+ " AND entruckDate < " + toTimestamp.toString() + " ORDER BY transportID, orderNum";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(find);
		ArrayList<EntruckReceiptPO> pos = new ArrayList<EntruckReceiptPO>();
		String transportID = "";//存储上一个货运编号
		ArrayList<String> orderNums = null;//存储上一个货运编号对应的许多快递单号
		EntruckReceiptPO po = null;
		while(resultSet.next()){
			String presentID = resultSet.getString(2);
			if(presentID.equals(transportID)){//如果货运编号相同，则增加快递单号
				String orderNum = resultSet.getString(5);
				orderNums.add(orderNum);
			}
			else{//如果货运编号不同，则增加一条装车单，并对下一条装车单初始化
				po.setOrderNum(orderNums);
				pos.add(po);
				
				orderNums = new ArrayList<String>();
				String orderNum = resultSet.getString(5);
				orderNums.add(orderNum);
				
				Date entruckDate = new Date(resultSet.getTimestamp(1).getTime());
				String arriveLoc = resultSet.getString(3);
				String truckID = resultSet.getString(4);
				double transportFee = resultSet.getDouble(6);
				po = new EntruckReceiptPO(entruckDate, presentID, arriveLoc, truckID, null, transportFee);				
			}
		}
		stopconnection(connection);
		return pos;		
	}
	
	public void addItem(EntruckReceiptPO item) throws SQLException{
		Timestamp entruckDate=new Timestamp(item.getEntruckDate().getTime());
		String transportID=item.getTransportID();
		String arriveLoc=item.getArriveLoc();
		String truckID=item.getTruckID();
		ArrayList<String> order=item.getOrderNum();
		double transportFee=item.getTransportFee();
		String add="INSERT INTO EntruckReceipt VALUES (?,?,?,?,?,?)";
		Connection connection=connectToDB();
		PreparedStatement statement = connection.prepareStatement(add);
		for(String orderNum:order){
			statement.setTimestamp(1, entruckDate);
			statement.setString(2, transportID);
			statement.setString(3, arriveLoc);
			statement.setString(4, truckID);
			statement.setString(5, orderNum);
			statement.setDouble(6, transportFee);
			statement.executeUpdate();
		}
		stopconnection(connection);
	}
	
	public void deleteAll() throws SQLException{
		String delete="DELETE FROM EntruckReceipt";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(delete);
		stopconnection(connection);
	}
	
}
