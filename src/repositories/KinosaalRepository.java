package repositories;

import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;
import entities.Kinosaal;

public class KinosaalRepository {

	public static void delete(Kinosaal kinosaal) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(kinosaal);
		session.getTransaction().commit();
		
	}

	public static Kinosaal find(int saalNr) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Kinosaal kinosaal = (Kinosaal) session.get(Kinosaal.class, saalNr);
		return kinosaal;
	}

	public static void update(Kinosaal kinosaal) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.merge(kinosaal);
		session.getTransaction().commit();
	}

	
	public static List<Kinosaal> getAllCinemahalls(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Kinosaal> hall = session.createCriteria(Kinosaal.class).list();
		session.getTransaction().commit();
		return hall;
	}
	
	public static int createNewCinemahall(Kinosaal kinosaal){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(kinosaal);
		session.getTransaction().commit();
		return kinosaal.getSaalNr();
	}
	
	
}
