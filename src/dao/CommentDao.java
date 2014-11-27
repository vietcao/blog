package dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import model.Comment;

public class CommentDao {

	/**
	 * @param args
	 */
	//add new comment
	public static Comment addComment(int _userid, int _postid, String _content, Timestamp _tp){
		Comment com = new Comment();
		CallableStatement cs, cs1;
		ResultSet rs1;
		int success = 0;
		
		Connection.Connections();
		try{
			cs= Connection.con.prepareCall("{call addComment(?,?,?,?)}");
			cs.setInt(1, _userid);
			cs.setInt(2, _postid);
			cs.setString(3, _content);
			cs.setTimestamp(4, _tp);
			
			success = cs.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(success == 1){
			try{
			cs1 = Connection.con.prepareCall("{call showCommentViaTimepost(?)}");
			cs1.setTimestamp(1, _tp);
			rs1  = cs1.executeQuery();
			if(rs1.next()){
				com.setId(rs1.getInt("id"));
				com.setContent(rs1.getString("content"));
				com.setTime_post(rs1.getTimestamp("time_post"));
				com.setLike(rs1.getInt("number_of_like"));		
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else
			return null;
		
		return com;		
		
	}

}
