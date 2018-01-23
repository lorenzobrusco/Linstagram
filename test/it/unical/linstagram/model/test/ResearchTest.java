package it.unical.linstagram.model.test;

import java.util.List;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.linstagram.helper.Indexer;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HashtagDAO;
import it.unical.linstagram.persistence.HibernateUtil;
import it.unical.linstagram.persistence.IHashtagDAO;
import it.unical.linstagram.persistence.IUserDAO;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.UserDAO;

public class ResearchTest extends AbstractModelTest {

	private static IHashtagDAO hashtagDAO;
	private static ModelDAO modelDAO;
	private static IUserDAO userDAO;

	@BeforeClass
	public static void init() {
		hashtagDAO = new HashtagDAO();
		modelDAO = new ModelDAO();
		userDAO = new UserDAO();
	}
	
	@Before
	public void before ()
	{
		Indexer.init();
	}
		
	@Test
	public void researchHashtagStandard() {
		
//		HibernateUtil.init();
		modelDAO.save(new Hashtag("ciao".toLowerCase()));
		modelDAO.save(new Hashtag("ciaoCOMEVA".toLowerCase()));
		modelDAO.save(new Hashtag("COMEVA".toLowerCase()));
		modelDAO.save(new Hashtag("tag".toLowerCase()));
		modelDAO.save(new Hashtag("CIA".toLowerCase()));
		
		Hashtag suggestions = hashtagDAO.getHashtagByValue("COMEVA");
		
		System.out.println(suggestions.getHashtag() + " : " + suggestions.getCount());
		
		Assert.assertEquals("comeva", suggestions.getHashtag());
	}
	@Test
	public void researchHashtagAutocomplete() {
		
		modelDAO.save(new Hashtag("ciao"));
		modelDAO.save(new Hashtag("ciaoCOMEVA"));
		modelDAO.save(new Hashtag("COMEVA"));
		modelDAO.save(new Hashtag("tag"));
		modelDAO.save(new Hashtag("CIA"));
//		modelDAO.save(new Hashtag("CIO"));
		
		Session hibernateTestSession = HibernateUtil.getSession();
//		List<Hashtag> hashtags = hibernateTestSession.createQuery("FROM Hashtag").list();
//
//		for (Hashtag h : hashtags) {
//			System.out.println(h.getHashtag());
//		}

		List<Hashtag> suggestions = hashtagDAO.getSuggestions("CIA");

		for (Hashtag hashtag : suggestions) {
			System.out.println(hashtag.getHashtag() + " : " + hashtag.getCount());
		}

		hibernateTestSession.close();
		Assert.assertEquals(3, suggestions.size());
	}
	
	@Test
	public void researchUserAutocomplete() {
		
//		HibernateUtil.init();
		modelDAO.save(new User("Eliana","email","pass"));
		modelDAO.save(new User("Elia","emaill","pass"));
		modelDAO.save(new User("lana","email2","pass"));
		modelDAO.save(new User("paola","email1","pass"));
		

		Session hibernateTestSession = HibernateUtil.getSession();
//		List<User> users = hibernateTestSession.createQuery("FROM User").list();

//		for (User u : users) {
//			System.out.println(u.getUsername());
//		}

		
		
		List<User> suggestions = userDAO.getSuggestions("el");
		System.out.println("la size della lista è "+ suggestions.size());
		
		System.out.println("SONO QUI");
		
		for (User user : suggestions) {
			System.out.println(user.getUsername());
		}
//		hibernateTestSession.close();
		
		Assert.assertEquals(2, suggestions.size());
	}

	
	@Test
	public void researchUserAutocompleteName() {
		
//		HibernateUtil.init();
		
		modelDAO.save(new User("Eliana","email","pass", "Eliana", "Cannella"));
		modelDAO.save(new User("cicciociccio","emaill","pass", "","ell"));
		modelDAO.save(new User("lana","email2","pass","ciccio", "pasticcio"));
		modelDAO.save(new User("paola","email1","pass", "Paola", "AruQ"));
				
		
		List<User> suggestions = userDAO.getSuggestionsName("el");
		System.out.println("la size della lista è "+ suggestions.size());
		
		
		for (User user : suggestions) {
			System.out.println(user.getUsername());
		}
//		hibernateTestSession.close();
		
		Assert.assertEquals(2, suggestions.size());
	}
	
	@Test
	public void researchCiccioCiccia() {

		
		modelDAO.save(new User("ciccio","email","pass", "Eliana", "Cannella"));
		modelDAO.save(new User("ciccia","emaill","pass", "","ell"));
		modelDAO.save(new User("cicioBello","email2","pass","ciccio", "pasticcio"));
		modelDAO.save(new User("alessio","email1","pass", "Paola", "AruQ"));
				
		
		List<User> suggestions = userDAO.getSuggestions("cicc");
		System.out.println("la size della lista è "+ suggestions.size());
		
		
		for (User user : suggestions) {
			System.out.println(user.getUsername());
		}	
		Assert.assertEquals(2, suggestions.size());
	}
	
	
}
