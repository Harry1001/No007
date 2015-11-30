package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.ReceiveReceiptPO;

public class ReceiveReceiptDBManager extends DBManager{

	public ArrayList<ReceiveReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		ArrayList<ReceiveReceiptPO> po=new ArrayList<ReceiveReceiptPO>();
		Timestamp fTime = new Timestamp(fromtime.getTime());
		Timestamp tTime = new Timestamp(toTime.getTime());
		String receiveReceipt="SELECT * FROM ReceiveReceipt WHERE receiveTime BETWEEN "+fTime+" AND "+tTime;
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(receiveReceipt);
		while(resultSet.next()){
			String receiveNum=resultSet.getString(1);
			String receiver=resultSet.getString(2);
			Date receiveTime=new Date(resultSet.getDate(3).getTime());
			ReceiveReceiptPO temppo=new ReceiveReceiptPO(receiveNum,receiver,receiveTime);
			po.add(temppo);
		}
		return po;
	}
	
	public void addItem(ReceiveReceiptPO item) throws SQLException{
		String receiveNum=item.getReceiveNum();
		String receiver=item.getReceiver();
		Timestamp receiveTime=new Timestamp(item.getReceiveTime().getTime());
		String add="INSERT INTO ReceiveReceipt VALUES ('"+receiveNum+"', '"+receiver+"', "+receiveTime+")";
		Connection connection=connectToDB();
		Statement statement=connection.createStatement();
		statement.executeUpdate(add);
		stopconnection(connection);
	}
	
	public void deleteAll() throws SQLException{
		String delete="DELETE FROM ReceiveReceipt";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(delete);
		stopconnection(connection);
	}
	
}
