package it.unical.linstagram.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
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
		HibernateUtil.initConfigPath();
		String configPath = HibernateUtil.getConfigPath();
		if (configPath != null && Files.exists(Paths.get(configPath + "/../build/indexes"))) {

			try {
				File file = new File(configPath + "/../build/indexes");
				String[]entries = file.list();
				for(String s: entries){
					File currentFile = new File(file.getPath(),s);
					FileUtils.deleteDirectory(currentFile);
				} 
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			HibernateUtil.initSessionFactory(false);

		}



		Indexer.init();
		return;
	}



} // class