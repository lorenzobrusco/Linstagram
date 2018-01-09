package it.unical.linstagram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.persistence.HashtagDAO;

@Service
public class HashtagService {

	@Autowired
	private HashtagDAO hashtagDAO;
	
	public Hashtag getHashtag(String tag){
		return hashtagDAO.getHashtagByValue(tag);
	}
}
