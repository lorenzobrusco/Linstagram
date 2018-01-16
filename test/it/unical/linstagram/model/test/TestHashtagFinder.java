package it.unical.linstagram.model.test;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import it.unical.linstagram.helper.HashtagFinder;
import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;

public class TestHashtagFinder {
	
	@Test
	public void testHashtagFinder() {
		User eliana = new User("Eliana","email","pass");
		User manuel = new User("Manuel","memail","pass");

		Post post = new Post(eliana,null,Calendar.getInstance(),"Sono #scema#scemoChiLegge#gay # ciao# prova #loSpazio");

		post.getComments().add(new Comment("Si è vero",manuel,post,Calendar.getInstance()));
		post.getComments().add(new Comment("Che stupido",eliana,post,Calendar.getInstance()));
		
		Set<String> findHashtag = HashtagFinder.findHashtags(post.getContent());
		
		for (String h : findHashtag) {
			System.out.println(h);
		}
		Assert.assertEquals(4,findHashtag.size());
	}

}
