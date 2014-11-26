package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import function.PostFunc;

/**
 * Servlet implementation class Post
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = (String)request.getAttribute("uri");
		path = path.substring(10); // delete /user/post
		
		if(path.equals("/edit")){
			PostFunc.editPost(request,response);
			return;
		}
		PostFunc.showPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = (String)request.getAttribute("uri");
		path = path.substring(10); // delete /user/post
		
		if(path.equals("/new")){
			PostFunc.addPost(request,response);
			return;
		}
		if(path.equals("/update")){
			PostFunc.updatePost(request, response);
			return;
		}
		
	}
	
	
}
