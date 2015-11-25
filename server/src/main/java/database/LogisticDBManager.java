package database;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import po.logisticpo.LogisticPO;

public class LogisticDBManager extends DBManager{

	public void update(LogisticPO po){
		String orderID=po.getOrderNum();
		Date arrivaltime=po.getArrivalTime();
		String logisticState=po.getState();
		Connection connection=connectToDB();
		String history="INSERT INTO Logistic VALUES ('"+orderID+"', "+arrivaltime+", '"+logisticState+"')";
		try {
			Statement statement=connection.createStatement();
			statement.executeUpdate(history);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
	}
	
	public ArrayList<LogisticPO> read(String num){
		ArrayList<LogisticPO> po=new ArrayList<LogisticPO>();
		String reader ="SELECT * FROM Logistic WHERE orderID = '"+num+"'";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(reader);
			while(resultSet.next()){
				String orderID=resultSet.getString(1);
				Date arrivaltime=new Date(resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),
						resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7));
				String logisticState=resultSet.getString(8);
				LogisticPO temppo=new LogisticPO(orderID,arrivaltime,logisticState);
				po.add(temppo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
		return po;		
	}
	
	public void removeLogistic(String num){
		String deleteLogistic="DELETE FROM Logistic WHERE orderID = '"+num + "'";
		Connection connection = connectToDB();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(deleteLogistic);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopconnection(connection);
	}
}
