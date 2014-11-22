package test;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;

import dao.Connection;

public class TestDel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection.Connections();
			try{
				CallableStatement cs1 = Connection.con.prepareCall("{call addFriend(?,?)}");
				cs1.setInt(1, 2);
				cs1.setInt(2, 12);
				cs1.executeUpdate();
			}catch(Exception e1){
				e1.printStackTrace();
				System.out.println("DSDs");
			}

	}

}
