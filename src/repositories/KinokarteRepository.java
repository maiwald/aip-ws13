package repositories;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;
import entities.Kinokarte;

public class KinokarteRepository {

	public static void delete(Kinokarte kinokarte) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(kinokarte);
		session.getTransaction().commit();
		
	}

	public static Kinokarte find(int barcode) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Kinokarte kinokarte = (Kinokarte) session.get(Kinokarte.class, barcode);
		return kinokarte;
	}

	public static void update(Kinokarte karte) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.merge(karte);
		session.getTransaction().commit();
	}

	
	public static List<Kinokarte> getAllCinematickets(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Kinokarte> cards = session.createCriteria(Kinokarte.class).list();
		session.getTransaction().commit();
		return cards;
	}
	
	public static int createNewCinematicket(Kinokarte karte){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(karte);
		session.getTransaction().commit();
		return karte.getBarcode();
	}
	
}
