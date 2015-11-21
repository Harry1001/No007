package database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfitDBManager extends DBManager{
	
	public void addIncome(double income){
		BigDecimal added = new BigDecimal(income);
		String incomeadd = "UPDATE Profit"
				+ " SET income = income + " + added.toString();
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(incomeadd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
	}
	
	public void renewIncome(){
		String incomerenew = "UPDATE Profit"
				+ " SET income = 0";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(incomerenew);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
	}
	
	public BigDecimal getIncome(){
		BigDecimal income = null;
		String incomeget = "SELECT income FROM Profit";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(incomeget);
			while(resultSet.next())	income = resultSet.getBigDecimal(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
		return income;
	}
	
	public void addOutcome(double outcome){
		BigDecimal added = new BigDecimal(outcome);
		String outcomeadd = "UPDATE Profit"
				+ " SET outcome = outcome + " + added.toString();
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(outcomeadd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
	}
	
	public void renewOutcome(){
		String outcomerenew = "UPDATE Profit"
				+ " SET outcome = 0";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(outcomerenew);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
	}

	public BigDecimal getOutcome(){
		BigDecimal outcome = null;
		String outcomeget = "SELECT outcome FROM Profit";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(outcomeget);
			while(resultSet.next())	outcome = resultSet.getBigDecimal(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
		return outcome;
	}
}
