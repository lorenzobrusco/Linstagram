package it.unical.linstagram.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.persistence.HashtagDAO;
import it.unical.linstagram.persistence.ModelDAO;

@Service
public class HashtagService {

	@Autowired
	private HashtagDAO hashtagDAO;

	public Hashtag getHashtag(String tag) {
		return hashtagDAO.getHashtagByValue(tag);
	}

	/**
	 * Update hashtag count according whether profile is public or private
	 * @param username
	 * @param isPrivate
	 * @return
	 */
	public void modifyCounterPerUser(String username, boolean isPrivate) {

			if(isPrivate) 
				hashtagDAO.updateAllHashtagCountsByUser(username, -1);
			else
				hashtagDAO.updateAllHashtagCountsByUser(username, +1);
		return;

	}

}
