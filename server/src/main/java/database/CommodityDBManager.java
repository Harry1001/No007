package database;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import po.commoditypo.CommodityPO;
import typeDefinition.Location;

public class CommodityDBManager extends DBManager{
	
	public void addCommodity(CommodityPO c) throws SQLException{
		String expressNum = c.getExpressNumber();
		Date time = c.getInTime();
		String desination = c.getDestination();
		Location location = c.getStoreloc();
		String transferNum = location.getTransferNum();
		int regionID = location.getRegionID();
		int rowID = location.getRowID();
		int shelfID = location.getShelfID();
		int postID = location.getPostID();
		Timestamp inTime = new Timestamp(time.getTime());
		
		String commodityInsert = "INSERT INTO Commodity"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = connectToDB();
		PreparedStatement statement = connection.prepareStatement(commodityInsert);
		statement.setString(1, expressNum);
		statement.setTimestamp(2, inTime);;
		statement.setString(3, desination);
		statement.setString(4, transferNum);
		statement.setInt(5, regionID);
		statement.setInt(6, rowID);
		statement.setInt(7, shelfID);
		statement.setInt(8, postID);
		statement.executeUpdate();
		stopconnection(connection);
	}
	
	public void removeCommodity(String expressNum){
		String commodityDelete =
				"DELETE FROM Commodity"
				+ " WHERE expressNumber = '"+expressNum + "'";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			int i = statement.executeUpdate(commodityDelete);
			System.out.println(i + " rows have been changed.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);		
	}
	
	public ArrayList<CommodityPO> getAll() throws SQLException{
		ArrayList<CommodityPO> commodity = new ArrayList<CommodityPO>();
		String commodityList =
				"Select * FROM Commodity";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(commodityList);
		while(resultSet.next()){
			Date inTime = new Date(resultSet.getTimestamp(2).getTime());
			Location storeloc = new Location(resultSet.getString(4), resultSet.getInt(5),
							resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8));
			CommodityPO commodityPO = new CommodityPO(resultSet.getString(1), inTime, 
							resultSet.getString(3), storeloc);
			commodity.add(commodityPO);
		}
		stopconnection(connection);
		return commodity;
	}

	public void clear(String transferNum) throws SQLException{
		String commodityclear =
				"DELETE FROM Commodity"
				+ " WHERE transferNum = '"+transferNum+"'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(commodityclear);
		stopconnection(connection);
	}
	
	public ArrayList<CommodityPO> getByTransferNum(String transferNum) throws SQLException{
		ArrayList<CommodityPO> c = new ArrayList<CommodityPO>();
		String commoditycheck =
				"SELECT * FROM Commodity"
				+ " WHERE transferNum = '"+transferNum+"'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(commoditycheck);
		while(resultSet.next()){
			Date inTime = new Date(resultSet.getTimestamp(2).getTime());
			Location storeloc = new Location(resultSet.getString(4), resultSet.getInt(5),
								resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8));
			CommodityPO commodityPO = new CommodityPO(resultSet.getString(1), inTime, resultSet.getString(3), storeloc);
			c.add(commodityPO);
		}
		stopconnection(connection);
		return c;
	}
}
