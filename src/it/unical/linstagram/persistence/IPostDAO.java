package it.unical.linstagram.persistence;

import java.util.List;

import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.User;


public interface IPostDAO {

	List<User> getUserTaggedByPostId(int postID);
	List<User> getLikesByPostId(int postID);
	List<Hashtag> getHashtagByPostId(int postID);
	List<Comment> getCommentByPostId(int postID);
	
//	Volendo si puuò richedere i post di una determinata data (tipo un filtro per i post)
	
}
