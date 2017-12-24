package it.unical.linstagram.helper;

import java.util.ArrayList;
import java.util.List;

public class HashtagFinder {

	public static List<String> findHashtags (String text)
	{
		String[] arr = text.split(" ");    
		List<String> hashtags = new ArrayList<>();

		for ( String ss : arr) {

			if (ss.startsWith("#"))
			{
				String[] connctedTags = ss.substring(1).split("#"); 
				for (int i = 0; i < connctedTags.length; i++) {
					hashtags.add(connctedTags[i]);
				}
			}
		}

		return hashtags;
	}

}
