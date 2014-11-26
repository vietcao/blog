package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import model.Post;

public class PostDao {

	/**
	 * @param args
	 */
	// add new post
	public static Post addPost(String _content, Timestamp _timpost, int _userid){
		Post result = new Post();
		CallableStatement cs,cs1;
		ResultSet rs,rs1;
		int success = 0;
		Connection.Connections();
		try{
			cs = Connection.con.prepareCall("{call addPost(?,?,?)}");
			cs.setString(1, _content);
			cs.setTimestamp(2, _timpost);
			cs.setInt(3, _userid);
			success = cs.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(success == 0){
			 return null;
		}else{
			result = showPostViaTimePost(_timpost);
		}
		return result;
		
	}
	public static Post showPostViaTimePost(Timestamp _timepost){
		Post result = new Post();
		CallableStatement cs;
		ResultSet rs;
		
		Connection.Connections();
		try{
			cs = Connection.con.prepareCall("{call showPostViaTimepost(?)}");
			cs.setTimestamp(1, _timepost);
			rs = cs.executeQuery();
			if(rs.next()){
				result.setId(rs.getInt("id"));
				result.setContent(rs.getString("content"));
				result.setTime_post(rs.getTimestamp("time_post"));
				result.setNumber_of_like(rs.getInt("number_of_like"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
