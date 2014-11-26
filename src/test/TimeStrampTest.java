package test;

import java.sql.Timestamp;
import java.util.Date;

public class TimeStrampTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		System.out.println(ts);
	}

}
