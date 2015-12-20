package database;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import po.infopo.StaffPO;
import typeDefinition.Job;

public class StaffDBManager extends DBManager{

	public void add(StaffPO po) throws SQLException{
		String staffid = po.getStaffID();
		String name = po.getName();
		String gender = po.getGender();
		Timestamp birthday = new Timestamp(po.getBirthday().getTime());
		Job position = po.getPosition();
		int job = position.ordinal();
		int basicsalary = po.getBasicSalary();
		int workfrequency = po.getWorkFrequency();
		
		String staffadd = "INSERT INTO Staff VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection connection = connectToDB();
		PreparedStatement statement = connection.prepareStatement(staffadd);
		statement.setString(1, staffid);
		statement.setString(2, name);
		statement.setString(3, gender);
		statement.setTimestamp(4, birthday);
		statement.setInt(5, job);
		statement.setInt(6, basicsalary);
		statement.setInt(7, workfrequency);
		statement.executeUpdate();
		stopconnection(connection);
	}
	
	public void delete(String staffid) throws SQLException{
		String staffdelete = "DELETE FROM Staff WHERE staffID = '" + staffid + "'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(staffdelete);
		stopconnection(connection);
	}
	
	public StaffPO get(String staffid) throws SQLException{
		String staffget = "SELECT * FROM Staff WHERE staffid = '" + staffid + "'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(staffget);
		StaffPO staff = null;
		while(resultSet.next()){
			staff = new StaffPO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					new Date(resultSet.getTimestamp(4).getTime()), Job.values()[resultSet.getInt(5)], resultSet.getInt(6));
		}
		return staff;			
	}
	
	public ArrayList<StaffPO> getAll() throws SQLException{
		ArrayList<StaffPO> staffs = new ArrayList<StaffPO>();
		String staffgetall = "SELECT * FROM Staff";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(staffgetall);
		while (resultSet.next()){
			StaffPO staff = new StaffPO(resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3), new Date(resultSet.getTimestamp(4).getTime()), 
						Job.values()[resultSet.getInt(5)],resultSet.getInt(7));
			staffs.add(staff);
		}
		return staffs;
	}
}
