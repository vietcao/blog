package dao;

import java.sql.CallableStatement;

import java.sql.ResultSet;

import model.User;

public class LoginDao {

	/**
	 * @param args
	 */
	public static boolean checkLogin(User user){
		Connection.Connections();
		try{
			CallableStatement cs = Connection.con.prepareCall("{call checkLogin(?,?)}");
			cs.setString(1, user.getUsername());
			cs.setString(2, user.getPassword());
			ResultSet rs = cs.executeQuery();
			System.out.println("true here");
			if( rs.next()){
				
				return true;
			}
		}catch(Exception e){
			e.getStackTrace();
		}
		return false;
	}

}
