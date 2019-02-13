package baza1;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModelDrzave extends AbstractTableModel {
	
	private List<Drzave> lstDrzave = null; // seznam drzav
	private String[] naslovnaVrstica = {"ID", "Država (SLO)", "Država (ISO)", "Oznaka (2)", "Oznaka (3)", "Opomba"}; // vrednosti v naslovni vrstici
	
	public TableModelDrzave(List<Drzave> lstDrzave) {
		super();
		this.lstDrzave = lstDrzave;
	} // konstruktor

	public List<Drzave> getList() {
		return lstDrzave;
	}
	
	@Override
	public int getColumnCount() {
		return 6; // stevilo stolpcev
	}

	@Override
	public int getRowCount() {
		return lstDrzave.size(); // stevilo vrstic = stevilo vnosov v seznamu
	}

	@Override
	public Object getValueAt(int vrstica, int stolpec) {
		Drzave drzava = null;
		drzava = lstDrzave.get(vrstica);
		switch (stolpec) {
			case 0: return drzava.getId_drzave();
			case 1: return drzava.getDrzava_slo();
			case 2: return drzava.getDrzava_iso();
			case 3: return drzava.getOznaka_dvomestna();
			case 4: return drzava.getOznaka_tromestna();
			case 5: return drzava.getOpomba();
			default: return "";
		}
	} // vrednost posameznega stolpca na posamezni vrstici
	
	public String getColumnName(int stolpec) {
		return naslovnaVrstica[stolpec];
	} // vrni ime stolpca

}
