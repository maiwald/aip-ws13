package repositories;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;
import entities.Bankkonto;

public class BankkontoRepository extends HibernateRepository {

	public static void create(Bankkonto konto) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(konto);
		session.getTransaction().commit();
	}

	public static Bankkonto find(String iban) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Bankkonto bankkonto = (Bankkonto) session.get(Bankkonto.class, iban);
		return bankkonto;
	}

	public static List<Bankkonto> getAll() {
		Session session = getCurrentSession();
		session.beginTransaction();
		List<Bankkonto> list = session.createCriteria(Bankkonto.class).list();
		session.getTransaction().commit();
		return list;
	}

	public static void update(Bankkonto konto) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.merge(konto);
		session.getTransaction().commit();
	}

	public static void delete(Bankkonto konto) {
		Session session = getCurrentSession();
		session.beginTransaction();
		session.delete(konto);
		session.getTransaction().commit();
	}

}
