package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.ChargeReceiptPO;

public class ChargeReceiptDBManager extends DBManager{

	public ArrayList<ChargeReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		//TODO
		Timestamp fromTimestamp = new Timestamp(fromtime.getTime());
		Timestamp toTimestamp = new Timestamp(toTime.getTime());
		String find = "SELECT * FROM Chargereceipt Where chargetime > '" + fromTimestamp.toString()
					+ "' AND chargetime < '" + toTimestamp.toString() + "' ORDER BY courier, orderID";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(find);
		ArrayList<ChargeReceiptPO> pos = new ArrayList<ChargeReceiptPO>();
		String formercourier = "#";
		ArrayList<String> orderIDs = null;
		ChargeReceiptPO po = null;
		while(resultSet.next()){
			String courier = resultSet.getString(3);
			if (formercourier.equals("#")){
				orderIDs = new ArrayList<String>();
				String orderID = resultSet.getString(4);
				orderIDs.add(orderID);

				Date chargeTime = new Date(resultSet.getTimestamp(1).getTime());
				double fee = resultSet.getDouble(2);
				po = new ChargeReceiptPO(chargeTime, fee, courier, null);
				formercourier=courier;
				continue;
			}
			if(courier.equals(formercourier)){
				String orderID = resultSet.getString(4);
				orderIDs.add(orderID);
			}
			else{
				po.setOrderIDs(orderIDs);
				pos.add(po);
				
				orderIDs = new ArrayList<String>();
				String orderID = resultSet.getString(4);
				orderIDs.add(orderID);
				
				Date chargeTime = new Date(resultSet.getTimestamp(1).getTime());
				double fee = resultSet.getDouble(2);
				po = new ChargeReceiptPO(chargeTime, fee, courier, null);
				formercourier=courier;
			}

		}
		if (po!=null){
			po.setOrderIDs(orderIDs);
			pos.add(po);
		}
		stopconnection(connection);
		return pos;
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
