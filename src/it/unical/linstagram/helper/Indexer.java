package it.unical.linstagram.helper;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;
import org.hibernate.search.batchindexing.MassIndexerProgressMonitor;
import org.hibernate.search.batchindexing.impl.SimpleIndexingProgressMonitor;

import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HibernateUtil;

public class Indexer {

	public static void init() {
		Session session = HibernateUtil.getSession();
		
		System.out.println("SONO LA INIT");
		reindex(Hashtag.class, session);
		reindex(User.class, session);
		session.close();
	}

	
	/**
	 * Regenerates the index for a given class
	 * 
	 * @param clazz
	 *            the class
	 * @param sess
	 *            the hibernate session
	 */
	private static void reindex(Class clazz, Session sess) {
		FullTextSession txtSession = Search.getFullTextSession(sess);
		MassIndexer massIndexer = txtSession.createIndexer(clazz);

		MassIndexerProgressMonitor monitor = new SimpleIndexingProgressMonitor();
		try {
			massIndexer.batchSizeToLoadObjects(25)
			.cacheMode(CacheMode.NORMAL)
			.threadsToLoadObjects(12)
			.idFetchSize(150)
			.transactionTimeout(1800)
			.progressMonitor(monitor) // a MassIndexerProgressMonitor implementation
			.startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println("mass reindexing interrupted: " + e.getMessage());
		} finally {
			txtSession.flushToIndexes();
		}
	}
}
