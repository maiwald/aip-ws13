package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Kinokarte")
public class Kinokarte {

	private int barcode;
	private Kunde kunde;
	private Kinosaal kinosaal;

	public Kinokarte() {
	}

	@Id
	@Column(name = "Barcode")
	public int getBarcode() {
		return barcode;
	}

	// Mit @JoinColumn legen wir fest, dass in der Tabelle Kinokarte eine
	// Fremdschl��sselspalte
	// existiert. Da wir keinen Namen festlegen, verwendet Hibernate die Spalte
	// kunde_id.
	@ManyToOne
	@JoinColumn(name = "kdNr", nullable = false)
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
