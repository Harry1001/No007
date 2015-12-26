package database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import myexceptions.InfoBLException;
import po.infopo.BankAccountPO;

public class BankAccountDBManager extends DBManager{

	public void addBankAccount(BankAccountPO po) throws SQLException{
		String accountuser = po.getAccountUser();
		BigDecimal balance = po.getBalance();
		String bankaccoutadd = "INSERT INTO Bankaccount"
				+ " VALUES ('"+accountuser+"', "+balance+")";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(bankaccoutadd);
		stopconnection(connection);
	}
	
	public void deleteBankAccount(String id) throws SQLException {
		String bankaccountdelete = "DELETE FROM Bankaccount"
				+ " WHERE accountuser = " + id;
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		statement.executeUpdate(bankaccountdelete);
		stopconnection(connection);
	}
	
	/**
	 * 收款和付款的时候调用此方法更改余额
	 * 银行账号余额无法实时更新，故使用此方法更新
	 * @param accountuser
	 * @param money
	 * @throws SQLException 
	 */
	public void updateBankAccount(String accountuser, double money) throws SQLException, InfoBLException {
		BankAccountPO po = get(accountuser);
		BigDecimal change = new BigDecimal(money);
		BigDecimal balance=po.getBalance();
		balance=balance.add(change);
		if (balance.compareTo(new BigDecimal(0))<0){
			throw new InfoBLException("当前账户银行余额不足，支付失败");
		}
		else {
			String bankaccountupdate = "UPDATE Bankaccount"
					+ " SET balance = " + balance.toString()
					+ " WHERE accountuser = '" + accountuser + "'";
			Connection connection = connectToDB();
			Statement statement = connection.createStatement();
			statement.executeUpdate(bankaccountupdate);
			stopconnection(connection);
		}
	}
	
	public ArrayList<BankAccountPO> getAll() throws SQLException{
		ArrayList<BankAccountPO> bankAccounts = new ArrayList<BankAccountPO>();
		String bankaccountgetall = "SELECT * FROM bankaccount";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(bankaccountgetall);
		while(resultSet.next()){
			BankAccountPO po = new BankAccountPO(resultSet.getString(1), resultSet.getBigDecimal(2));
			bankAccounts.add(po);
		}
		return bankAccounts;
	}
	
	public BankAccountPO get(String id) throws SQLException {
		String bankaccountget = "SELECT * FROM bankaccount WHERE accountuser = '" + id + "'";
		Connection connection = connectToDB();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(bankaccountget);
		BankAccountPO bankAccountPO = null;
		while(resultSet.next()) {
			bankAccountPO = new BankAccountPO(resultSet.getString(1), resultSet.getBigDecimal(2));
		}
		stopconnection(connection);
		return bankAccountPO;
	}
}
