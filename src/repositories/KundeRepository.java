package repositories;

import java.util.List;

import org.hibernate.Session;

import entities.Kunde;

public class KundeRepository extends HibernateRepository {

	public static void create(Kunde kunde) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.save(kunde);
		session.getTransaction().commit();
	}

	public static Kunde find(int kundennr) {
		Session session = getCurrentSession();
		session.beginTransaction();
		Kunde kunde = (Kunde) session.get(Kunde.class, kundennr);
		session.getTransaction().commit();
		return kunde;
	}

	public static List<Kunde> getAll() {
		Session session = getCurrentSession();
		session.beginTransaction();
		List<Kunde> kunden = session.createCriteria(Kunde.class).list();
		session.getTransaction().commit();
		return kunden;
	}

	public static void update(Kunde kunde) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.update(kunde);
		session.getTransaction().commit();
	}

	public static void delete(Kunde kunde) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.delete(kunde);
		session.getTransaction().commit();

	}
}
