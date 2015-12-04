package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.DepotInReceiptPO;
import typeDefinition.Location;

public class DepotInReceiptDBManager extends DBManager{

	public ArrayList<DepotInReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		ArrayList<DepotInReceiptPO> po=new ArrayList<DepotInReceiptPO>();
		Timestamp fTime = new Timestamp(fromtime.getTime());
		Timestamp tTime = new Timestamp(toTime.getTime());
		String depotInReceipt="SELECT * FROM DepotInReceipt WHERE inTime BETWEEN "+fTime+" AND "+tTime;
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(depotInReceipt);
		while(resultSet.next()){
			String packID=resultSet.getString(1);
			Date inTime=new Date(resultSet.getTimestamp(2).getTime());
			String destination=resultSet.getString(3);
			Location location=new Location(resultSet.getString(4), resultSet.getInt(5),
					resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8));
			DepotInReceiptPO temppo=new DepotInReceiptPO(packID,inTime,destination,location);
			po.add(temppo);
		}
		return po;
	}
	
	public void addItem(DepotInReceiptPO item) throws SQLException{
		String packID=item.getPackID();
		Timestamp inTime=new Timestamp(item.getInTime().getTime());
		String destination=item.getDestination();
		Location location=item.getLocation();
		String transferNum = location.getTransferNum();
		int regionID = location.getRegionID();
		int rowID = location.getRowID();
		int shelfID = location.getShelfID();
		int postID = location.getPostID();
		String add="INSERT INTO DepotInReceipt VALUES (?,?,?,?,?,?,?,?)";
		Connection connection=connectToDB();
		PreparedStatement statement = connection.prepareStatement(add);
		statement.setString(1, packID);
		statement.setTimestamp(2, inTime);
		statement.setString(3, destination);
		statement.setString(4, transferNum);
		statement.setInt(5, regionID);
		statement.setInt(6, rowID);
		statement.setInt(7, shelfID);
		statement.setInt(8, postID);
		statement.executeUpdate();
		stopconnection(connection);
	}
	
	public void deleteAll() throws SQLException{
		String delete="DELETE FROM DepotInReceipt";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(delete);
		stopconnection(connection);
	}
	
}
