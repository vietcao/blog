package dao;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class Connection {
	static java.sql.Connection con;
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
	
	//check user name not duplicate and then add User
	
	public static boolean addUser(User user){
		Connections();
		try{
			CallableStatement cs = con.prepareCall("{call checkduplicateUser(?)}");
			cs.setString(1, user.getUsername());
			ResultSet rs2 =cs.executeQuery();
			if( !rs2.next()){
				System.out.println("check here");
				cs = con.prepareCall("{call addUser(?,?,?,?,?)}");
				cs.setString(1, user.getUsername());
				cs.setString(2, user.getPassword());
				cs.setString(3, user.getNick());
				cs.setDate(4, user.getBirth());
				cs.setString(5, user.getAbout());
				cs.executeUpdate();
			}else return false;
		}catch(Exception e){
			e.getStackTrace();
		}
		return true;
	}
	public static void main(String args[]){
		
		addUser(new User("Dds","dsd","dsd",null,"dads"));
	}
	
}

