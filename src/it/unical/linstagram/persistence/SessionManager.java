package it.unical.linstagram.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {

	private static SessionManager sessionManager;
	private SessionFactory mSessionFactory;
	private Session session;
	
	
	public static SessionManager getInstance() {
		if(sessionManager == null)
			sessionManager = new SessionManager();
		return sessionManager;
	}

	private SessionManager() {
		this.mSessionFactory = new Configuration().configure().buildSessionFactory();	
	}
	
	public Session openSession() {
		this.session = this.mSessionFactory.openSession();
		return this.session;
	}

	public void closeSession() {
		this.session.close();
	}
	
	
	
	
}
