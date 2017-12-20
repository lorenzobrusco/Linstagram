package it.unical.linstagram.persistence;

import it.unical.linstagram.model.Hashtag;

public interface IHashtagDAO {
	
	Hashtag getHashtagByValue (String value);

}
