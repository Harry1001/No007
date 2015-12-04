package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.EntruckReceiptPO;

public class EntruckReceiptDBManager extends DBManager{

	public ArrayList<EntruckReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		//TODO 查询一段时间内的装车单信息
		return null;		
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
