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
		
		// Handle request from inside
		if(request.getAttribute("query") == "/index"){					
			UserFunc.RedirectfromInside(request, response);
			return;
		}
		
		// Handle request from outside
		String path = (String)request.getAttribute("uri");
		System.out.println(path);
		path = path.substring(5); // delete /user
		System.out.println(path);
		
		if(path.startsWith("/function")){
			path = path.substring(9); // delete /function
			System.out.println(path);
			if(path.contains("/search")){
				System.out.println("userservlet");
				UserFunc.SearchUser(request, response);
				return;
			}
			if(path =="/addfriend"){
				
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
