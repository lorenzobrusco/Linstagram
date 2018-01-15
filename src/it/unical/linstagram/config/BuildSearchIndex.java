package it.unical.linstagram.config;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import it.unical.linstagram.helper.Indexer;
import it.unical.linstagram.persistence.HibernateUtil;

@Component
public class BuildSearchIndex
implements ApplicationListener<ContextRefreshedEvent> {
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

		String configPath = HibernateUtil.getConfigPath();
		if (configPath != null && !Files.exists(Paths.get(HibernateUtil.getConfigPath() + "/../build/indexes"))) {
			;
		}
		Indexer.init();
		return;
	}



} // class