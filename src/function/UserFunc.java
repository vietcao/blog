package function;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import sun.util.logging.resources.logging;
import util.GetCookie;

import model.Post;
import model.User;
import dao.UserDao;

public class UserFunc {
	public static ArrayList<User> arr_user;
	/**
	 * @param args
	 */
	// retrieve post for inside redirect  
	public static void RedirectfromInside(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user_logined = (User)request.getAttribute("user_logined");
		ArrayList<Post> arr_post = UserDao.searchPost(user_logined.getId(), 0);
		 
		request.setAttribute("arr_post", arr_post);
		request.setAttribute("arr_user", arr_user);
		Cookie index_ck = new Cookie("index_begin", "10");
		response.addCookie(index_ck);
		request.getRequestDispatcher("/user/index.jsp").forward(request, response);
	}
	
	// retrieve post for outside redirect 
	public static void RedirectfromOutsite(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user= new User();
		String sid = GetCookie.run(request, "id");
		int id = Integer.parseInt(sid);
		user = UserDao.searchUserViaId(id); // retrieve user to display
		ArrayList<Post> arr_post = UserDao.searchPost(id, 0);
		request.setAttribute("user_logined", user);
		request.setAttribute("arr_post", arr_post);
		request.setAttribute("arr_user", arr_user);
		Cookie index_cookie = new Cookie("index_begin", "10"); // reset index back to 10 each time user click home
		index_cookie.setPath("/");
		response.addCookie(index_cookie);
		request.getRequestDispatcher("/user/index.jsp").forward(request, response);
	}
	
	// load more post
	public static void loadMorePost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String sid = GetCookie.run(request, "id");
		String index = GetCookie.run(request, "index_begin");
		int id = Integer.parseInt(sid);
		int index_begin = Integer.parseInt(index);
		ArrayList<Post> arr_post = UserDao.searchPost(id, index_begin);
		request.setAttribute("arr_post", arr_post);
		Cookie index_cookie = new Cookie("index_begin", String.valueOf(index_begin+10)); // plus 10 index each time load more post
		index_cookie.setPath("/");
		response.addCookie(index_cookie);
		request.getRequestDispatcher("/user/loadmorePost.jsp").forward(request, response);
		
	}
	

	// search user via nick
	public static void SearchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String seachkeyword;
		ArrayList<User> arr_user;
		seachkeyword = request.getParameter("searchkeyword");
		arr_user = UserDao.searchUserViaNick(seachkeyword);
		request.setAttribute("arr_user", arr_user);
		request.getRequestDispatcher("/user/search.jsp").forward(request, response);
	}
	
	// add friend request
	public static void AddFriendRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id_requestStr = GetCookie.run(request, "id");
		int id_request = Integer.parseInt(id_requestStr);
		String id_acceptStr = request.getParameter("id");
		int id_accept = Integer.parseInt(id_acceptStr);
		
		String result; 
		
		if(UserDao.addFriendRequest(id_request, id_accept)){ 	//return 1 to indicate already friend and 0 for not
			result = "1";
		}else result = "0";
		
		request.setAttribute("success", result);
		request.getRequestDispatcher("/user/addFriendRequest.jsp").forward(request, response);

	}
	
	// get Info of each friend request for user can chose
	public static void getInfoFriendRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String  sid = GetCookie.run(request, "id");
		int id = Integer.parseInt(sid);
		
		ArrayList<User> result = UserDao.getInfoFriendRequest(id);
		request.setAttribute("arr_request", result);
		request.getRequestDispatcher("/user/getInfoFriendRequest.jsp").forward(request, response);
		
	}
	
	// accept a request add friend 
	public static void acceptFriendRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String  sid = GetCookie.run(request, "id");
		int id = Integer.parseInt(sid);
		
		String sid_friend = request.getParameter("id");
		int id_friend = Integer.parseInt(sid_friend);
		if ( UserDao.acceptFriendRequest(id, id_friend)){
			request.setAttribute("result", "1");	
		}else
			request.setAttribute("result", "0");
		
		request.getRequestDispatcher("/user/result_message.jsp").forward(request, response);
	}
	
	// deny a request add friend
	public static void denyFriendRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String  sid = GetCookie.run(request, "id");
		int id = Integer.parseInt(sid);
		
		String sid_friend = request.getParameter("id");
		int id_friend = Integer.parseInt(sid_friend);
		if ( UserDao.denyFriendRequest(id, id_friend)){
			request.setAttribute("result", "1");
			
		}else
			request.setAttribute("result", "0");
		
		request.getRequestDispatcher("/user/result_message.jsp").forward(request, response);
	}
	
	// get New event
	public static void getNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String sid = GetCookie.run(request, "id");
		String stimepull = GetCookie.run(request, "timepull");
		
		int id = Integer.parseInt(sid);
		long timepull = Long.parseLong(stimepull);
		
		ArrayList<Post> arr_post = UserDao.getNew(id, timepull);
		request.setAttribute("arr_post", arr_post);
		
		request.getRequestDispatcher("/user/loadmorePost.jsp").forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
