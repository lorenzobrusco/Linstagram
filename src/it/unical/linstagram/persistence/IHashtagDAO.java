package it.unical.linstagram.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.lang.Nullable;

import it.unical.linstagram.model.Hashtag;

public interface IHashtagDAO {
	
	@Nullable
	Hashtag getHashtagByValue (String value);
	
	public List getSuggestions (String queryString);
//	public List standardSearch (String queryString);

	List<Hashtag> getAllHashtagByUser(String username);

	void updateAllHashtagCountsByUser(String username, int toAdd);

}
