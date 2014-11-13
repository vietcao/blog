package dao;

import java.sql.CallableStatement;

import java.sql.ResultSet;

import model.User;

public class LoginDao {

	/**
	 * @param args
	 */
	public static int checkLogin(User user){
		Connection.Connections();
		try{
			CallableStatement cs = Connection.con.prepareCall("{call checkLogin(?,?)}");
			cs.setString(1, user.getUsername());
			cs.setString(2, user.getPassword());
			ResultSet rs = cs.executeQuery();
			if( rs.next()){			
				return rs.getInt("id");
			}
		}catch(Exception e){
			e.getStackTrace();
		}
		return 0;
	}

}
