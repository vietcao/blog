package model;

import java.sql.Timestamp;

public class Post {
	private
		int id;
		String content;
		Timestamp time_post;
		Timestamp time_edit;
		int number_of_like;
		User user;
		
	public Post() {

	}
	public Post(int id, String content, Timestamp time_post, Timestamp time_edit,
			int number_of_like, User user) {
		super();
		this.id = id;
		this.content = content;
		this.time_post = time_post;
		this.time_edit = time_edit;
		this.number_of_like = number_of_like;
		this.user = user;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}	


	public void setContent(String content) {
		this.content = content;
	}


	public Timestamp getTime_post() {
		return time_post;
	}


	public void setTime_post(Timestamp time_post) {
		this.time_post = time_post;
	}


	public Timestamp getTime_edit() {
		return time_edit;
	}


	public void setTime_edit(Timestamp time_edit) {
		this.time_edit = time_edit;
	}


	public int getNumber_of_like() {
		return number_of_like;
	}


	public void setNumber_of_like(int number_of_like) {
		this.number_of_like = number_of_like;
	}
	
	

	
}
