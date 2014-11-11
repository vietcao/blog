package function;

import java.io.IOException;

import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import dao.SignInDao;

/**
 * Servlet implementation class SingIn
 */
@WebServlet("/SignInServlet")
public class SingIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = null;
		String password = null;
		String nick = null;
		Date birth= null;
		String about = null;	
		User user = null;
		
		username = request.getParameter("username");
		password = request.getParameter("password");

		if(username == "" || password == "") {
			request.setAttribute("error","Username or password can't empty");
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
			return;
		}
		
		int day, month, year;
		
		try{
		day = Integer.parseInt(request.getParameter("day"));					//Convert to date	
		month = Integer.parseInt(request.getParameter("month"));
		year = Integer.parseInt(request.getParameter("year")) - 1900;
		birth = new Date(year, month, day);	
		}catch(NumberFormatException e){
			
		}																		//	
		if (request.getParameter("nick") != "") { nick = request.getParameter("nick"); }
		if (request.getParameter("about") !="") { about = request.getParameter("about"); }
		user = new User(username, password, nick, birth, about);
		boolean success = SignInDao.addUser(user);
		
		if( success ){
			request.getRequestDispatcher("/SignInSuccess.html").forward(request, response);
			
		}else{
			request.setAttribute("error","Username was exist! please chose another one..");
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
