package function;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//request.setAttribute("index_begin", index_begin+10);
		request.setAttribute("arr_post", arr_post);
		request.setAttribute("arr_user", arr_user);
		Cookie index_ck = new Cookie("index_begin", "10");
		response.addCookie(index_ck);
		request.getRequestDispatcher("/user/index.jsp").forward(request, response);
	}
	
	// retrieve post for outside redirect 
	public static void RedirectfromOutsite(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user= new User();
		Cookie[] ck = request.getCookies();
		String s ="id";
		String sid ="";
		// search cookie id
		for(int i=0; i< ck.length; i++){
			if( s.equals(ck[i].getName())) {
				sid = ck[i].getValue();
				break;
			}
		}
		
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
		Cookie[] ck = request.getCookies();
		System.out.println("loadnmorehere");
		String s ="id";
		String sid ="";
		String sindex = "index_begin";
		String index ="";
		// search cookie id
		for(int i=0; i< ck.length; i++){
			if( s.equals(ck[i].getName())) {
				sid = ck[i].getValue();
			}
			if( sindex.equals(ck[i].getName())) {
				index = ck[i].getValue();
			}
		}
		
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
}