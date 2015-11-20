package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.commoditypo.CommodityPO;
import typeDefinition.Location;
import typeDefinition.myTime;

public class CommodityDBManager extends DBManager{
	
	public void addCommodity(CommodityPO c){
		String expressNum = c.getExpressNumber();
		myTime time = c.getInTime();
		int year = time.getYear();
		int month = time.getMonth();
		int day = time.getDate();
		String desination = c.getDestination();
		Location location = c.getStoreloc();
		String transferNum = location.getTransferNum();
		int regionID = location.getRegionID();
		int rowID = location.getRowID();
		int shelfID = location.getShelfID();
		int postID = location.getPostID();
		String commodityInsert = 
				"INSERT INTO Commodity"
				+ " VALUES ('"+ expressNum +"', "+year+", "+month+", "+day+", '"+desination+"', "
				+ "'"+transferNum+"', "+regionID+", "+rowID+", "+shelfID+", "+postID+")";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(commodityInsert);
		} catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
			System.out.println("该位置已经存放货物");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		stopconnection(connection);
	}
	
	public void removeCommodity(String expressNum){
		String commodityDelete =
				"DELETE FROM Commodity"
				+ " WHERE expressNumber = '"+expressNum + "'";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(commodityDelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);		
	}
	
	public ArrayList<CommodityPO> getAll(){
		ArrayList<CommodityPO> commodity = new ArrayList<CommodityPO>();
		String commodityList =
				"Select * FROM Commodity";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(commodityList);
			while(resultSet.next()){
				myTime inTime = new myTime(resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));
				Location storeloc = new Location(resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10));
				CommodityPO commodityPO = new CommodityPO(resultSet.getString(1), inTime, resultSet.getString(5), storeloc);
				commodity.add(commodityPO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
		return commodity;
	}

	public void clear(String transferNum){
		String commodityclear =
				"DELETE FROM Commodity"
				+ " WHERE transferNum = '"+transferNum+"'";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(commodityclear);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
	}
	
	public ArrayList<CommodityPO> getByTransferNum(String transferNum){
		ArrayList<CommodityPO> c = new ArrayList<CommodityPO>();
		String commoditycheck =
				"SELECT * FROM Commodity"
				+ " WHERE transferNum = '"+transferNum+"'";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(commoditycheck);
			while(resultSet.next()){
				myTime inTime = new myTime(resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));
				Location storeloc = new Location(resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10));
				CommodityPO commodityPO = new CommodityPO(resultSet.getString(1), inTime, resultSet.getString(5), storeloc);
				c.add(commodityPO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
		return c;
	}
}
