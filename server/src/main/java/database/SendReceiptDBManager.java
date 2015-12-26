package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.SendReceiptPO;

public class SendReceiptDBManager extends DBManager{

	public ArrayList<SendReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		ArrayList<SendReceiptPO> po=new ArrayList<SendReceiptPO>();
		Timestamp fTime = new Timestamp(fromtime.getTime());
		Timestamp tTime = new Timestamp(toTime.getTime());
		String sendReceipt="SELECT * FROM SendReceipt WHERE time BETWEEN "+fTime+" AND "+tTime;
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sendReceipt);
		while(resultSet.next()){
			String senderName=resultSet.getString(1);
			String senderLoc=resultSet.getString(2);
			String senderUnit=resultSet.getString(3);
			String senderPhone=resultSet.getString(4);
			String receiverName=resultSet.getString(5);
			String receiverLoc=resultSet.getString(6);
			String receiverUnit=resultSet.getString(7);
			String receiverPhone=resultSet.getString(8);
			int number=resultSet.getInt(9);
			double weight=resultSet.getDouble(10);
			double volume=resultSet.getDouble(11);
			String name=resultSet.getString(12);
			String expressType=resultSet.getString(13);
			String pack=resultSet.getString(14);
			String expressNumber=resultSet.getString(15);
			double money=resultSet.getDouble(16);
			Date time=new Date(resultSet.getTimestamp(17).getTime());
			SendReceiptPO temppo=new SendReceiptPO(senderName,senderLoc,senderUnit,senderPhone,receiverName,receiverLoc,
					receiverUnit,receiverPhone,number,weight,volume,name,expressType,pack,expressNumber,money,time);
			po.add(temppo);
		}
		stopconnection(connection);
		return po;
	}
	
	public void addItem(SendReceiptPO item) throws SQLException{
		String senderName=item.getSenderName();
		String senderLoc=item.getSenderLoc();
		String senderUnit=item.getSenderUnit();
		String senderPhone=item.getSenderPhone();
		String receiverName=item.getReceiverName();
		String receiverLoc=item.getReceiverLoc();
		String receiverUnit=item.getReceiverUnit();
		String receiverPhone=item.getReceiverPhone();
		int number=item.getNumber();
		double weight=item.getWeight();
		double volume=item.getVolume();
		String name=item.getName();
		String expressType=item.getExpressType();
		String pack=item.getPack();
		String expressNumber=item.getExpressNumber();
		double money=item.getMoney();
		Timestamp time=new Timestamp(item.getTime().getTime());
		String add="INSERT INTO SendReceipt VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection=connectToDB();
		PreparedStatement statement = connection.prepareStatement(add);
		statement.setString(1, senderName);
		statement.setString(2, senderLoc);
		statement.setString(3, senderUnit);
		statement.setString(4, senderPhone);
		statement.setString(5, receiverName);
		statement.setString(6, receiverLoc);
		statement.setString(7, receiverUnit);
		statement.setString(8, receiverPhone);
		statement.setInt(9, number);
		statement.setDouble(10, weight);
		statement.setDouble(11, volume);
		statement.setString(12, name);
		statement.setString(13, expressType);
		statement.setString(14, pack);
		statement.setString(15, expressNumber);
		statement.setDouble(16, money);
		statement.setTimestamp(17, time);
		statement.executeUpdate();
		stopconnection(connection);
	}
	
	public void deleteAll() throws SQLException{
		String delete="DELETE FROM SendReceipt";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(delete);
		stopconnection(connection);
	}

	public SendReceiptPO getItem(String orderID) throws SQLException {
		String sendReceipt="SELECT * FROM SendReceipt WHERE expressNum = '" + orderID + "'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sendReceipt);
		if (resultSet.next()){
			String senderName=resultSet.getString(1);
			String senderLoc=resultSet.getString(2);
			String senderUnit=resultSet.getString(3);
			String senderPhone=resultSet.getString(4);
			String receiverName=resultSet.getString(5);
			String receiverLoc=resultSet.getString(6);
			String receiverUnit=resultSet.getString(7);
			String receiverPhone=resultSet.getString(8);
			int number=resultSet.getInt(9);
			double weight=resultSet.getDouble(10);
			double volume=resultSet.getDouble(11);
			String name=resultSet.getString(12);
			String expressType=resultSet.getString(13);
			String pack=resultSet.getString(14);
			String expressNumber=resultSet.getString(15);
			double money=resultSet.getDouble(16);
			Date time=new Date(resultSet.getTimestamp(17).getTime());
			SendReceiptPO po=new SendReceiptPO(senderName,senderLoc,senderUnit,senderPhone,receiverName,receiverLoc,
					receiverUnit,receiverPhone,number,weight,volume,name,expressType,pack,expressNumber,money,time);
			stopconnection(connection);
			return po;
		}
		else {
			stopconnection(connection);
			return null;
		}
	}
	
}
