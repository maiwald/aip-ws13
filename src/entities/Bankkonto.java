package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Session;

import util.HibernateUtil;

@Entity
@Table(name = "Bankkonto", uniqueConstraints = { @UniqueConstraint(columnNames = "IBAN") })
public class Bankkonto {

	private int iban;
	private String name;

	public Bankkonto() {
	}

	// SO ?????
	public Bankkonto(String kdName, int iban) {
		this.name = kdName;
		this.iban = iban;
	}

	@Id
	@GeneratedValue
	@Column(name = "IBAN")
	public int getIban() {
		return iban;
	}

	@Column(name = "Nachname")
	public String getKunde() {
		return name;
	}

	public void setKunde(String name) {
		this.name = name;
	}

	public void setIban(int iban) {
		this.iban = iban;
	}

	public String toString() {
		return "Iban: " + getIban() + ", Name: " + getKunde();
	}

}
