package aufgabe1_2;

import java.util.List;

import repositories.BankkontoRepository;
import repositories.KinokarteRepository;
import repositories.KinosaalRepository;
import repositories.KundeRepository;
import entities.Bankkonto;
import entities.Kinokarte;
import entities.Kinosaal;
import entities.Kunde;

public class Main {

	public static void main(String[] args) {

		// Kunde und Bankkonto

		Bankkonto bankkonto = new Bankkonto();
		bankkonto.setIban("DE13849751983475");
		BankkontoRepository.create(bankkonto);

		Kunde kunde1 = new Kunde("Neugebauer");
		kunde1.setBankkonto(bankkonto);
		KundeRepository.create(kunde1);

		Kunde kunde2 = new Kunde("Buskobusko");
		KundeRepository.create(kunde2);

		Kunde dbKunde1 = KundeRepository.find(kunde1.getKundenNr());
		System.out.println(dbKunde1);

		List<Kunde> list = KundeRepository.getAll();
		System.out.println(list);

		kunde1.setName("Lempel");
		KundeRepository.update(kunde1);

		KundeRepository.delete(kunde2);

		Kinosaal kinosaal = new Kinosaal(25, 3000);
		KinosaalRepository.create(kinosaal);

		Kinokarte kinokarte = new Kinokarte();
		kinokarte.setKinosaal(kinosaal);
		kinokarte.setKunde(kunde1);
		KinokarteRepository.create(kinokarte);
	}

}