package repositories;

import org.hibernate.Session;

import util.HibernateUtil;

public abstract class HibernateRepository {

	protected static Session getCurrentSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

}
