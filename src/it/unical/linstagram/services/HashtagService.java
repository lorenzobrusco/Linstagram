package it.unical.linstagram.services;

import java.util.List;

import org.apache.lucene.queryparser.flexible.standard.processors.AllowLeadingWildcardProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.persistence.HashtagDAO;
import it.unical.linstagram.persistence.ModelDAO;

@Service
public class HashtagService {

	@Autowired
	private HashtagDAO hashtagDAO;
	@Autowired
	private ModelDAO modelDAO;

	public Hashtag getHashtag(String tag){
		return hashtagDAO.getHashtagByValue(tag);
	}

	public boolean decremetCounterPerUser (String username)
	{
		
		hashtagDAO.updateAllHashtagCountsByUser (username, -1);
//		List<Hashtag> allHashtagByUser = hashtagDAO.getAllHashtagByUser(username);
//
//		for (Hashtag hashtag : allHashtagByUser) {
//			hashtag.setCount(hashtag.getCount()-1);
//			modelDAO.update(hashtag);
//		}
		return true;

	}

	public boolean incrementCounterPerUser (String username)
	{

		hashtagDAO.updateAllHashtagCountsByUser (username, 1);
//		List<Hashtag> allHashtagByUser = hashtagDAO.getAllHashtagByUser(username);
//
//		for (Hashtag hashtag : allHashtagByUser) {
//			hashtag.setCount(hashtag.getCount()+1);
//			modelDAO.merge(hashtag);
//		}
		return true;

	}
}
