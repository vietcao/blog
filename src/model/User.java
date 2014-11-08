package model;

import java.sql.Date;

public class User {

	private
		String username;
		String password;
		String nick;
		Date birth;
		String about;
		
		
		public User() {

		}
		
		public User(String username, String password, String nick, Date birth,
				String about) {
			super();
			this.username = username;
			this.password = password;
			this.nick = nick;
			this.birth = birth;
			this.about = about;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getNick() {
			return nick;
		}
		public void setNick(String nick) {
			this.nick = nick;
		}
		public Date getBirth() {
			return birth;
		}
		public void setBirth(Date birth) {
			this.birth = birth;
		}
		public String getAbout() {
			return about;
		}
		public void setAbout(String about) {
			this.about = about;
		}
		
	
}
