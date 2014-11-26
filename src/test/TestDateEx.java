package test;

import java.sql.Date;

public class TestDateEx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date(2014, 11, 25);
		System.out.println(date.getYear());
		System.out.println(date.getMonth());
		System.out.println(date.getDate());
		System.out.println(date.getDay());
	}

}
