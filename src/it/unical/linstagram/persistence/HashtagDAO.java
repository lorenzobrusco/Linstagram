package it.unical.linstagram.persistence;

import org.hibernate.Session;

import it.unical.linstagram.model.Hashtag;

public class HashtagDAO implements IHashtagDAO {

	@Override
	public Hashtag getHashtagByValue(String value) {
		Session session = HibernateUtil.getHibernateSession();
		Hashtag hashtag = (Hashtag) session.createQuery("FROM  Hashtag h where h.hashtag=:_hashtag")
				.setParameter("_hashtag", value).uniqueResult();
		session.close();
		return hashtag;
	}

	
}
