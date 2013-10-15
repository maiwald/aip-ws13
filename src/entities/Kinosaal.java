package entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kinosaal {

	private int saalNr;
	private int groesse;
	private Set<Kinokarte> kinokarten;

	public Kinosaal() {
	}

	public Kinosaal(int saalNr, int groesse) {
		this.saalNr = saalNr;
		this.groesse = groesse;
	}

	@Id
	@Column(name = "saalNr", nullable = false, length = 100)
	public int getSaalNr() {
		return saalNr;
	}

	@Column(name = "saalGroesse", nullable = false, length = 100)
	public int getGroesse() {
		return groesse;
	}

	@OneToMany(mappedBy = "kinosaal")
	public Set<Kinokarte> getKinokarten() {
		return kinokarten;
	}

	public void setSaalNr(int saalNr) {
		this.saalNr = saalNr;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	public void setKinokarten(Set<Kinokarte> kinokarten) {
		this.kinokarten = kinokarten;
	}

	public String toString() {
		return "SaalNr: " + getSaalNr() + "; Saalgroesse: " + getGroesse();
	}

}
