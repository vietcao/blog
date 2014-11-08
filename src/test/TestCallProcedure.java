package test;

import java.sql.*;

public class TestCallProcedure {

	/**
	 * @param args
	 */
	public static Connection getConnection(String dbClass, String dbUrl) 
			throws SQLException {
			Connection conn = null;
			try{
			Class.forName(dbClass);
			conn =  DriverManager.getConnection(dbUrl, "root", "cbhtabt123");
			}catch(ClassNotFoundException e) {
			e.printStackTrace();
			}catch(SQLException e) {
			throw e;
			}
			return conn;
			}
	public static void main(String[] args) {
		String dbClass = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://127.0.0.1/blog";
		Connection conn = null;
		try {
			conn = getConnection(dbClass, dbUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		try{
		CallableStatement cs = conn.prepareCall("{call show_user()}");
		ResultSet rs = cs.executeQuery();


		while (rs.next()) {
		    String supplier = rs.getString("username");
		    String coffee = rs.getString("password");
		    System.out.println(supplier + ": " + coffee);
		}
		}catch(Exception ex){
			ex.getStackTrace();
		}
		try{
		CallableStatement c2 = conn.prepareCall("{call show_userviaid(?)}");
		c2.setInt(1, 2);
		ResultSet rs2 = c2.executeQuery();
		
		while (rs2.next()) {
		    String supplier = rs2.getString("username");
		    String coffee = rs2.getString("password");
		    System.out.println(supplier + ": " + coffee);
		}
		}catch(Exception e){
			e.getStackTrace();
		}
	}

}
