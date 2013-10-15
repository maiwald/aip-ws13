package aufgabe1_2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entities.Bankkonto;
import entities.Kinokarte;
import entities.Kinosaal;
import entities.Kunde;

public class Main {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction transaction = null;

		Kunde newNeugebauer = new Kunde("Neugebauer");
		Kunde newMaiwi = new Kunde("Maiwi");

		Bankkonto newKontoNeugebauer = new Bankkonto("DE1115");
		Bankkonto newKontoMaiwi = new Bankkonto("DE2226");

		newNeugebauer.setBankkonto(newKontoNeugebauer);
		newMaiwi.setBankkonto(newKontoMaiwi);

		Kinosaal newKinosaal1 = new Kinosaal(1, 255);
		Kinokarte newKinokarte1 = new Kinokarte();
		
		newKinokarte1.setKinosaal(newKinosaal1);
		newKinokarte1.setKunde(newNeugebauer);

		// Neuen Benutzer in Datenbank speichern:
		try {
			session = sf.getCurrentSession();
			transaction = session.beginTransaction();
			session.save(newKontoNeugebauer);
			session.save(newKontoMaiwi);
			session.save(newNeugebauer);
			session.save(newMaiwi);

			session.save(newKinosaal1);
			session.save(newKinokarte1);

			transaction.commit();

			session.close();
		} catch (Exception e) {
			// rollback(transaction);
			System.out.println(e.getMessage());
		}

	}

}