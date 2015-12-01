package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.ChargeReceiptPO;

public class ChargeReceiptDBManager extends DBManager{

	public ArrayList<ChargeReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		//TODO
		return null;		
	}
	
	public void addItem(ChargeReceiptPO item) throws SQLException{
		Timestamp chargeTime=new Timestamp(item.getChargeTime().getTime());
		double fee=item.getFee();
		String courier=item.getCourier();
		ArrayList<String> orderIDs=item.getOrderIDs();
		String add="INSERT INTO ChargeReceipt VALUES (?,?,?,?)";
		Connection connection=connectToDB();
		PreparedStatement statement = connection.prepareStatement(add);
		for(String orderID:orderIDs){
			statement.setTimestamp(1, chargeTime);
			statement.setDouble(2, fee);
			statement.setString(3, courier);
			statement.setString(4, orderID);
			statement.executeUpdate();
		}
		stopconnection(connection);
	}
	
	public void deleteAll() throws SQLException{
		String delete="DELETE FROM ChargeReceipt";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(delete);
		stopconnection(connection);
	}
	
}
