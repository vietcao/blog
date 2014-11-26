package model;

import java.sql.Timestamp;

public class Comment {
	private
		int id;
		String content;
		Timestamp time_post;
		Timestamp time_edit;
		int like;
		User user;
		int postid;
	public Comment() {
			super();
			// TODO Auto-generated constructor stub
		}
	public Comment(int id, String content, Timestamp time_post,
			Timestamp time_edit, int like, User user, int postid) {
		super();
		this.id = id;
		this.content = content;
		this.time_post = time_post;
		this.time_edit = time_edit;
		this.like = like;
		this.user = user;
		this.postid = postid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	
	

}
