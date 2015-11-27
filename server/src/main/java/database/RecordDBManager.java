package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	
	public ArrayList<RecordPO> getAll() throws SQLException{
		RecordPO po;
		ArrayList<RecordPO> apo=new ArrayList<RecordPO>();
		String recordGet="SELECT * FROM Record";
		Connection connection=connectToDB();
		Statement statement=connection.createStatement();
		ResultSet resultset=statement.executeQuery(recordGet);
		while(resultset.next()){
			resultset.next();
			po=new RecordPO(resultset.getString(1),resultset.getDate(2),resultset.getString(3));
			apo.add(po);
		}
		return apo;
	}
}