package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.infopo.AgencyPO;

public class AgencyDBManager extends DBManager{
	
	public void add(AgencyPO po) throws SQLException{
		String agencyadd = "INSERT INTO Agency"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		Connection connection = connectToDB();
		PreparedStatement preparedStatement = connection.prepareStatement(agencyadd);
		preparedStatement.setString(1, po.getAgencyName());
		preparedStatement.setString(2, po.getAgencyType());
		preparedStatement.setString(3, po.getAgencyID());
		preparedStatement.setString(4, po.getLocation());
		preparedStatement.setInt(5, po.getArea());
		preparedStatement.setInt(6, po.getRent());
		preparedStatement.executeUpdate(agencyadd);
		stopconnection(connection);		
	}
	
	public void delete(String agencyID) throws SQLException{
		String agencydelete = "DELETE FROM Agency"
				+ " WHERE ID = '" + agencyID + "'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(agencydelete);
		stopconnection(connection);
	}
	
	public ArrayList<AgencyPO> getall() throws SQLException{
		ArrayList<AgencyPO> agencyPOs = new ArrayList<AgencyPO>();
		String agencygetall = "SELECT * FROM Agency";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(agencygetall);
		while(resultSet.next()){
			AgencyPO agencyPO = new AgencyPO(resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5),
						resultSet.getInt(6));
			agencyPOs.add(agencyPO);
		}
		stopconnection(connection);		
		return agencyPOs;
	}
}
