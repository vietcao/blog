package test;

import java.sql.Date;

public class TestDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date d1 = new Date(1993, 02, 17);
		Date d2 = new Date(1994, 01, 12);
		System.out.print(d1.compareTo(d2));
	}

}
