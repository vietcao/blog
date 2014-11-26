package function;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.java.javaURLContextFactory;

import dao.PostDao;

import util.GetCookie;
import util.sortViaPost_time_edit;

import model.Post;

public class PostFunc {

	/**
	 * @param args
	 */
	// choose newest post of all friend to display on index page
	public static ArrayList<Post> choose10NextPost(ArrayList<Post> input, int index_begin){
		ArrayList<Post> result= new ArrayList<Post>();
		input.sort(new sortViaPost_time_edit());
		/*
		int i;
		int j = index_begin + 10;
		for(  i = index_begin ; i < j; i++){
			result.add(input.get(i));
		}
		*/
		int i= index_begin;
		while( i< input.size()){
			result.add(input.get(i));
			i++;
			if(i == 10) break;
		}
		return result;
	}
	
	// add new Post 
	public static void addPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		int user_id;
		String suser_id = GetCookie.run(request, "id");
		user_id = Integer.valueOf(suser_id);
		
		String content = request.getParameter("data");
		
		java.util.Date date = new Date();
		Timestamp time_post = new Timestamp(date.getTime());
		
		Post post = new Post();
		post = PostDao.addPost(content,time_post, user_id);
		if(post ==null){
			request.setAttribute("success", "0");
		}else{
			request.setAttribute("success", "1");
		}
		request.setAttribute("post", post);
		request.getRequestDispatcher("/post/newPost.jsp").forward(request, response);
	}
}


