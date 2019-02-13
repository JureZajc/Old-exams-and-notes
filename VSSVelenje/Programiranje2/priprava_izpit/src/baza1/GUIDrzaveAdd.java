package baza1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GUIDrzaveAdd extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// gradniki GUI vmsenika
	private JPanel ploscaAdd;
	private JTextField txtDrzavaSLO;
	private JTextField txtDrzavaISO;
	private JTextField txtOznaka2;
	private JTextField txtOznaka3;
	private JTextArea taOpomba;
	private JButton btnShrani;
	private JButton btnPreklici;
	private static GUIDrzaveSeznam GUIseznam; // glavno okno
	
	private DrzaveSQL drzaveSQL; // baza
	

	// konstruktor za GUI vmesnik
	public GUIDrzaveAdd(GUIDrzaveSeznam main) {
		GUIseznam = main;
		setBounds(100, 100, 450, 300);
		ploscaAdd = new JPanel();
		ploscaAdd.setBorder(new EmptyBorder(5, 5, 5, 5));
		ploscaAdd.setLayout(new GridLayout(6, 2, 5, 5));
		setContentPane(ploscaAdd);

		JLabel lblDrzavaSLO = new JLabel("Država SLO"); // definicija oznake
		lblDrzavaSLO.setHorizontalAlignment(SwingConstants.CENTER); // postavitev na sredino stolpca
		ploscaAdd.add(lblDrzavaSLO); // dodajanje na plosco
		
		txtDrzavaSLO = new JTextField(); // prazno besedilno polje
		txtDrzavaSLO.setColumns(120); // stevilo stolpcev
		ploscaAdd.add(txtDrzavaSLO); // dodajanje na plosco
		
		JLabel lblDrzavaISO = new JLabel("Država ISO"); // definicija oznake
		lblDrzavaISO.setHorizontalAlignment(SwingConstants.CENTER); // postavitev na sredino stolpca
		ploscaAdd.add(lblDrzavaISO); // dodajanje na plosco
		
		txtDrzavaISO = new JTextField(); // prazno besedilno polje
		txtDrzavaISO.setColumns(120); // stevilo stolpcev
		ploscaAdd.add(txtDrzavaISO); // dodajanje na plosco
		
		JLabel lblOznaka2 = new JLabel("Oznaka (2)"); // definicija oznake
		lblOznaka2.setHorizontalAlignment(SwingConstants.CENTER); // postavitev na sredino stolpca
		ploscaAdd.add(lblOznaka2); // dodajanje na plosco
		
		txtOznaka2 = new JTextField(); // prazno besedilno polje
		txtOznaka2.setColumns(120); // stevilo stolpcev
		ploscaAdd.add(txtOznaka2); // dodajanje na plosco
		
		JLabel lblOznaka3 = new JLabel("Oznaka (3)"); // definicija oznake
		lblOznaka3.setHorizontalAlignment(SwingConstants.CENTER); // postavitev na sredino stolpca
		ploscaAdd.add(lblOznaka3); // dodajanje na plosco
		
		txtOznaka3 = new JTextField(); // prazno besedilno polje
		txtOznaka3.setColumns(120); // stevilo stolpcev
		ploscaAdd.add(txtOznaka3); // dodajanje na plosco
		
		JLabel lblOpomba = new JLabel("Opomba"); // definicija oznake
		lblOpomba.setHorizontalAlignment(SwingConstants.CENTER); // postavitev na sredino stolpca
		ploscaAdd.add(lblOpomba); // dodajanje na plosco
		
		taOpomba = new JTextArea(); // prazna besedilna povrsina
		taOpomba.setColumns(120); // stevilo stolpcev
		ploscaAdd.add(taOpomba); // dodajanje na plosco
		
		JPanel ploscaGumbi = new JPanel(); // plosca z gumbi
		
		btnShrani = new JButton("Shrani");
		btnShrani.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				drzaveSQL =  new DrzaveSQL();
				// insert vrednosti v besedilnih poljih kot vrednosti za tabelo v bazi - nov vnos v drzave
				drzaveSQL.insertDrzave(txtDrzavaSLO.getText(), txtDrzavaISO.getText(), txtOznaka2.getText(), txtOznaka3.getText(), taOpomba.getText());
				List<Drzave> temp = drzaveSQL.getDrzaveVsi();
				GUIseznam.posodobiTabelo(temp); // posodobi tabelo zaradi novega vnosa na glavnem oknu
				dispose(); // zapre okno in njegove resurse
			}
		});
		ploscaGumbi.add(btnShrani); // doda gumb na plsoco za gumbe
		
		btnPreklici = new JButton("Preklièi");
		btnPreklici.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); // zapre okno in vse njegove resurse
			}
		});
		ploscaGumbi.add(btnPreklici); // doda gumb na plosco za gumbe
		ploscaAdd.add(ploscaGumbi); // plosca z gumbi dodana na glavno plosco
		
	}
	
	// zagon
	public static void main(String[] args) {
		GUIDrzaveAdd frame = new GUIDrzaveAdd(GUIseznam);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); // postavitev na sredino zaslona
	} // main
}
