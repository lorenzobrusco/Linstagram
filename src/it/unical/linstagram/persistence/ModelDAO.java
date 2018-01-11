package it.unical.linstagram.persistence;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ModelDAO {

	public boolean save(Object model) {
		final Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(model);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			session.close();
		}
	}

	public List<?> getAll(Class<?> object) {
		final Session session = HibernateUtil.getSession();
		List<?> list = session
				.createNativeQuery(String.format("SELECT * FROM %s", object.getSimpleName().toLowerCase()), object)
				.list();
		session.close();
		return list;
	}

	public boolean update(Object model) {
		final Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(model);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			session.close();
		}
	}

	public boolean merge(Object model) {
		final Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			session.merge(model);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	public boolean delete(Class<?> type, Serializable id) {
		final Session session = HibernateUtil.getSession();
	    Object persistentInstance = session.load(type, id);
	    Transaction transaction = null;
		
	    try {
	    	if (persistentInstance != null) {
	    		transaction = session.beginTransaction();
				session.delete(persistentInstance);
				transaction.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	
	public Object initialize(Object detachedParent,String fieldName) {
	    Session session = HibernateUtil.getSession();
		Object reattachedParent = session.merge(detachedParent); 

	    // get the field from the entity and initialize it
	    Field fieldToInitialize;
		try {
			fieldToInitialize = detachedParent.getClass().getDeclaredField(fieldName);
			fieldToInitialize.setAccessible(true);
			Object objectToInitialize = fieldToInitialize.get(reattachedParent);
			Hibernate.initialize(objectToInitialize);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			session.close();
			
		}
		return reattachedParent;

	}


}
