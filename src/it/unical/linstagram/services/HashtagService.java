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
	@Autowired
	private ModelDAO modelDAO;

	public Hashtag getHashtag(String tag) {
		return hashtagDAO.getHashtagByValue(tag);
	}

	/**
	 * Update hashtag count according whether profile is public or private
	 * @param username
	 * @param isPrivate
	 * @return
	 */
	public boolean modifyCounterPerUser(String username, boolean isPrivate) {
		final List<Hashtag> allHashtagByUser = hashtagDAO.getAllHashtagByUser(username);
		for (Hashtag hashtag : allHashtagByUser) {
			hashtag.setCount((isPrivate) ? hashtag.getCount() - 1 : hashtag.getCount() + 1);
			modelDAO.update(hashtag);
		}
		return true;

	}

}
