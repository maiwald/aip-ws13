package repositories;

import java.util.List;

import org.hibernate.Session;

import entities.Kinosaal;

public class KinosaalRepository extends HibernateRepository {

	public static void create(Kinosaal kinosaal) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(kinosaal);
		session.getTransaction().commit();
	}

	public static Kinosaal find(int saalNr) {
		Session session = getCurrentSession();
		session.beginTransaction();
		Kinosaal kinosaal = (Kinosaal) session.get(Kinosaal.class, saalNr);
		return kinosaal;
	}

	public static List<Kinosaal> getAll() {
		Session session = getCurrentSession();
		session.beginTransaction();
		List<Kinosaal> list = session.createCriteria(Kinosaal.class).list();
		session.getTransaction().commit();
		return list;
	}

	public static void update(Kinosaal kinosaal) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.merge(kinosaal);
		session.getTransaction().commit();
	}

	public static void delete(Kinosaal kinosaal) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.delete(kinosaal);
		session.getTransaction().commit();
	}

}
