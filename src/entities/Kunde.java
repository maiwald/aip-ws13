package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Session;

import util.HibernateUtil;

@Entity
@SequenceGenerator(name = "kdNr", initialValue = 101)
@Table(name = "Kunde")
public class Kunde {

	private int kundenNr;
	private String nachname;
	private Bankkonto bankkonto;
	private Set<Kinokarte> kinokarten = new HashSet<Kinokarte>();

	public Kunde() {
	}

	public Kunde(String nachname) {
		this.nachname = nachname;
	}

	// nullable: erzeugt einen NOT-NULL Constraint, beim erstellen der Spalte
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KdNr")
	@Column(name = "kdNr", unique = true, nullable = false)
	public int getKundenNr() {
		return kundenNr;
	}

	@Column(name = "Nachname", nullable = false)
	public String getName() {
		return nachname;
	}

	@OneToOne
	@JoinColumn(name = "IBAN")
	public Bankkonto getBankkonto() {
		return this.bankkonto;
	}

	// @ManyToOne beschreibt die Beziehung aus Sicht der Klasse Kinokarte; bidirektional
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "kunde")
	public Set<Kinokarte> getKinokarten() {
		return this.kinokarten;
	}

	public void setKundenNr(int kundenNr) {
		this.kundenNr = kundenNr;
	}

	public void setName(String name) {
		this.nachname = name;
	}

	public void setBankkonto(Bankkonto bankkonto) {
		this.bankkonto = bankkonto;
	}

	public void setKinokarten(Set<Kinokarte> kinokarten) {
		this.kinokarten = kinokarten;
	}

	
	public void addBankkonto(Bankkonto bankkonto) {
		if (this.bankkonto == null) {
			this.bankkonto = bankkonto;
		}
	}

	public String toString() {
		return "KundenNr: " + getKundenNr() + "; Name: " + getName();
	}

}
