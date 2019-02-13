package baza1;

// razred drzave
public class Drzave {
	// atributi
	private int id_drzave;
	private String drzava_slo;
	private String drzava_iso;
	private String oznaka_dvomestna;
	private String oznaka_tromestna;
	private String opomba;
	
	// konstruktor s parametri
	public Drzave(int id_drzave, String drzava_slo, String drzava_iso, String oznaka_dvomestna, String oznaka_tromestna,
			String opomba) {
		super();
		this.id_drzave = id_drzave;
		this.drzava_slo = drzava_slo;
		this.drzava_iso = drzava_iso;
		this.oznaka_dvomestna = oznaka_dvomestna;
		this.oznaka_tromestna = oznaka_tromestna;
		this.opomba = opomba;
	} // konstruktor - konec
	
	// konstruktor brez parametrov
	public Drzave() {
	}

	// getters in setters - vracanje in nastavljanje vrednosti
	public int getId_drzave() {
		return id_drzave;
	}

	public void setId_drzave(int id_drzave) {
		this.id_drzave = id_drzave;
	}

	public String getDrzava_slo() {
		return drzava_slo;
	}

	public void setDrzava_slo(String drzava_slo) {
		this.drzava_slo = drzava_slo;
	}

	public String getDrzava_iso() {
		return drzava_iso;
	}

	public void setDrzava_iso(String drzava_iso) {
		this.drzava_iso = drzava_iso;
	}

	public String getOznaka_dvomestna() {
		return oznaka_dvomestna;
	}

	public void setOznaka_dvomestna(String oznaka_dvomestna) {
		this.oznaka_dvomestna = oznaka_dvomestna;
	}

	public String getOznaka_tromestna() {
		return oznaka_tromestna;
	}

	public void setOznaka_tromestna(String oznaka_tromestna) {
		this.oznaka_tromestna = oznaka_tromestna;
	}

	public String getOpomba() {
		return opomba;
	}

	public void setOpomba(String opomba) {
		this.opomba = opomba;
	}
	// getters in setters - konec
	
} // razred - konec
