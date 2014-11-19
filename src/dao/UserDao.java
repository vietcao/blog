package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import function.PostFunc;
import function.UserFunc;

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
	public static ArrayList<Post> searchPost(int user_id, int index_begin){
		Connection.Connections();
		ArrayList<Post> result = new ArrayList<Post>();
		UserFunc.arr_user = new ArrayList<User>();
		try{
			CallableStatement cs = Connection.con.prepareCall("{call select_friendid(?)}"); // select all friend's id
			cs.setInt(1, user_id);
			ResultSet rs = cs.executeQuery();
			ArrayList<Integer> id_arrays = new ArrayList<Integer>();
			
			while( rs.next()){
				id_arrays.add(rs.getInt("idfriend"));
			}
			
			for( int j =0; j< id_arrays.size(); j++){			
				User user_friend = new User();						// select each friend to attach to post
				cs = Connection.con.prepareCall("{call showUserViaId(?)}");
				cs.setInt(1, id_arrays.get(j));
				rs = cs.executeQuery();
				
				if(rs.next()){
					user_friend.setId(rs.getInt("id"));
					user_friend.setUsername(rs.getString("username"));
					user_friend.setNick(rs.getString("nick"));
					user_friend.setBirth(rs.getDate("birth"));
					user_friend.setAbout(rs.getString("about"));
					UserFunc.arr_user.add(user_friend);				// add each friend to display on index page
				}
				
				cs = Connection.con.prepareCall("{call searchpost(?,?,?)}");	// retrieve all post of user's friend and each post 
				cs.setInt(1, id_arrays.get(j));									//* attach to unique friend
				cs.setInt(2, 0);
				cs.setInt(3, 10); // pick 10 post newest of each friend
				rs = cs.executeQuery();
				while(rs.next()){
					Post post = new Post();
					post.setUser(user_friend);
					post.setId(rs.getInt("id"));
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
	
	//search user via keyword
	public static ArrayList<User> searchUserViaNick(String input){

		Connection.Connections();
		ArrayList<User> result = new ArrayList<User>();
		
		// retrieve all user if search keyword not specific 
		if(input == ""){
			try{
				CallableStatement cs = Connection.con.prepareCall("{call showUser()}");
				ResultSet rs = cs.executeQuery();
				while( rs.next()){
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setNick(rs.getString("nick"));
					user.setBirth(rs.getDate("birth"));
					user.setAbout(rs.getString("about"));
					result.add(user);
				}
				return result;
			}catch(Exception e){
				e.getStackTrace();
			}
		}
		
		// retrieve user normally via "nick" keyword with pattern 
		try{
		 CallableStatement cs = Connection.con.prepareCall("{call showUserViaNick(?)}");
		 cs.setString(1,input);
		 ResultSet rs = cs.executeQuery();
		 while( rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setNick(rs.getString("nick"));
				user.setBirth(rs.getDate("birth"));
				user.setAbout(rs.getString("about"));
				result.add(user);
			}
		 
		}catch(Exception e){
			e.getStackTrace();
		}
		return result;
	}

	// search user via id
	public static User searchUserViaId(int input){
		User result = new User();
		try{
			 CallableStatement cs = Connection.con.prepareCall("{call showUserViaId(?)}");
			 cs.setInt(1,input);
			 ResultSet rs = cs.executeQuery();
			if ( rs.next()){
					result.setId(rs.getInt("id"));
					result.setUsername(rs.getString("username"));
					result.setNick(rs.getString("nick"));
					result.setBirth(rs.getDate("birth"));
					result.setAbout(rs.getString("about"));
				}
			 
			}catch(Exception e){
				e.getStackTrace();
			}
		return result;
	}
	
	// add friend request
	
	public static boolean addFriendRequest(int id_request, int id_accept){
		Connection.Connections();
		
		try{
			CallableStatement cs = Connection.con.prepareCall("{call checkAlreadyFriend(?,?)}"); // check already add friend yet
			cs.setInt(1, id_request);
			cs.setInt(2, id_accept);
			ResultSet rs = cs.executeQuery();
			
			if( !rs.next()){
				try{
					cs = Connection.con.prepareCall("{call addFriendRequest(?,?)}"); // add to addFriend table to wait accept
					cs.setInt(1, id_request);
					cs.setInt(2, id_accept);
					cs.executeUpdate();
				}catch(Exception e1){ // if exception occur it mean row was exist and still run
					
				}
				try{
					cs = Connection.con.prepareCall("{call addFriend(?,?)}"); // add to friend table one row to indicate friend one way
					cs.setInt(1, id_request);
					cs.setInt(2, id_accept);
					cs.executeUpdate();
				}catch(Exception e2){
					return false;
				}
			}else return false;
		}catch(Exception e){
			return false;
		}
		return true;
	}
	

	
	
	
	
	
	
}
