package it.unical.linstagram.helper;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import it.unical.linstagram.persistence.HibernateUtil;

@Component
public class BuildSearchIndex
implements ApplicationListener<ContextRefreshedEvent> {

	//	@PersistenceContext
	//	private EntityManager entityManager;

	/**
	 * Create an initial Lucene index for the data already present in the
	 * database.
	 * This method is called when Spring's startup.
	 */
	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		
		System.out.println("SONO ARRIVATO!");
		HibernateUtil.initSessionFactory(false);
		System.out.println("HO FINITO!");
		Indexer.init();
		return;
	}
	


} // class