package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.DespatchReceiptPO;

public class DespatchReceiptDBManager extends DBManager{

	public ArrayList<DespatchReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		ArrayList<DespatchReceiptPO> po=new ArrayList<DespatchReceiptPO>();
		Timestamp fTime = new Timestamp(fromtime.getTime());
		Timestamp tTime = new Timestamp(toTime.getTime());
		String despatchReceipt="SELECT * FROM DespatchReceipt WHERE arrivalTime BETWEEN "+fTime+" AND "+tTime;
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(despatchReceipt);
		while(resultSet.next()){
			Date arrivalTime=new Date(resultSet.getDate(1).getTime());
			String orderNum=resultSet.getString(2);
			String despatchMan=resultSet.getString(3);
			DespatchReceiptPO temppo=new DespatchReceiptPO(arrivalTime,orderNum,despatchMan);
			po.add(temppo);
		}
		return po;
	}
	
	public void addItem(DespatchReceiptPO item) throws SQLException{
		Timestamp arrivalTime=new Timestamp(item.getArrivalTime().getTime());
		String orderNum=item.getOrderNum();
		String despatchMan=item.getDespatchMan();
		String add="INSERT INTO DespatchReceipt VALUES ("+arrivalTime+", '"+orderNum+"', '"+despatchMan+"')";
		Connection connection=connectToDB();
		Statement statement=connection.createStatement();
		statement.executeUpdate(add);
		stopconnection(connection);
	}
	
	public void deleteAll() throws SQLException{
		String delete="DELETE FROM DespatchReceipt";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(delete);
		stopconnection(connection);
	}
}
