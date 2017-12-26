package it.unical.linstagram.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unical.linstagram.model.Post;

public class ListHelper {
	
	public static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

	public static void order (List<Post> list) {
		Collections.sort(list, new Comparator<Post>() {
			@Override
			public int compare(Post post2, Post post1)
			{
				return  post1.getPostDate().compareTo(post2.getPostDate());
			}
		});
	}
	
}
