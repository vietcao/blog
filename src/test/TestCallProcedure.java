package test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.User;

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
		CallableStatement cs = conn.prepareCall("{call checkduplicateUser(?)}");
		cs.setString(1, "caominhviet@gmail.com");
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
			PreparedStatement st = conn.prepareStatement("select idfriend from friends where friends.id = (?)");
			st.setInt(1, 1);
			ResultSet rs = st.executeQuery();
			ArrayList<Integer> id_arrays = new ArrayList<Integer>();
			
			while( rs.next()){
				id_arrays.add(rs.getInt("idfriend"));
			}
			for( int j =0; j< id_arrays.size(); j++){
				st = conn.prepareStatement("select * from posts where posts.userid = (?)");
				st.setInt(1, id_arrays.get(j));
				rs = st.executeQuery();
				while(rs.next()){
					System.out.println(rs.getString("content"));
				}
			}

			}catch(Exception e) {
				e.getStackTrace();
			}
			
			
		
		
	
		
		/*
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
		}*/
	
	}
}
