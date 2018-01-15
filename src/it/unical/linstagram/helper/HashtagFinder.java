package it.unical.linstagram.helper;

import java.util.HashSet;
import java.util.Set;

public class HashtagFinder {

	public static Set<String> findHashtags (String text)
	{
		String[] arr = text.split("\\s");    
		Set<String> hashtags = new HashSet<>();

		for ( String ss : arr) {

			if (ss.startsWith("#"))
			{
				String[] connctedTags = ss.substring(1).split("#"); 
				for (int i = 0; i < connctedTags.length; i++) {
					if (connctedTags[i].length() != 0)
					hashtags.add(connctedTags[i]);
				}
			}
		}

		return hashtags;
	}

}
