package it.unical.linstagram.model.test;

import java.util.List;

import javax.jws.WebParam.Mode;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.persistence.HashtagDAO;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.IHashtagDAO;
import it.unical.linstagram.persistence.ModelDAO;

public class ResearchTest extends AbstractModelTest {

	private static IHashtagDAO hashtagDAO;
	private static ModelDAO modelDAO;

	@BeforeClass
	public static void init() {
		hashtagDAO = new HashtagDAO();
		modelDAO = new ModelDAO();
	}

	@Test
	public void researchHashtagAutocomplete() {
		
		modelDAO.save(new Hashtag("ciao"));
		modelDAO.save(new Hashtag("ciaoCOMEVA"));
		modelDAO.save(new Hashtag("COMEVA"));
		modelDAO.save(new Hashtag("tag"));
		modelDAO.save(new Hashtag("CIA"));
		
		Session hibernateTestSession = HibernateUtil.getHibernateTestSession();
		List<Hashtag> hashtags = hibernateTestSession.createQuery("FROM Hashtag").list();

		for (Hashtag h : hashtags) {
			System.out.println(h.getHashtag());
		}

		List<Hashtag> suggestions = hashtagDAO.getSuggestions("cia");

		for (Hashtag hashtag : suggestions) {
			System.out.println(hashtag.getHashtag() + " : " + hashtag.getCount());
		}

		hibernateTestSession.close();
		Assert.assertEquals(3, suggestions.size());
	}

	@Test
	public void researchHashtagStandard() {
		
		modelDAO.save(new Hashtag("ciao".toLowerCase()));
		modelDAO.save(new Hashtag("ciaoCOMEVA".toLowerCase()));
		modelDAO.save(new Hashtag("COMEVA".toLowerCase()));
		modelDAO.save(new Hashtag("tag".toLowerCase()));
		modelDAO.save(new Hashtag("CIA".toLowerCase()));

		Hashtag suggestions = hashtagDAO.getHashtagByValue("COMEVA");

		System.out.println(suggestions.getHashtag() + " : " + suggestions.getCount());

		Assert.assertEquals("comeva", suggestions.getHashtag());
	}

}
