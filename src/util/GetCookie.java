package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class GetCookie {

	/**
	 * @param args
	 */
	public static String run(HttpServletRequest request,String cookie_name){
		Cookie[] arr_ck = request.getCookies();
		for(int i=0; i< arr_ck.length; i++){
			if(cookie_name.equals(arr_ck[i].getName())) return arr_ck[i].getValue();
		}
		return null;
	}

}
