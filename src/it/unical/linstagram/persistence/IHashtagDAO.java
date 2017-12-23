package it.unical.linstagram.persistence;

import org.springframework.lang.Nullable;

import it.unical.linstagram.model.Hashtag;

public interface IHashtagDAO {
	
	@Nullable
	Hashtag getHashtagByValue (String value);

}
