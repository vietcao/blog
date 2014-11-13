package function;

import java.io.IOException;
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
		int flag_id; // store id of user just login or 0 if not correct
		flag_id = LoginDao.checkLogin(user);
		if( flag_id != 0){
			Cookie ck = new Cookie("login", "true");
			response.addCookie(ck);
			request.setAttribute("query", "/index");
			request.setAttribute("user_id", flag_id);
			request.setAttribute("index_begin", 0);
			request.getRequestDispatcher("/UserServlet").forward(request, response);
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
