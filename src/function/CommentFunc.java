package function;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;

import model.Comment;

import util.GetCookie;

public class CommentFunc {

	/**
	 * @param args
	 */
	public static void addComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String suserid = GetCookie.run(request, "id");
		int userid = Integer.parseInt(suserid);
		
		String spostid = request.getParameter("postid");
		int postid = Integer.parseInt(spostid);
		
		String content = request.getParameter("data");
		
		java.util.Date date = new Date();
		Timestamp tp = new Timestamp(date.getTime());
		
		Comment com = CommentDao.addComment(userid, postid, content, tp);
		if( com == null){
			request.setAttribute("success", "0");
		}else
			request.setAttribute("success", "1");
		request.setAttribute("com", com);
		request.getRequestDispatcher("/comment/newComment.jsp").forward(request, response);
	}

}
