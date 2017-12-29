package it.unical.linstagram.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ModelDAO {

	public boolean save(Object model) {
		final Session session = HibernateUtil.getHibernateSession();
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
		final Session session = HibernateUtil.getHibernateSession();
		List<?> list = session
				.createNativeQuery(String.format("SELECT * FROM %s", object.getSimpleName().toLowerCase()), object)
				.list();
		session.close();
		return list;
	}

	public boolean update(Object model) {
		final Session session = HibernateUtil.getHibernateSession();
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
		final Session session = HibernateUtil.getHibernateSession();
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

}
