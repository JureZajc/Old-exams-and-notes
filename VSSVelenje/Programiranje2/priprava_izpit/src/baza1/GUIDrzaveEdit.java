package baza1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GUIDrzaveEdit extends JFrame {

	// gradniki GUI vmsenika
	private JPanel ploscaE;
	private JTextField txtDrzavaSLO;
	private JTextField txtDrzavaISO;
	private JTextField txtOznaka2;
	private JTextField txtOznaka3;
	private JTextArea taOpomba;
	private JButton btnShrani;
	private JButton btnPreklici;
	
	private static GUIDrzaveSeznam GUISeznam;
	private static int izbranaVrstica;
	
	private DrzaveSQL drzaveSQL; // baza
	private Drzave zapisDrzave; // drzava
	

	
	// konstruktor za plosco v oknu
	public GUIDrzaveEdit(GUIDrzaveSeznam main, int vrstica) {
		GUISeznam = main;
		izbranaVrstica = vrstica;
		setBounds(100, 100, 600, 400);
		ploscaE = new JPanel();
		ploscaE.setBorder(new EmptyBorder(5, 5, 5, 5));
		ploscaE.setLayout(new GridLayout(6, 2, 5, 5));
		setContentPane(ploscaE);
		
		// dodajanje elementov na plosco
		JLabel lblDrzavaSLO = new JLabel("Država (SLO)"); // definicija oznake
		lblDrzavaSLO.setHorizontalAlignment(SwingConstants.CENTER); // postavitev na sredino
		ploscaE.add(lblDrzavaSLO); // dodajanje na plosco
		
		txtDrzavaSLO = new JTextField(); // definicija besedilnega polja
		txtDrzavaSLO.setColumns(120); // stevilo stolpcev 120
		ploscaE.add(txtDrzavaSLO); // dodajanje na plosco

		JLabel lblDrzavaISO = new JLabel("Država (ISO)"); // definicija oznake
		lblDrzavaISO.setHorizontalAlignment(SwingConstants.CENTER); // postavitev na sredino
		ploscaE.add(lblDrzavaISO); // dodajanje na plosco

		txtDrzavaISO = new JTextField(); // definicija besedilnega polja
		txtDrzavaISO.setColumns(120); // stevilo stolpcev 120
		ploscaE.add(txtDrzavaISO); // dodajanje na plosco
		
		JLabel lblOznaka2 = new JLabel("Oznaka (2)"); // definicija oznake
		lblOznaka2.setHorizontalAlignment(SwingConstants.CENTER); // postavitev na sredino
		ploscaE.add(lblOznaka2); // dodajanje na plosco

		txtOznaka2 = new JTextField(); // definicija besedilnega polja
		txtOznaka2.setColumns(120); // stevilo stolpcev 120
		ploscaE.add(txtOznaka2); // dodajanje na plosco
		
		JLabel lblOznaka3 = new JLabel("Oznaka (3)"); // definicija oznake
		lblOznaka3.setHorizontalAlignment(SwingConstants.CENTER); // postavitev na sredino
		ploscaE.add(lblOznaka3); // dodajanje na plosco

		txtOznaka3 = new JTextField(); // definicija besedilnega polja
		txtOznaka3.setColumns(120); // stevilo stolpcev 120
		ploscaE.add(txtOznaka3); // dodajanje na plosco
		
		JLabel lblOpomba = new JLabel("Opomba"); // definicija oznake
		lblOpomba.setHorizontalAlignment(SwingConstants.CENTER); // postavitev na sredino
		ploscaE.add(lblOpomba); // dodajanje na plosco

		taOpomba = new JTextArea(); // definicija besedilne povrsine
		JScrollPane spOpomba = new JScrollPane(taOpomba); // drsno podrocje z besedilno povrsino
		ploscaE.add(spOpomba); // dodajanje na plosco
		
		JLabel lblAktivnosti = new JLabel("Aktivnosti"); // definicija oznake
		lblAktivnosti.setHorizontalAlignment(SwingConstants.CENTER); // postavitev na sredino
		ploscaE.add(lblAktivnosti); // dodajanje na plosco
		
		// gumbi
		JPanel ploscaGumbi = new JPanel(); // plosca z gumbi
		btnShrani = new JButton("Shrani"); // definicija gumba
		btnShrani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent a) {
				// shrani spremenjen zapis
				zapisDrzave.setDrzava_slo(txtDrzavaSLO.getText());
				zapisDrzave.setDrzava_iso(txtDrzavaISO.getText());
				zapisDrzave.setOznaka_dvomestna(txtOznaka2.getText());
				zapisDrzave.setOznaka_tromestna(txtOznaka3.getText());
				zapisDrzave.setOpomba(taOpomba.getText());
				// posodobi spremenjen zapis se v bazi
				drzaveSQL.updateDrzave(zapisDrzave.getDrzava_slo(), zapisDrzave.getDrzava_iso(), zapisDrzave.getOznaka_dvomestna(), 
						zapisDrzave.getOznaka_tromestna(), zapisDrzave.getOpomba(), zapisDrzave.getId_drzave());
				GUISeznam.posodobiZapis(izbranaVrstica, zapisDrzave);
				dispose(); // izhod iz okna
			}
		});
		ploscaGumbi.add(btnShrani); // dodajanje gumba na plosco
		btnPreklici = new JButton("Preklici"); // definicija gumba
		btnPreklici.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); // zapre okno
			}
		});
		ploscaGumbi.add(btnPreklici); // dodajanje gumba na plosco
		ploscaE.add(ploscaGumbi); // dodajnje plosce z gumbi na glavno plosco
	}
	
	// doloci vrerdnosti zapisDrzave iz ze znanih vrednosti v prejsnjem oknu - tabeli
	public void dolociVrednosti(Drzave drzava, DrzaveSQL drzavaSQL) {
		zapisDrzave = drzava;
		txtDrzavaSLO.setText(zapisDrzave.getDrzava_slo());
		txtDrzavaISO.setText(zapisDrzave.getDrzava_iso());
		txtOznaka2.setText(zapisDrzave.getOznaka_dvomestna());
		txtOznaka3.setText(zapisDrzave.getOznaka_tromestna());
		taOpomba.setText(zapisDrzave.getOpomba());
		this.drzaveSQL = drzavaSQL;
	}
	
	public Drzave vrniDrzava() {
		return zapisDrzave;
	}

	// zagon
	public static void main(String[] args) {
		GUIDrzaveEdit frame = new GUIDrzaveEdit(GUISeznam, izbranaVrstica);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); // sredina zaslona
	}

}
