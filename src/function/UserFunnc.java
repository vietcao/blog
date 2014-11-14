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

public class UserFunnc {

	/**
	 * @param args
	 */
	// retrieve post for inside redirect  
	public static void RedirectfromInside(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user_logined = (User)request.getAttribute("user_logined");
		int index_begin = (int)request.getAttribute("index_begin");
		ArrayList<Post> arr_post = UserDao.searchPost(user_logined.getId(), index_begin);
		//request.setAttribute("index_begin", index_begin+10);
		request.setAttribute("arr_post", arr_post);
		Cookie index_ck = new Cookie("index_begin", "10");
		response.addCookie(index_ck);
		request.getRequestDispatcher("/user/index.jsp").forward(request, response);
	}
}
