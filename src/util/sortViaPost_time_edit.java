package util;

import java.util.Comparator;
import model.Post;

public class sortViaPost_time_edit implements Comparator<Post>{


	@Override
	public int compare(Post o1, Post o2) {
		// TODO Auto-generated method stub
		return o2.getTime_edit().compareTo(o1.getTime_edit());
	}

	

}
