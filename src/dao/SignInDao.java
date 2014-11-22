package dao;

import java.sql.CallableStatement;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import model.User;

public class SignInDao {

	/**
	 * @param args
	 */
	public static boolean addUser(User user){
		Connection.Connections();
		try{
			CallableStatement cs = Connection.con.prepareCall("{call checkduplicateUser(?)}"); //check user name not duplicate
			cs.setString(1, user.getUsername());
			ResultSet rs =cs.executeQuery();
			if( !rs.next()){
				
				cs = Connection.con.prepareCall("{call addUser(?,?,?,?,?)}");	// add User to users table
				cs.setString(1, user.getUsername());
				cs.setString(2, user.getPassword());
				cs.setString(3, user.getNick());
				cs.setDate(4, user.getBirth());
				cs.setString(5, user.getAbout());
				cs.executeUpdate();
				
				// set friend default itself 
				int id = 0;
				cs = Connection.con.prepareCall("{call getIdviaUserName(?)}"); // retrieve id to go down
				cs.setString(1, user.getUsername());
				rs = cs.executeQuery();
				
				if( rs.next()) id = rs.getInt("id");
				cs = Connection.con.prepareCall("{call addFriend(?,?)}"); // add friend default for new user
				cs.setInt(1, id);
				cs.setInt(2, id);
				cs.executeQuery();
				
				cs = Connection.con.prepareCall("{call addTimePull(?,?)}");	// add time pull default for new user
				cs.setInt(1, id);
				Timestamp timestp = new Timestamp(new Date().getTime());
				cs.setTimestamp(2, timestp);
				cs.executeUpdate();
			}else return false;
		}catch(Exception e){
			e.getStackTrace();
		}
		return true;
	}
}

