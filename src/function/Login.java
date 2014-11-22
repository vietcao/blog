package function;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDao;

import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// declecation and define variable 
		String username;
		String password;
		User user;
		username = request.getParameter("username");
		password = request.getParameter("password");
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		// check user name password 
		User user_logined; // store user just login or null if not correct
		user_logined = LoginDao.checkLogin(user);

		if( user_logined != null){
			/*Timestamp timepull;
			timepull = LoginDao.getPullTime(user.getId());
			String timepull = String.valueOf(timepull.)
			Cookie  ck = new Cookie("timepull", a)*/
			
			Cookie ck = new Cookie("login", "true");
			response.addCookie(ck);
			String id = String.valueOf(user_logined.getId());
			ck = new Cookie("id", id );
			response.addCookie(ck);
			//request.setAttribute("query", "/index");
			//request.setAttribute("user_logined", user_logined);
			//request.getRequestDispatcher("/UserServlet").forward(request, response);
			response.sendRedirect("/user/index");
		}else{
			request.setAttribute("error", "Username and Password doesn't match! ..");
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
