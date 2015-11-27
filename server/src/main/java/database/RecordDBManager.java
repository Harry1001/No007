package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import po.recordpo.RecordPO;


public class RecordDBManager extends DBManager{

	public void addRecord(RecordPO po) throws SQLException{
		Date d=po.getOpeTime();
		String s1=po.getOperator();
		String s2=po.getOperation();
		
		String recordInsert="INSERT INTO Record"+" VALUES ("+d+","+s1+","+s2+")";
		
		Connection connection =connectToDB();
		Statement statement=connection.createStatement();
		statement.executeUpdate(recordInsert);
		stopconnection(connection);
	}
	
	//public 
}
