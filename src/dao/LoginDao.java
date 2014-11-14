package dao;

import java.sql.CallableStatement;

import java.sql.ResultSet;

import model.User;

public class LoginDao {

	/**
	 * @param args
	 */
	// check login return User object or null if not correct
	public static User checkLogin(User user){
		Connection.Connections();
		User result = new User();
		try{
			CallableStatement cs = Connection.con.prepareCall("{call checkLogin(?,?)}");
			cs.setString(1, user.getUsername());
			cs.setString(2, user.getPassword());
			ResultSet rs = cs.executeQuery();
			if( rs.next()){			
				result.setId(rs.getInt("id"));
				result.setUsername(rs.getString("username"));
				result.setNick(rs.getString("nick"));
				result.setBirth(rs.getDate("birth"));
				result.setAbout(rs.getString("about"));
				return result;
			}
		}catch(Exception e){
			e.getStackTrace();
		}
		return null;
	}

}
