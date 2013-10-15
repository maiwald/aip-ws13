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
public class Bankkonto {

	private String iban;

	public Bankkonto() {
	}

	
	public Bankkonto(String iban) {
		this.iban = iban;
	}

	@Id
	@Column(name = "iban")
	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String toString() {
		return "Iban: " + getIban();
	}

}
