package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.StoreArrivalReceiptPO;
import typeDefinition.PackArrivalState;

public class StoreArrivalReceiptDBManager extends DBManager{

	public ArrayList<StoreArrivalReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		ArrayList<StoreArrivalReceiptPO> po=new ArrayList<StoreArrivalReceiptPO>();
		Timestamp fTime = new Timestamp(fromtime.getTime());
		Timestamp tTime = new Timestamp(toTime.getTime());
		String storeArrivalReceipt="SELECT * FROM StoreArrivalReceipt WHERE arriveTime BETWEEN "+fTime+" AND "+tTime;
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(storeArrivalReceipt);
		while(resultSet.next()){
			String orderID=resultSet.getString(1);
			Date arriveTime=new Date(resultSet.getDate(2).getTime());
			String transReceiptID=resultSet.getString(3);
			String fromPosition=resultSet.getString(4);
			PackArrivalState arriveState=PackArrivalState.values()[resultSet.getInt(5)];
			StoreArrivalReceiptPO temppo=new StoreArrivalReceiptPO(orderID,arriveTime,transReceiptID,fromPosition,arriveState);
			po.add(temppo);
		}
		return po;
	}
	
	public void addItem(StoreArrivalReceiptPO item) throws SQLException{
		String orderID=item.getOrderID();
		Timestamp arriveTime=new Timestamp(item.getArriveTime().getTime());
		String transReceiptID=item.getTransReceiptID();
		String fromPosition=item.getFromPosition();
		int arriveState=item.getArriveState().ordinal();
		String add="INSERT INTO StoreArrivalReceipt VALUES (?,?,?,?,?)";
		Connection connection=connectToDB();
		PreparedStatement statement = connection.prepareStatement(add);
		statement.setString(1, orderID);
		statement.setTimestamp(2, arriveTime);
		statement.setString(3, transReceiptID);
		statement.setString(4, fromPosition);
		statement.setInt(5, arriveState);
		statement.executeUpdate();
		stopconnection(connection);
	}
	
	public void deleteAll() throws SQLException{
		String delete="DELETE FROM StoreArrivalReceipt";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(delete);
		stopconnection(connection);
	}
	
}
