package baza1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class GUIDrzaveSeznam extends JFrame {
	// gradniki GUI vmesnika
	private JPanel plosca;
	private JTable tblDrzave;
	private DrzaveSQL drzaveSQL; // drzave v bazi
	private TableModelDrzave tmDrzave; // model za tabelo drzave
	private List<Drzave> lstDrzave; // seznam drzav
	private JScrollPane scrDrzave; // drsno podrocje za tabelo drzave
	private TableRowSorter<TableModelDrzave> trsDrzave; // razvrscanje vrstic
	private JPopupMenu popupMenu; // vsebinski meni ob desnem kliku
	private JMenuItem menuItemIzbrisi; // element v meniju
	private JMenuItem menuItemDodaj; // element v meniju
	private static GUIDrzaveSeznam frame; // okno v statièni obliki, da se lahko uporablja tudi v konstrukturju in drugih oknih

	

	// konstruktor za ustvarjanje vsebine plosce
	public GUIDrzaveSeznam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		plosca = new JPanel();
		plosca.setBorder(new EmptyBorder(5, 5, 5, 5));
		plosca.setLayout(new BorderLayout(0, 0));
		setContentPane(plosca);
		
		// dodajanje vrednosti v seznam
		drzaveSQL = new DrzaveSQL();
		lstDrzave = drzaveSQL.getDrzaveVsi();
		tmDrzave = new TableModelDrzave(lstDrzave);
		
		// tabela
		tblDrzave = new JTable(tmDrzave); // tabela napolnjena iz vrednosti v seznamu oz. modelu tabele
		tblDrzave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ob dvokliku odpri novo okno in posreduj vrednosti
				if(e.getClickCount() == 2) {
					// poisce indeks izbranega objekta v tabeli in ga pretvori v indeks v modelu za tabelo - zaradi sortinga nista ista
					int izbranaVrstica = tblDrzave.convertRowIndexToModel(tblDrzave.getSelectedRow());
					// poslje potrebne podatke na naslednje okno
					GUIDrzaveEdit editOkno = new GUIDrzaveEdit(frame, izbranaVrstica); // okno za urejanje
										
					/* skriti stolpec z idjem se uporabi za iskanje v bazi za drzavo */
					int izbraniId = (int) tblDrzave.getValueAt(tblDrzave.getSelectedRow(), 0);
					Drzave izbranaDrzava = drzaveSQL.getDrzavePrekoID(izbraniId);
					editOkno.dolociVrednosti(izbranaDrzava, drzaveSQL); // posreduj vrednosti
					editOkno.setVisible(true); // prikaz novega okna za urejanje
				}
			}
		});
		tblDrzave.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // nacin izbire elementov v tabeli
		trsDrzave = new TableRowSorter<TableModelDrzave>(tmDrzave); // razvrscanje vrstic
		tblDrzave.setRowSorter(trsDrzave); // nastavi razvrsacnje vrstic
		
		// skrije vrstico z IDjem oz. prvo vrstico
		tblDrzave.getColumnModel().getColumn(0).setMinWidth(0);
		tblDrzave.getColumnModel().getColumn(0).setMaxWidth(0);
		
		scrDrzave = new JScrollPane(tblDrzave); // drsno podrocje s tabelo
		plosca.add(scrDrzave); // drsno podrocje doda na plosco
		
		// vsebinski meni
		popupMenu = new JPopupMenu();
		menuItemIzbrisi = new JMenuItem("Izbriši"); // nov element menija
		menuItemIzbrisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent a) {
				JMenuItem menuItem = (JMenuItem) a.getSource();
				if(menuItem == menuItemIzbrisi) {					
					// poisce indeks izbranega objekta v tabeli in ga pretvori v indeks v modelu za tabelo - zaradi sortinga nista ista
					int izbranaVrstica = tblDrzave.convertRowIndexToModel(tblDrzave.getSelectedRow());
					// poisce id elementa v seznamu preko indeksa
					drzaveSQL.deleteDrzave(lstDrzave.get(izbranaVrstica).getId_drzave()); // brisanje iz baze preko IDja
					lstDrzave.remove(izbranaVrstica); // zbrise objekt drzave iz seznama s podanim indeksom
					tmDrzave.fireTableDataChanged(); // posodablanje modela tabele in s tem tudi tabele
				}
				
			}
		});
		popupMenu.add(menuItemIzbrisi); // doda element v meni
		
		menuItemDodaj = new JMenuItem("Dodaj");
		menuItemDodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JMenuItem menuItem = (JMenuItem) e.getSource();
				if(menuItem == menuItemDodaj) {
					// za posodabljanje tabele na glavnem oknu, mora novo okno imeti dostop do glavnega okna
					GUIDrzaveAdd addOkno = new GUIDrzaveAdd(frame); // glavno okno pošlje na drugo okno
					addOkno.setVisible(true);
				}
				
			}
		});
		popupMenu.add(menuItemDodaj);
		
		// dodajanje vsebinskega menija tabeli
		tblDrzave.setComponentPopupMenu(popupMenu);
		tblDrzave.addMouseListener(new TableMouseListener(tblDrzave));
		
		
	}
	
	// posodabljanje celotne tabele
	public void posodobiTabelo(List<Drzave> list) {
		this.tblDrzave.setRowSorter(new TableRowSorter<TableModelDrzave>(new TableModelDrzave(list)));
		this.tblDrzave.setModel(new TableModelDrzave(list));
		// zaradi novega modela za tabelo se pokaze vrstica z idjem in jo zato skrijemo
		this.tblDrzave.getColumnModel().getColumn(0).setMinWidth(0);
		this.tblDrzave.getColumnModel().getColumn(0).setMaxWidth(0);
	}
	
	// posodobi zapis v tabeli
	public void posodobiZapis(int izbranaVrstica, Drzave drzava) {
		this.lstDrzave.set(izbranaVrstica, drzava); // posodobi vnos v seznamu
		this.tmDrzave.fireTableRowsUpdated(izbranaVrstica, izbranaVrstica); // posodobi vrstico v modelu
	}
	
	public static void main(String[] args) {
		frame = new GUIDrzaveSeznam();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}
