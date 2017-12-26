package it.unical.linstagram.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import it.unical.linstagram.model.Hashtag;

@Repository
public class HashtagDAO implements IHashtagDAO {


	private static final String HASHTAG_EDGE_NGRAM_INDEX = "edgeNGramHashtag";
//	private static final String STANDARD_INDEX = "hashtag";
	
	@Override
	public Hashtag getHashtagByValue(String value) {
		Session session = HibernateUtil.getHibernateSession();
		Hashtag hashtag = (Hashtag) session.createQuery("FROM  Hashtag h where h.hashtag=:_hashtag")
				.setParameter("_hashtag", value.toLowerCase()).uniqueResult();
		session.close();
		return hashtag;
	}

	@Override
	public List getSuggestions(String queryString) {
		Session session = HibernateUtil.getHibernateSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Hashtag.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields(HASHTAG_EDGE_NGRAM_INDEX).matching(queryString).createQuery();

		// wrap Lucene query in a javax.persistence.Query
		org.hibernate.search.FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Hashtag.class);
		fullTextQuery.setMaxResults(5);
		
		List<Hashtag> contactList = fullTextQuery.list();
		
		fullTextSession.close();
		
		return contactList;
	}

//	@Override
//	public List standardSearch(String queryString) {
//		Session session = HibernateUtil.getHibernateSession();
//		FullTextSession fullTextSession = Search.getFullTextSession(session);
//		
//		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Hashtag.class).get();
//		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields(STANDARD_INDEX).matching(queryString).createQuery();
//
//		// wrap Lucene query in a javax.persistence.Query
//		org.hibernate.search.FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Hashtag.class);
//		fullTextQuery.setMaxResults(5);
//		
//		List<Hashtag> contactList = fullTextQuery.list();
//		
//		fullTextSession.close();
//		
//		return contactList;
//	}
	
	


}
