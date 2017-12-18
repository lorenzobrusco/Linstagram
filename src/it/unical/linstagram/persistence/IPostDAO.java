package it.unical.linstagram.persistence;

import java.util.List;

import it.unical.linstagram.model.User;


public interface IPostDAO {

	List<User> getUserTaggedByPostId(int postID);
	List<User> getLikesByPostId(int postID);
	
//	Volendo si puuò richedere i post di una determinata data (tipo un filtro per i post)
	
}
