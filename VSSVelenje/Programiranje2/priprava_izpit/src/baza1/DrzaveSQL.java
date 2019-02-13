package baza1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

// razred za povezavo z bazo
public class DrzaveSQL {
	// podatki pomembni za povezavo z bazo
	static final String DB_URL = "jdbc:postgresql://localhost:5432/poste"; // vrsta povezave in baze ter URL naslov baze
	static final String DB_USR = "postgres"; // uporabnik podatkovne baze
	static final String DB_PWD = "postgres"; // geslo podatkovne baze
	private Connection povezavaDB = null; // povezava z bazo
	private PreparedStatement selectDrzaveVsi = null; // select vseh vnosov v tabeli Drzave
	private PreparedStatement selectDrzaveOznaka = null; // select drzav glede na oznako
	private PreparedStatement selectIdDrzave = null; // id drzave glede na vrednosti
	private PreparedStatement selectDrzavaSPID; // izberi drzavo glede na id
	private PreparedStatement insertDrzave = null; // insert tabela drzave
	private PreparedStatement updateDrzave = null; // update tabela drzave
	private PreparedStatement deleteDrzave = null; // delete tabela drzave - brisanje vnosov
	
	public DrzaveSQL() {
		try {
			povezavaDB = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
			selectDrzaveVsi = povezavaDB.prepareStatement("SELECT id_drzave, drzava_slo, drzava_iso, oznaka_dvomestna, oznaka_tromestna, opomba FROM drzave");
			selectDrzaveOznaka = povezavaDB.prepareStatement("SELECT id_drzave, drzava_slo, drzava_iso, oznaka_dvomestna, oznaka_tromestna, opomba FROM drzave"
					+ " WHERE oznaka_tromestna LIKE ?");
			selectIdDrzave = povezavaDB.prepareStatement("SELECT id_drzave FROM drzave WHERE drzava_slo = ? AND oznaka_tromestna = ?");
			selectDrzavaSPID = povezavaDB.prepareStatement("SELECT id_drzave, drzava_slo, drzava_iso, oznaka_dvomestna, oznaka_tromestna, opomba FROM drzave"
					+ " WHERE id_drzave = ?");
			insertDrzave = povezavaDB.prepareStatement("INSERT INTO drzave(drzava_slo, drzava_iso, oznaka_dvomestna, oznaka_tromestna, opomba)"
					+ "VALUES (?, ?, ?, ?, ?)");
			updateDrzave = povezavaDB.prepareStatement("UPDATE drzave SET "
					+ "drzava_slo = ?, " 
					+ "drzava_iso = ?, "
					+ "oznaka_dvomestna = ?, "
					+ "oznaka_tromestna = ?, "
					+ "opomba = ? "
					+ "WHERE id_drzave = ?");
			deleteDrzave = povezavaDB.prepareStatement("DELETE FROM drzave WHERE id_drzave = ?");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1); // izhod v primeru napake
		}
	} // konstruktor
	
	// vrne seznam vseh drzav iz DB, elementi so tipa razreda Drzave
	public List<Drzave> getDrzaveVsi() {
		List<Drzave> lstDrzave = null;
		ResultSet rsDrzave =  null;
		try {
			rsDrzave = selectDrzaveVsi.executeQuery(); // hranjenje vrednosti v bazi v rezultat
			lstDrzave = new ArrayList<Drzave>(); // definicija seznama
			// polnenje seznama z rezultati iz poizvedbe preko while zanke - dokler so rezultati se polni
			while(rsDrzave.next()) {
				lstDrzave.add(new Drzave(rsDrzave.getInt("id_drzave"), rsDrzave.getString("drzava_slo"), rsDrzave.getString("drzava_iso"), 
						rsDrzave.getString("oznaka_dvomestna"), rsDrzave.getString("oznaka_tromestna"), rsDrzave.getString("opomba")));
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				rsDrzave.close(); // zapiranje rezultata poizvedbe
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lstDrzave; // vracanje seznama
	} // getDrzaveVsi - konec
	
	// vrne seznam drzav z doloceno oznako
	public List<Drzave> getDrzaveKriterij(String oznaka) {
		List<Drzave> lstDrzave = null;
		ResultSet rsDrzave =  null;
		try {
			selectDrzaveOznaka.setString(1, "%" + oznaka); // nastavljanje vrednosti pogoja
			rsDrzave = selectDrzaveOznaka.executeQuery(); // shrani rezultate poizvede
			lstDrzave = new ArrayList<Drzave>(); // ustvari seznam
			// polnenje seznama z rezultati iz poizvedbe preko while zanke - dokler so rezultati se polni
			while(rsDrzave.next()) {
				lstDrzave.add(new Drzave(rsDrzave.getInt("id_drzave"), rsDrzave.getString("drzava_slo"), rsDrzave.getString("drzava_iso"), 
						rsDrzave.getString("oznaka_dvomestna"), rsDrzave.getString("oznaka_tromestna"), rsDrzave.getString("opomba")));
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				rsDrzave.close(); // zapiranje rezultata poizvedbe
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lstDrzave; // vracanje seznama
	 } // getDrzaveKriterij - konec
	
	public int getIdDrzave(String drzava_slo, String oznaka_tromestna) {
		int id = 0;
		ResultSet rsId = null;
		try {
			selectIdDrzave.setString(1, drzava_slo);
			selectIdDrzave.setString(2, oznaka_tromestna);
			rsId = selectIdDrzave.executeQuery();
			while(rsId.next()) {
				id = rsId.getInt("id_drzave");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				rsId.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return id;
	}
	
	public Drzave getDrzavePrekoID(int id_drzave) {
		Drzave drzava = new Drzave();
		ResultSet rsDrzava = null;
		try {
			selectDrzavaSPID.setInt(1, id_drzave);
			rsDrzava = selectDrzavaSPID.executeQuery();
			while(rsDrzava.next()) {
				drzava.setId_drzave(rsDrzava.getInt("id_drzave"));
				drzava.setDrzava_slo(rsDrzava.getString("drzava_slo"));
				drzava.setDrzava_iso(rsDrzava.getString("drzava_iso"));
				drzava.setOznaka_dvomestna(rsDrzava.getString("oznaka_dvomestna"));
				drzava.setOznaka_tromestna(rsDrzava.getString("oznaka_tromestna"));
				drzava.setOpomba(rsDrzava.getString("opomba"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				rsDrzava.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return drzava;
	}
	
	// vstavlanje novega zapisa v tabelo drzave
	public int insertDrzave(String drzava_slo, String drzava_iso, String oznaka_dvomestna, String oznaka_tromestna, String opomba) {
		int rezultat = 0; // rezultat poizvedbe
		try {
			povezavaDB.setAutoCommit(false);
			// dolocanje vrednosti parametrov
			insertDrzave.setString(1, drzava_slo);
			insertDrzave.setString(2, drzava_iso);
			insertDrzave.setString(3, oznaka_dvomestna);
			insertDrzave.setString(4, oznaka_tromestna);
			insertDrzave.setString(5, opomba);	
			// zagon update query oz. poizvedbe
			rezultat = insertDrzave.executeUpdate();
			povezavaDB.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				povezavaDB.rollback(); // ob napaki povrne bazo v prejsnje stanje
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} // try_catch
		return rezultat;
	} // vstavljanje v drzave

	// posodabljanje vnosov v tabeli drzave
	public int updateDrzave(String drzava_slo, String drzava_iso, String oznaka_dvomestna, String oznaka_tromestna, String opomba, int id_drzave) {
		int rezultat = 0;
		try {
			povezavaDB.setAutoCommit(false);	
			// dolocanje vrednosti parametrov
			updateDrzave.setString(1, drzava_slo);
			updateDrzave.setString(2, drzava_iso);
			updateDrzave.setString(3, oznaka_dvomestna);
			updateDrzave.setString(4, oznaka_tromestna);
			updateDrzave.setString(5, opomba);	
			updateDrzave.setInt(6, id_drzave); // pogoj
			// zagon update query oz. poizvedbe
			rezultat = updateDrzave.executeUpdate();
			povezavaDB.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				povezavaDB.rollback(); // ob napaki povrne bazo v prejsnje stanje
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} // try_catch
		return rezultat;
	} // posodabljanje tabele drzave
	
	// brisanje iz tabele drzave
	public int deleteDrzave(int id_drzave) {
		int rezultat = 0;
		try {
			povezavaDB.setAutoCommit(false);
			deleteDrzave.setInt(1, id_drzave); // pogoji
			// zagon delete query poizvedbe
			rezultat = deleteDrzave.executeUpdate();
			povezavaDB.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rezultat;
	} // brisanje iz tabele drzave
	
	// zapiranje povezave z bazo
	public void closeDB() {
		try {
			povezavaDB.close(); // zapre povezavo z bazo
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // closeDB - konec
	
}
