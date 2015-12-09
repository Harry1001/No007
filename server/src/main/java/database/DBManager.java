package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	protected Connection connectToDB(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/elsdatabase?useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String password = "";
        
        Connection connection = null;
        try {
			Class.forName(driver);
			connection  = DriverManager.getConnection(url, user, password);
			if(!connection.isClosed())	System.out.println("Successfully connected to the database!");
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry, can't find the database");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return connection;
	}
	
	protected void stopconnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
