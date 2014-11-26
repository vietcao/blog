package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import jdk.nashorn.internal.codegen.CompilerConstants.Call;

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
	* 	and retrieve all friend to static global arr_user
	*/
	public static ArrayList<Post> searchPost(int user_id, int index_begin){
		Connection.Connections();
		ArrayList<Post> result = new ArrayList<Post>();
		UserFunc.arr_user = new ArrayList<User>();
		try{
			CallableStatement cs = Connection.con.prepareCall("{call selectFriendId(?)}"); // select all friend's id
			cs.setInt(1, user_id);
			ResultSet rs = cs.executeQuery();
			ArrayList<Integer> id_arrays = new ArrayList<Integer>();
			
			while( rs.next()){
				id_arrays.add(rs.getInt("idfriend"));
			}
			
			
			for( int j =0; j< id_arrays.size(); j++){			
				User user_friend = new User();
				cs = Connection.con.prepareCall("{call showUserViaId(?)}"); // select each friend to attach to post
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
					post.setTime_post(rs.getTimestamp("time_post"));
					post.setTime_edit(rs.getTimestamp("time_edit"));
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
	
	// get information about request friends and return all User request
	public static ArrayList<User> getInfoFriendRequest(int id){
		Connection.Connections();
		CallableStatement cs,cs1;
		ResultSet rs, rs1;
		ArrayList<User> result = new ArrayList<>();
		ArrayList<Integer> arr_id	= new ArrayList<>();
		try{
			cs = Connection.con.prepareCall("{call getAddFriendRequest(?)}");  // retrieve all request friend have now
			cs.setInt(1, id);
			rs = cs.executeQuery();
			
			while( rs.next()){
				arr_id.add(rs.getInt("id_request"));
			}
			cs1 = Connection.con.prepareCall("{call showUserViaId(?)}"); // get information about each user request
			for(Integer id_request: arr_id){
				cs1.setInt(1, id_request);
				rs1 = cs1.executeQuery();
				
				while( rs1.next()){
					User user = new User();
					user.setId(rs1.getInt("id"));
					user.setUsername(rs1.getString("username"));
					user.setNick(rs1.getString("nick"));
					result.add(user);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	// accept friend request return true if everything ok ! and false if something happen in database to make user can accept later
	public static boolean acceptFriendRequest(int id, int id_friend){
		Connection.Connections();
		CallableStatement cs, cs1;
		int result =0;
		try{
			cs = Connection.con.prepareCall("{call delRequestFriend(?,?)}");
			cs.setInt(1, id_friend);
			cs.setInt(2, id);
			result = cs.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		try{
			cs1 = Connection.con.prepareCall("{call addFriend(?,?)}");
			cs1.setInt(1, id);
			cs1.setInt(2, id_friend);
			cs1.executeUpdate();
		}catch(Exception e1){
			e1.printStackTrace();
			return false;
		}
		if( result == 1) return true;
		return false;
	}
	
	// deny add friend request return true if everything ok ! and false if something happen in database to make user can accept later
	public static boolean denyFriendRequest(int id, int id_friend){
		Connection.Connections();
		CallableStatement cs;
		int result = 0; 
		try{
			cs = Connection.con.prepareCall("{call delRequestFriend(?,?)}");
			cs.setInt(1, id_friend);
			cs.setInt(2, id);
			result = cs.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		if( result == 1) return true;
		return false;
	}
	
	
	// get new frequenlly return a array post have time_edit sooner than time pull of user
	public static ArrayList<Post> getNew(int id, long timepull){
		Connection.Connections();
		CallableStatement cs, cs1;
		ResultSet rs, rs1;
		ArrayList<Post> result = new ArrayList<>();
		ArrayList<Integer> arr_fid= new ArrayList<>();
		Timestamp timestamp = new Timestamp(timepull);
		
		try{
			cs= Connection.con.prepareCall("{call selectFriendId(?)}"); // select list friend id
			cs.setInt(1, id);
			rs = cs.executeQuery();
			while(rs.next()){
				arr_fid.add(rs.getInt("idfriend"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for(Integer idfriend : arr_fid){
			
			try{
				cs = Connection.con.prepareCall("{call getNew(?,?)}");	 // retrieve all new post of each friend
				cs.setInt(1, idfriend);
				cs.setTimestamp(2, timestamp);			
				rs = cs.executeQuery();
				
				User user = new User();
				if( rs.isBeforeFirst()){								// if a friend have one or more new get User information to display
					cs1 = Connection.con.prepareCall("{call showUserViaId(?)}");
					cs1.setInt(1, idfriend);
					rs1 = cs1.executeQuery();
					if( rs1.next()){
						
						user.setId(rs1.getInt("id"));
						user.setUsername(rs1.getString("username"));
						user.setNick(rs1.getString("nick"));
					}

				}
				while( rs.next()){
					Post post = new Post();
					post.setUser(user);
					post.setId(rs.getInt("id"));
					post.setContent(rs.getString("content"));
					post.setTime_post(rs.getTimestamp("time_post"));
					post.setNumber_of_like(rs.getInt("number_of_like"));
					result.add(post);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
		
	}
	
	// get Id via User name
	public static int getIdViaUsername(String _username){
		int result= 0;
		try{
			 CallableStatement cs = Connection.con.prepareCall("{call getIdViaUsername(?)}");
			 cs.setString(1,_username);
			 ResultSet rs = cs.executeQuery();
			if ( rs.next())
				result = rs.getInt("id");
			 
			}catch(Exception e){
				e.getStackTrace();
			}
		
		return result;	
	}
	

	/* search not limit Post of one User via ID
	and not retrieve user info of each Post cause already know this
	*/
	public static ArrayList<Post> getPostViaUserId(int _id){
		ArrayList<Post> arr_post = new ArrayList<>();
		CallableStatement cs;
		ResultSet rs;
		
		Connection.Connections();
		
		try{
			cs = Connection.con.prepareCall("{call searchPostViaUserId(?)}");
			cs.setInt(1, _id);
			rs = cs.executeQuery();
			
			while(rs.next()){
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setContent(rs.getString("content")); 
				post.setTime_post(rs.getTimestamp("time_post"));
				post.setTime_edit(rs.getTimestamp("time_edit"));
				post.setNumber_of_like(rs.getInt("number_of_like"));
				arr_post.add(post);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return arr_post;
	}
	

	// get List friend of one User to display
	public static ArrayList<User> getListFriend(int _id){
		Connection.Connections();
		
		ArrayList<User> result = new ArrayList<User>();
		try{
			CallableStatement cs = Connection.con.prepareCall("{call selectFriendId(?)}"); // select all friend's id
			cs.setInt(1, _id);
			ResultSet rs = cs.executeQuery();
			ArrayList<Integer> id_arrays = new ArrayList<Integer>();
			
			while( rs.next()){
				id_arrays.add(rs.getInt("idfriend"));
			}
			
			
			for( int j =0; j< id_arrays.size(); j++){			
				User user_friend = new User();
				cs = Connection.con.prepareCall("{call showUserViaId(?)}"); // select each friend 
				cs.setInt(1, id_arrays.get(j));
				rs = cs.executeQuery();
				
				if(rs.next()){
					user_friend.setId(rs.getInt("id"));
					user_friend.setUsername(rs.getString("username"));
					user_friend.setNick(rs.getString("nick"));
					user_friend.setBirth(rs.getDate("birth"));
					user_friend.setAbout(rs.getString("about"));
					result.add(user_friend);			// add each friend to display on friend list
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	// edit User information and return User object was edited
	public static User editUser(User _user){
		User result = new User();
		Connection.Connections();
		CallableStatement cs, cs1;
		ResultSet rs1;
		int success = 0;
		try{
			cs = Connection.con.prepareCall("{call editUser(?,?,?,?)}");
			cs.setInt(1, _user.getId());
			cs.setString(2, _user.getNick());
			cs.setDate(3, _user.getBirth());
			cs.setString(4, _user.getAbout());
			
			success = cs.executeUpdate();
			if( success == 1){
				return _user;
			}else{
				try{
					cs1 = Connection.con.prepareCall("{call showUserViaId(?)}");
					cs1.setInt(1,_user.getId());
					
					rs1 = cs1.executeQuery();
					if(rs1.next()){
						result.setNick(rs1.getString("nick"));
						result.setBirth(rs1.getDate("birth"));
						result.setAbout(rs1.getString("about"));
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	// reset Time pull to live
	public static boolean setTimePull(int _id, Timestamp _timepull){
		CallableStatement cs;
		int result = 0;
		
		Connection.Connections();
		try{
			cs = Connection.con.prepareCall("{call setTimePull(?,?)}");
			cs.setInt(1, _id);
			cs.setTimestamp(2, _timepull);
			result = cs.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(result == 1){
			return true;
		}
		return false;
	}





}
