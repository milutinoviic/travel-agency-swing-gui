package RezervacijaView;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.RezervacijaController;

import model.Rezervacija;

public class RezervacijaTabela extends AbstractTableModel {
	private String[] columnNames = { "ID", "Broj putnika", "Datum kreiranja", "Turista", "Aranžman", "Agent", "Status"};
	private ArrayList<Rezervacija> rezervacije;
	private RezervacijaController rezervacijaController;

	public RezervacijaTabela() {
		super();
		rezervacijaController = rezervacijaController;
		System.out.println(rezervacijaController);
		rezervacije = new ArrayList<>();
		
		for(Rezervacija r : rezervacijaController.getAll())
		{
			if(!r.isDeleted())
			{
				rezervacije.add(r);
			}
		}
			
		
	}

	public void refresh()
	{
		rezervacije.clear();
		for(Rezervacija r : rezervacijaController.getAll())
		{
			if(!r.isDeleted())
			{
				rezervacije.add(r);
			}
		}
		System.out.println(rezervacije.size());
		fireTableDataChanged();
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return rezervacije.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Rezervacija getRezervacija(int row)
	{
		return rezervacije.get(row);
	}
	

	public Object getValueAt(int row, int col) {
		Rezervacija rezervacija = rezervacije.get(row);

		switch (col) {
		case 0:
			return rezervacija.getId();
		case 1:
			return rezervacija.getBrojPutnika();
		case 2:
			return rezervacija.getDatumKreiranja();
		case 3: 
			return rezervacija.getKupac().getKorisnickoIme();
		case 4:
			return rezervacija.getAranzman().getId();
		case 5:
			return rezervacija.getProdavac().getKorisnickoIme();
		case 6: 
			return rezervacija.getStatus();
		
		}

		return null;
	}

}
