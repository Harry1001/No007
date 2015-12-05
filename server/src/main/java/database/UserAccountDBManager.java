package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.infopo.UserAccountPO;
import typeDefinition.Job;

public class UserAccountDBManager extends DBManager{

	public void add(UserAccountPO po) throws SQLException{
		String userid = po.getUserID();
		String name = po.getName();
		Job posi = po.getPosition();
		int position = posi.ordinal();
		String password = po.getPassword();
		String useraccountadd = "INSERT INTO Useraccount VALUES (?, ?, ?, ?)";
		Connection connection = connectToDB();
		PreparedStatement statement = connection.prepareStatement(useraccountadd);
		statement.setString(1, userid);
		statement.setString(2, name);
		statement.setInt(3, position);
		statement.setString(4, password);
		statement.executeUpdate();
		stopconnection(connection);
	}
	
	public void delete(String userID) throws SQLException{
		String useraccountdelete = "DELETE FROM Useraccount WHERE userId = '" + userID + "'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(useraccountdelete);
		stopconnection(connection);
	}
	
	public UserAccountPO get(String userid) throws SQLException{
		String useraccountget = "SELECT * FROM Useraccount WHERE userId = '" + userid + "'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(useraccountget);
		resultSet.next();
		UserAccountPO userAccount = new UserAccountPO(resultSet.getString(1), resultSet.getString(2), 
					Job.values()[resultSet.getInt(3)], resultSet.getString(4));
		return userAccount;
	}
	
	public ArrayList<UserAccountPO> getall() throws SQLException {
		ArrayList<UserAccountPO> userAccountPOs = new ArrayList<UserAccountPO>();
		String useraccountgetall = "SELECT * FROM Useraccount";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(useraccountgetall);
		while(resultSet.next()){
			UserAccountPO userAccount = new UserAccountPO(resultSet.getString(1), resultSet.getString(2), 
					Job.values()[resultSet.getInt(3)], resultSet.getString(4));
			userAccountPOs.add(userAccount);
		}
		return userAccountPOs;
	}
}
