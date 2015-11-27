package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.infopo.DriverPO;

public class DriverDBManager extends DBManager{
	
	public void add(DriverPO po) throws SQLException{
		String driverid = po.getDriverID();
		String name = po.getName();
		Date birthday = po.getBirthday();
		String idnum = po.getIDNum();
		String phonenum = po.getPhoneNum();
		String gender = po.getGender();
		Date licenselimit = po.getLicenseLimit();
		
		String driveradd = "INSERT INTO Driver "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection connection = connectToDB();
		PreparedStatement statement = connection.prepareStatement(driveradd);
		statement.setString(1, driverid);
		statement.setString(2, name);
		statement.setDate(3, birthday);
		statement.setString(4, idnum);
		statement.setString(5, phonenum);
		statement.setString(6, gender);
		statement.setDate(7, licenselimit);
		statement.executeUpdate();		
		stopconnection(connection);
	}
	
	public void delete(String driverid) throws SQLException{
		String driverdelete = "DELETE FROM Driver WHERE DriverID = '" + driverid + "'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(driverdelete);
		stopconnection(connection);		
	}
	
	public ArrayList<DriverPO> getAll() throws SQLException{
		ArrayList<DriverPO> drivers = new ArrayList<DriverPO>();
		String drivergetall = "SELECT * FROM Driver";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(drivergetall);
		while(resultSet.next()){
			DriverPO driver = new DriverPO(resultSet.getString(1), resultSet.getString(2),
					resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5),
					resultSet.getString(6), resultSet.getDate(7));
			drivers.add(driver);
		}
		stopconnection(connection);
		return drivers;
	}

}
