package it.unical.linstagram.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.lang.Nullable;

import it.unical.linstagram.model.Hashtag;

public interface IHashtagDAO {
	
	@Nullable
	Hashtag getHashtagByValue (String value);
	
	public List search (String queryString);
	public List standardSearch (String queryString);

}
