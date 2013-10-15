package repositories;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;
import entities.Kunde;

public class KundeRepository {

	
	public static void delete(Kunde kunde) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(kunde);
		session.getTransaction().commit();
		
	}

	public static Kunde find(int kundennr) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Kunde kunde = (Kunde) session.get(Kunde.class, kundennr);
		return kunde;
	}

	public static void update(Kunde kunde) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.merge(kunde);
		session.getTransaction().commit();
	}

	
	public static List<Kunde> getAllCustomers(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Kunde> customers = session.createCriteria(Kunde.class).list();
		session.getTransaction().commit();
		return customers;
	}
	
	public static int createCustomer(Kunde kunde){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(kunde);
		session.getTransaction().commit();
		return kunde.getKundenNr();
	}
}
