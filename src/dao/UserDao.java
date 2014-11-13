package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.MixArrPostAndUser;

import function.PostFunc;

import model.Post;
import model.User;


public class UserDao {

	/**
	 * @param args
	 */
	/* search next 10 post from index_begin order by time_edit
	* 	with one friend  pick 10 post newest and union altogether 
	* 	then choose 10 post newest from this begin from index_begin
	*/
	/*
	public static ArrayList<Post> searchPost(int user_id, int index_begin){
		Connection.Connections();
		ArrayList<Post> result = new ArrayList<Post>();
		
		try{
			CallableStatement cs = Connection.con.prepareCall("{call select_friendid(?)}"); // select all friend's id
			cs.setInt(1, user_id);
			ResultSet rs = cs.executeQuery();
			ArrayList<Integer> id_arrays = new ArrayList<Integer>();
			
			while( rs.next()){
				id_arrays.add(rs.getInt("idfriend"));
			}
			
			for( int j =0; j< id_arrays.size(); j++){		
				cs = Connection.con.prepareCall("{call searchpost(?,?,?)}");
				cs.setInt(1, id_arrays.get(j));
				cs.setInt(2, 0);
				cs.setInt(3, 10); // pick 10 post newest of each friend
				rs = cs.executeQuery();
				while(rs.next()){
					Post post = new Post();
					post.setContent(rs.getString("content")); 
					post.setTime_post(rs.getDate("time_post"));
					post.setTime_edit(rs.getDate("time_edit"));
					post.setNumber_of_like(rs.getInt("number_of_like"));
					result.add(post);
				}
				
			}

		}catch(Exception e) {
			e.getStackTrace();
		}
		// pick 10 post newest from all post of friends
		return result = PostFunc.choose10NextPost(result, index_begin);
	}
	*/
	public static MixArrPostAndUser searchPost(int user_id, int index_begin){
		Connection.Connections();
		ArrayList<Post> post_arr = new ArrayList<Post>();
		ArrayList<User> user_arr = new ArrayList<User>();
		MixArrPostAndUser result = new MixArrPostAndUser();
		
		try{
			CallableStatement cs = Connection.con.prepareCall("{call select_friendid(?)}"); // select all friend's id
			cs.setInt(1, user_id);
			ResultSet rs = cs.executeQuery();
			ArrayList<Integer> id_arrays = new ArrayList<Integer>();
			
			while( rs.next()){
				id_arrays.add(rs.getInt("idfriend"));
			}
			
			for( int j =0; j< id_arrays.size(); j++){
				User user = new User();
				cs = Connection.con.prepareCall("{call showUserViaID(?)}");
				cs.setInt(1, id_arrays.get(j));
				rs = cs.executeQuery();
				while( rs.next() ){
					user.setUsername(rs.getString("username"));
					user.setNick(rs.getString("nick"));
					user.setAbout(rs.getString("about"));
					user_arr.add(user);
				}
				cs = Connection.con.prepareCall("{call searchpost(?,?,?)}");
				cs.setInt(1, id_arrays.get(j));
				cs.setInt(2, 0);
				cs.setInt(3, 10); // pick 10 post newest of each friend
				rs = cs.executeQuery();
				while(rs.next()){
					Post post = new Post();
					post.setContent(rs.getString("content")); 
					post.setTime_post(rs.getDate("time_post"));
					post.setTime_edit(rs.getDate("time_edit"));
					post.setNumber_of_like(rs.getInt("number_of_like"));
					post_arr.add(post);
				}
				
			}

		}catch(Exception e) {
			e.getStackTrace();
		}
		// pick 10 post newest from all post of friends
		return post_arr = PostFunc.choose10NextPost(post_arr, index_begin);
	}
	
	public static void main(String args[]){
		searchPost(1,0);
	}
}
