package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.Comment;
import model.Post;
import model.User;

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
	
	// get a post via Time post
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
	
	// get a Post via Id
	public static Post getPost(int _id){
		Post result = new Post();
		CallableStatement cs;
		ResultSet rs;
		
		Connection.Connections();
		try{
			cs = Connection.con.prepareCall("{call showPostViaId(?)}");
			cs.setInt(1, _id);
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
	
	// update e Post return true if done
	public static boolean updatePost(int _id,String _data, Timestamp _tp){
		CallableStatement cs;
		int success = 0;
		Connection.Connections();
		try{
			cs = Connection.con.prepareCall("{call updatePost(?,?,?)}");
			cs.setInt(1,_id);
			cs.setString(2, _data);
			cs.setTimestamp(3, _tp);
			success = cs.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(success == 1){
			return true;
		}
		
		return false;
		
	}
	
	
	// get All Comment of a Post
	public static ArrayList<Comment> getComments(int _postid){
		ArrayList<Comment> arr_com = new ArrayList<>();
		CallableStatement cs,cs1;
		ResultSet rs,rs1;
		
		Connection.Connections();
		try{
			cs = Connection.con.prepareCall("{call showComment(?)}");
			cs.setInt(1, _postid);
			rs = cs.executeQuery();
			
			cs1 = Connection.con.prepareCall("{call showUserViaId(?)}");
			int userid;
			while( rs.next()){
				Comment com = new Comment();
				com.setId(rs.getInt("id"));
				com.setContent(rs.getString("content"));
				com.setTime_post(rs.getTimestamp("time_post"));
				com.setPostid(rs.getInt("postid"));
				
				userid = rs.getInt("userid");
				User user = new User();
				cs1.setInt(1, userid);
				rs1 = cs1.executeQuery();
				if( rs1.next()){
					user.setUsername(rs1.getString("username"));
					user.setNick(rs1.getString("nick"));
				}
				com.setUser(user);
				arr_com.add(com);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return arr_com;
		
	}
}
