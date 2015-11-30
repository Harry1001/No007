package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.HubArrivalReceiptPO;
import typeDefinition.PackArrivalState;

public class HubArrivalReceiptDBManager extends DBManager{

	public ArrayList<HubArrivalReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		ArrayList<HubArrivalReceiptPO> po=new ArrayList<HubArrivalReceiptPO>();
		Timestamp fTime = new Timestamp(fromtime.getTime());
		Timestamp tTime = new Timestamp(toTime.getTime());
		String hubArrivalReceipt="SELECT * FROM HubArrivalReceipt WHERE time BETWEEN "+fTime+" AND "+tTime;
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(hubArrivalReceipt);
		while(resultSet.next()){
			String orderID=resultSet.getString(1);
			String hubID=resultSet.getString(2);
			Date arriveTime=new Date(resultSet.getDate(3).getTime());
			String transReceiptID=resultSet.getString(4);
			String fromPosition=resultSet.getString(5);
			PackArrivalState arriveState=PackArrivalState.values()[resultSet.getInt(6)];
			HubArrivalReceiptPO temppo=new HubArrivalReceiptPO(orderID,hubID,arriveTime,transReceiptID,fromPosition,arriveState);
			po.add(temppo);
		}
		return po;
	}
	
	public void addItem(HubArrivalReceiptPO item) throws SQLException{
		String orderID=item.getOrderID();
		String hubID=item.getHubID();
		Timestamp arriveTime=new Timestamp(item.getArriveTime().getTime());
		String transReceiptID=item.getTransReceiptID();
		String fromPosition=item.getFromPosition();
		int arriveState=item.getArriveState().ordinal();
		String add="INSERT INTO HubArrivalReceipt VALUES (?,?,?,?,?,?)";
		Connection connection=connectToDB();
		PreparedStatement statement = connection.prepareStatement(add);
		statement.setString(1, orderID);
		statement.setString(2, hubID);
		statement.setTimestamp(3, arriveTime);
		statement.setString(4, transReceiptID);
		statement.setString(5, fromPosition);
		statement.setInt(6, arriveState);
		statement.executeUpdate();
		stopconnection(connection);
	}
	
	public void deleteAll() throws SQLException{
		String delete="DELETE FROM HubArrivalReceipt";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(delete);
		stopconnection(connection);
	}
}
