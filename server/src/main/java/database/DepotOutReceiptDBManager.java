package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.DepotOutReceiptPO;
import typeDefinition.Vehicle;

public class DepotOutReceiptDBManager extends DBManager{

	public ArrayList<DepotOutReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		ArrayList<DepotOutReceiptPO> po=new ArrayList<DepotOutReceiptPO>();
		Timestamp fTime = new Timestamp(fromtime.getTime());
		Timestamp tTime = new Timestamp(toTime.getTime());
		String depotOutReceipt="SELECT * FROM DepotOutReceipt WHERE outTime BETWEEN '"+fTime+"' AND '"+tTime+"'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(depotOutReceipt);
		while(resultSet.next()){
			String packID=resultSet.getString(1);
			Date outTime=new Date(resultSet.getTimestamp(2).getTime());
			String destination=resultSet.getString(3);
			Vehicle vehicle=Vehicle.values()[resultSet.getInt(4)];
			String transID=resultSet.getString(5);
			DepotOutReceiptPO temppo=new DepotOutReceiptPO(packID,outTime,destination,vehicle,transID);
			po.add(temppo);
		}
		stopconnection(connection);
		return po;
	}
	
	public void addItem(DepotOutReceiptPO item) throws SQLException{
		String packID=item.getPackID();
		Timestamp outTime=new Timestamp(item.getOutTime().getTime());
		String destination=item.getDestination();
		int vehicle=item.getVehicle().ordinal();
		String transID=item.getTransID();
		String add="INSERT INTO DepotOutReceipt VALUES (?,?,?,?,?)";
		Connection connection=connectToDB();
		PreparedStatement statement = connection.prepareStatement(add);
		statement.setString(1, packID);
		statement.setTimestamp(2, outTime);
		statement.setString(3, destination);
		statement.setInt(4, vehicle);
		statement.setString(5, transID);
		statement.executeUpdate();
		stopconnection(connection);
	}
	
	public void deleteAll() throws SQLException{
		String delete="DELETE FROM DepotOutReceipt";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(delete);
		stopconnection(connection);
	}
	
}
