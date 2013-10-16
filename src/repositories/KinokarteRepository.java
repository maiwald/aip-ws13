package repositories;

import java.util.List;

import org.hibernate.Session;

import entities.Kinokarte;

public class KinokarteRepository extends HibernateRepository {

	public static void create(Kinokarte karte) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(karte);
		session.getTransaction().commit();
	}

	public static Kinokarte find(int barcode) {
		Session session = getCurrentSession();
		session.beginTransaction();
		Kinokarte kinokarte = (Kinokarte) session.get(Kinokarte.class, barcode);
		session.getTransaction().commit();
		return kinokarte;
	}

	public static List<Kinokarte> getAll() {
		Session session = getCurrentSession();
		session.beginTransaction();
		List<Kinokarte> cards = session.createCriteria(Kinokarte.class).list();
		session.getTransaction().commit();
		return cards;
	}

	public static void update(Kinokarte karte) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.merge(karte);
		session.getTransaction().commit();
	}

	public static void delete(Kinokarte kinokarte) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.delete(kinokarte);
		session.getTransaction().commit();
	}

}
