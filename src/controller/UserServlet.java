package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import function.UserFunc;



/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//--- Handle request "index" from inside
		if(request.getAttribute("query") == "/index"){					
			UserFunc.RedirectfromInside(request, response);
			return;
		}
		
		// ---Handle request from outside
		String path = (String)request.getAttribute("uri");
		
		path = path.substring(5); // delete /user
		
		// Handle request "index" from outside
		if(path.startsWith("/index")){
			UserFunc.RedirectfromOutsite(request, response);
			return;
		}
		// Handle load more post
		if(path.startsWith("/loadmorePost")){
			UserFunc.loadMorePost(request, response);
			return;
		}
		
		// -- Handle specific function
		// Handle request "search" from outside	
		if(path.startsWith("/function")){
			path = path.substring(9); // delete /function
			if(path.startsWith("/search")){
				UserFunc.SearchUser(request, response);
				return;
			}
		}
		UserFunc.getUserInfo(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ---Handle request from outside
		String path = (String)request.getAttribute("uri");
		path = path.substring(5); // delete /user
		// -- Handle specific function
		// Handle request "search" from outside	
		if(path.startsWith("/function")){
			path = path.substring(9); // delete /function
			if(path.startsWith("/addfriend")){
				String query = request.getParameter("query");
				if(query.equals("request")){
					UserFunc.AddFriendRequest(request, response);
					return;
				}
				if(query.equals("getinfo")){
					UserFunc.getInfoFriendRequest(request, response);
					return;
				}
				if(query.equals("accept")){
					UserFunc.acceptFriendRequest(request, response);
					return;
				}if(query.equals("deny")){
					UserFunc.denyFriendRequest(request, response);
					return;
				}
			}
			if(path.startsWith("/new")){
				UserFunc.getNew(request, response);
				return;
			}
			if(path.startsWith("/edit")){
				UserFunc.editUser(request, response);
				return;
			}
			if(path.startsWith("/timepull")){
				UserFunc.setTimePull(request, response);
				return;
			}
		}
	}
}