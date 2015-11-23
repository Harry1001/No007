package database;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.infopo.AgencyPO;

public class AgencyDBManager extends DBManager{
	
	public void add(AgencyPO po){
//		String agencyadd = "INSERT INTO Agency"
//				+ " VALUES (?, ?, ?, ?, ?, ?)";
//		Connection connection = connectToDB();
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement(agencyadd);
//			preparedStatement.setString(1, po.getAgencyName());
//			preparedStatement.setString(2, po.getAgencyType());
//			preparedStatement.setString(3, po.getAgencyID());
//			preparedStatement.setString(4, po.getLocation());
//			preparedStatement.setInt(5, po.getArea());
//			preparedStatement.setInt(6, po.getRent());
//			preparedStatement.executeUpdate(agencyadd);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		stopconnection(connection);
		String agencyadd = "INSERT INTO Agency"
				+ " VALUES ('" + po.getAgencyName() + "', '"+po.getAgencyType()+"', '"+po.getAgencyID()+"', '"+po.getLocation()
				+"', "+po.getArea()+", "+po.getRent()+")";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(agencyadd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);		
	}
	
	public void delete(String agencyID){
		String agencydelete = "DELETE FROM Agency"
				+ " WHERE ID = '" + agencyID + "'";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(agencydelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
	}
	
	public ArrayList<AgencyPO> getall(){
		ArrayList<AgencyPO> agencyPOs = new ArrayList<AgencyPO>();
		String agencygetall = "SELECT * FROM Agency";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(agencygetall);
			while(resultSet.next()){
				AgencyPO agencyPO = new AgencyPO(resultSet.getString(1), resultSet.getString(2),
							resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5),
							resultSet.getInt(6));
				agencyPOs.add(agencyPO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);		
		return agencyPOs;
	}
}
