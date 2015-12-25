package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import po.receiptpo.PayReceiptPO;
import typeDefinition.FeeType;
import typeDefinition.ReceiptState;

public class PayReceiptDBManager extends DBManager{

	public ArrayList<PayReceiptPO> getList(Date fromtime, Date toTime) throws SQLException{
		ArrayList<PayReceiptPO> po=new ArrayList<PayReceiptPO>();
		Timestamp fTime = new Timestamp(fromtime.getTime());
		Timestamp tTime = new Timestamp(toTime.getTime());
		String payReceipt="SELECT * FROM PayReceipt WHERE payTime BETWEEN '"+fTime+"' AND '"+tTime+"'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(payReceipt);
		while(resultSet.next()){
			Date payTime=new Date(resultSet.getTimestamp(1).getTime());
			double fee=resultSet.getDouble(2);
			String payMan=resultSet.getString(3);
			String payAccount=resultSet.getString(4);
			FeeType payType=FeeType.values()[resultSet.getInt(5)];
			PayReceiptPO temppo=new PayReceiptPO(payTime,fee,payMan,payAccount,payType);
			po.add(temppo);
		}
		return po;
	}

	public ArrayList<PayReceiptPO> getListByState(ReceiptState state){
		//todo 刘卉继续写
		return null;
	}
	
	public void addItem(PayReceiptPO item) throws SQLException{
		Timestamp payTime=new Timestamp(item.getPayTime().getTime());
		double fee=item.getFee();
		String payMan=item.getPayMan();
		String payAccount=item.getPayAccount();
		int payType=item.getPayType().ordinal();
		String add="INSERT INTO PayReceipt VALUES (?,?,?,?,?)";
		Connection connection=connectToDB();
		PreparedStatement statement = connection.prepareStatement(add);
		statement.setTimestamp(1, payTime);
		statement.setDouble(2, fee);
		statement.setString(3, payMan);
		statement.setString(4, payAccount);
		statement.setInt(5, payType);
		statement.executeUpdate();
		stopconnection(connection);
	}
	
	public void deleteAll() throws SQLException{
		String delete="DELETE FROM PayReceipt";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(delete);
		stopconnection(connection);
	}
	
}
