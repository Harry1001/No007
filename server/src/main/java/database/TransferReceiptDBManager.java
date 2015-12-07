package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.TransferReceiptPO;
import typeDefinition.Vehicle;

public class TransferReceiptDBManager extends DBManager{

	public ArrayList<TransferReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		//TODO 查询一段时间内的中转单信息
		Timestamp fromTimestamp = new Timestamp(fromtime.getTime());
		Timestamp toTimestamp = new Timestamp(toTime.getTime());
		String find = "SELECT * FROM Transferreceipt WHERE transferdate > " + fromTimestamp.toString()
				+ " AND transferDate < " + toTimestamp.toString() + " ORDER BY transferID, orderID";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(find);
		ArrayList<TransferReceiptPO> pos = new ArrayList<TransferReceiptPO>();
		String formertransferID = "";
		ArrayList<String> orderIDs = null;
		TransferReceiptPO po = null;
		while(resultSet.next()){
			String transferID = resultSet.getString(3);
			if(transferID.equals(formertransferID)){
				String orderID = resultSet.getString(8);
				orderIDs.add(orderID);
			}
			else{
				po.setOrderID(orderIDs);
				pos.add(po);
				
				orderIDs = new ArrayList<String>();
				String orderID = resultSet.getString(8);
				orderIDs.add(orderID);
				
				Vehicle transferType = Vehicle.values()[resultSet.getInt(1)];
				Date transferDate = new Date(resultSet.getTimestamp(2).getTime());
				String vehicleID = resultSet.getString(4);
				String departLoc = resultSet.getString(5);
				String arriveLoc = resultSet.getString(6);
				int counterID = resultSet.getInt(7);
				double transferFee = resultSet.getDouble(9);
				po = new TransferReceiptPO(transferType, transferDate, 
						transferID, vehicleID, departLoc, arriveLoc, counterID, null, transferFee);
			}
		}
		stopconnection(connection);
		return pos;		
	}
	
	public void addItem(TransferReceiptPO item) throws SQLException{
		int transferType=item.getTransferType().ordinal();
		Timestamp transferDate=new Timestamp(item.getTransferDate().getTime());
		String transferID=item.getTransferID();
		String vehicleID=item.getVehicleID();
		String departLoc=item.getDepartLoc();
		String arriveLoc=item.getArriveLoc();
		int counterID=item.getCounterID();
		ArrayList<String> order=item.getOrderID();
		double transferFee=item.getTransferFee();
		String add="INSERT INTO TransferReceipt VALUES (?,?,?,?,?,?,?,?,?)";
		Connection connection=connectToDB();
		PreparedStatement statement = connection.prepareStatement(add);
		for(String orderID:order){
			statement.setInt(1, transferType);
			statement.setTimestamp(2, transferDate);
			statement.setString(3, transferID);
			statement.setString(4, vehicleID);
			statement.setString(5, departLoc);
			statement.setString(6, arriveLoc);
			statement.setInt(7, counterID);
			statement.setString(8, orderID);
			statement.setDouble(9, transferFee);
			statement.executeUpdate();
		}
		stopconnection(connection);
	}
	
	public void deleteAll() throws SQLException{
		String delete="DELETE FROM TransferReceipt";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(delete);
		stopconnection(connection);
	}
	
}
