package repositories;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;
import entities.Bankkonto;

public class BankkontoRepository {

	public static void delete(Bankkonto konto) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(konto);
		session.getTransaction().commit();
	}
	
	public static void update(Bankkonto konto) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.merge(konto);
		session.getTransaction().commit();
	}

	public static List<Bankkonto> getAllBankaccounts(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Bankkonto> bank = session.createCriteria(Bankkonto.class).list();
		session.getTransaction().commit();
		return bank;
	}
	
	public static int createBankaccount(Bankkonto konto){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(konto);
		session.getTransaction().commit();
		return konto.getIban();
	}
	
	public static Bankkonto find(int iban) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Bankkonto bankkonto = (Bankkonto) session.get(Bankkonto.class, iban);
		return bankkonto;
	}
	
	
	
}
