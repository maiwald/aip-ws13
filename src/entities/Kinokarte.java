package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "barcode" }))
public class Kinokarte {

	private int barcode;
	private Kunde kunde;
	private Kinosaal kinosaal;

	public Kinokarte() {
	}

	@Id
	@GeneratedValue
	@Column(name = "barcode")
	public int getBarcode() {
		return barcode;
	}

	// Mit @JoinColumn legen wir fest, dass in der Tabelle Kinokarte eine
	// Fremdschl��sselspalte
	// existiert. Da wir keinen Namen festlegen, verwendet Hibernate die Spalte
	// kunde_id.
	@ManyToOne
	@JoinColumn(name = "kdNr")
	public Kunde getKunde() {
		return kunde;
	}

	@ManyToOne
	@JoinColumn(name = "saalNr", nullable = false)
	public Kinosaal getKinosaal() {
		return kinosaal;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public void setKinosaal(Kinosaal kinosaal) {
		this.kinosaal = kinosaal;
	}

}
