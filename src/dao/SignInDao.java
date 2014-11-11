package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import model.User;

public class SignInDao {

	/**
	 * @param args
	 */
	public static boolean addUser(User user){
		Connection.Connections();
		try{
			CallableStatement cs = Connection.con.prepareCall("{call checkduplicateUser(?)}");
			cs.setString(1, user.getUsername());
			ResultSet rs2 =cs.executeQuery();
			if( !rs2.next()){
				System.out.println("check here");
				cs = Connection.con.prepareCall("{call addUser(?,?,?,?,?)}");
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
}

