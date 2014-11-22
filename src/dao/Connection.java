package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
public class Connection {
	public static java.sql.Connection con;
	/**
	 * @param args
	 */
	// Connect to database;
	public static void Connections(){
		String dbClass = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://127.0.0.1/blog";
		String account ="root";
		String password ="cbhtabt123";
		try{
			Class.forName(dbClass);
			con =  DriverManager.getConnection(dbUrl, account, password);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.getStackTrace();
		}
		
	}

}

