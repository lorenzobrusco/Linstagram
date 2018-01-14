package it.unical.linstagram.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TagFinder {

	public static Set<String> findTags (String text)
	{
		String[] arr = text.split(" ");    
		Set<String> tags = new HashSet<>();

		for ( String ss : arr) {

			
			if (ss.startsWith("@"))
			{
				tags.add(ss.substring(1));
			}
		}

		return tags;
	}

}
