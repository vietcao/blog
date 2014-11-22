package test;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.chrono.JapaneseChronology;
import java.util.Calendar;
import java.util.Timer;

import dao.Connection;

public class Time {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection.Connections();
		try{
			CallableStatement cs = Connection.con.prepareCall("{call addTimePull(?,?)}");
			cs.setInt(1, 7);
			Timestamp stamp = new Timestamp(new java.util.Date().getTime());
			cs.setTimestamp(2, stamp);
			cs.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		//Date date = new Date(new java.util.Date().getTime());
		//CallableStatement cs = Connection.con.prepareCall("{call addTimePull(?,?)}");
		java.sql.Time date = new java.sql.Time(new java.util.Date().getTime());
		//cs.setTimestamp(parameterIndex, x);
		Timestamp stao = new Timestamp(new java.util.Date().getTime());
		System.out.println(stao);
		
	}

}
