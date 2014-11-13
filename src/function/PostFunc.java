package function;

import java.util.ArrayList;
import util.sortViaPost_time_edit;

import model.Post;

public class PostFunc {

	/**
	 * @param args
	 */
	public static ArrayList<Post> choose10NextPost(ArrayList<Post> input, int index_begin){
		ArrayList<Post> result= new ArrayList<Post>();
		input.sort(new sortViaPost_time_edit());
		/*
		int i;
		int j = index_begin + 10;
		for(  i = index_begin ; i < j; i++){
			result.add(input.get(i));
		}
		*/
		int i= index_begin;
		while( i< input.size()){
			result.add(input.get(i));
			i++;
			if(i == 10) break;
		}
		return result;
	}
}


