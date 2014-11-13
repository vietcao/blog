package model;

import java.sql.Date;

public class Post {
	private
		String content;
		Date time_post;
		Date time_edit;
		int number_of_like;
		
		
	public Post() {

	}


	public Post(String content, Date time_post, Date time_edit,
			int number_of_like) {
		super();
		this.content = content;
		this.time_post = time_post;
		this.time_edit = time_edit;
		this.number_of_like = number_of_like;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getTime_post() {
		return time_post;
	}


	public void setTime_post(Date time_post) {
		this.time_post = time_post;
	}


	public Date getTime_edit() {
		return time_edit;
	}


	public void setTime_edit(Date time_edit) {
		this.time_edit = time_edit;
	}


	public int getNumber_of_like() {
		return number_of_like;
	}


	public void setNumber_of_like(int number_of_like) {
		this.number_of_like = number_of_like;
	}
	
	

	
}