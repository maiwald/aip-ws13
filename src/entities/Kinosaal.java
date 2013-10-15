package entities;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Kinosaal")
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
	@Column(name = "SaalNr", nullable = false, length = 100)
	public int getSaalNr() {
		return saalNr;
	}

	@Column(name = "SaalGroesse", nullable = false, length = 100)
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
		return "SaalNr: " + getSaalNr() + "; Saalgröße: " + getGroesse();
	}

}
