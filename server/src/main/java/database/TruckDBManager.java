package database;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import po.infopo.TruckPO;

public class TruckDBManager extends DBManager{

	public void add(TruckPO truck) throws SQLException{
		String truckid = truck.getTruckID();
		String licenseid = truck.getLicenceID();
		String engineid = truck.getEngineID();
		String chassisid = truck.getChassisID();
		Timestamp buytime = new Timestamp(truck.getBuyTime().getTime());
		int servetime = truck.getServeTime();
		
		String truckadd = "INSERT INTO Truck VALUES (?, ?, ?, ?, ?, ?)";
		Connection connection = connectToDB();
		PreparedStatement statement = connection.prepareStatement(truckadd);
		statement.setString(1, truckid);
		statement.setString(2, licenseid);
		statement.setString(3, engineid);;
		statement.setString(4, chassisid);
		statement.setTimestamp(5, buytime);
		statement.setInt(6, servetime);
		statement.executeUpdate();
		
		stopconnection(connection);
	}
	
	public void delete(String truckid) throws SQLException{
		String truckdelete = "DELETE FROM Truck WHERE truckid = '" + truckid + "'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(truckdelete);
		stopconnection(connection);
	}
	
	public ArrayList<TruckPO> getAll() throws SQLException{
		ArrayList<TruckPO> trucks  = new ArrayList<TruckPO>();
		String truckgetall = "SELECT * FROM Truck";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(truckgetall);
		while(resultSet.next()){
			TruckPO truck = new TruckPO(resultSet.getString(1), resultSet.getString(2),
					resultSet.getString(3), resultSet.getString(4), new Date(resultSet.getTimestamp(5).getTime()),
					resultSet.getInt(6));
			trucks.add(truck);
		}
		return trucks;
	}
}
