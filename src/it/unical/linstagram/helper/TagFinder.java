package it.unical.linstagram.helper;

import java.util.ArrayList;
import java.util.List;

public class TagFinder {

	public static List<String> findTags (String text)
	{
		String[] arr = text.split(" ");    
		List<String> tags = new ArrayList<>();

		for ( String ss : arr) {

			
			if (ss.startsWith("@"))
			{
				tags.add(ss.substring(1));
			}

		}

		return tags;
	}

}
