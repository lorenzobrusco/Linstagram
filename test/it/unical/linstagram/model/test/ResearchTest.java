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
	public static void init ()
	{
		hashtagDAO = new HashtagDAO();
		modelDAO = new ModelDAO();
	}
	
	  /**
     * Regenerates the index for a given class 
     * 
     * @param clazz the class 
     * @param sess the hibernate session 
     */ 
    public static void reindex(Class clazz, Session sess) { 
        FullTextSession txtSession = Search.getFullTextSession(sess); 
        MassIndexer massIndexer = txtSession.createIndexer(clazz); 
        try { 
            massIndexer.startAndWait(); 
        } catch (InterruptedException e) { 
            System.err.println("mass reindexing interrupted: " + e.getMessage()); 
        } finally { 
            txtSession.flushToIndexes(); 
        } 
    } 
	
	@Test
	public void researchHashtag()
	{	
		Session hibernateTestSession = HibernateUtil.getHibernateTestSession();
		reindex(Hashtag.class, hibernateTestSession);
		modelDAO.save(new Hashtag("ciao"));
		modelDAO.save(new Hashtag("ciaoCOMEVA"));	
		modelDAO.save(new Hashtag("COMEVA"));
		modelDAO.save(new Hashtag("tag"));
		modelDAO.save(new Hashtag("CIA"));
		
		System.out.println("SONO QUI");


		List<Hashtag> hashtags =  hibernateTestSession.createQuery("FROM Hashtag").list();
		

		for(Hashtag h : hashtags) {
			System.out.println(h.getHashtag());
		}

		Assert.assertEquals(5,hashtags.size());
		
//		List<Hashtag> suggestions = hashtagDAO.getSuggestions("cia");
		List<Hashtag> suggestions = hashtagDAO.search("cia");
		
		for (Hashtag hashtag : suggestions) {
			System.out.println(hashtag.getHashtag());
		}
		
		hibernateTestSession.close();
		
//		
//		Assert.assertEquals(3, suggestions.size());
	}
	
}
