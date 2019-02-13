package baza1;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class TableMouseListener extends MouseAdapter {

	private JTable tabela;
	
	public TableMouseListener(JTable tbl) {
		this.tabela = tbl;
	}// konstruktor
	
	@Override
	public void mousePressed(MouseEvent e) {
		Point tocka = e.getPoint(); // mesto klika
		int trenutnaVrstica = tabela.rowAtPoint(tocka); // mesto vrstice na tocki
		tabela.setRowSelectionInterval(trenutnaVrstica, trenutnaVrstica); // izbor vrstice
	}
}
