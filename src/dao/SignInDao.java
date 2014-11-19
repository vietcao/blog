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
			ResultSet rs =cs.executeQuery();
			if( !rs.next()){
				
				cs = Connection.con.prepareCall("{call addUser(?,?,?,?,?)}");
				cs.setString(1, user.getUsername());
				cs.setString(2, user.getPassword());
				cs.setString(3, user.getNick());
				cs.setDate(4, user.getBirth());
				cs.setString(5, user.getAbout());
				cs.executeUpdate();
				
				// set friend default itself 
				int id = 0;
				cs = Connection.con.prepareCall("{call getIdviaUserName(?)}");
				cs.setString(1, user.getUsername());
				rs = cs.executeQuery();
				if( rs.next()) id = rs.getInt("id");
				cs = Connection.con.prepareCall("{call addFriend(?,?)}");
				cs.setInt(1, id);
				cs.setInt(2, id);
				cs.executeQuery();
			}else return false;
		}catch(Exception e){
			e.getStackTrace();
		}
		return true;
	}
}

