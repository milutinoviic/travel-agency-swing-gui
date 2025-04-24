package RezervacijaTurist;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.RezervacijaController;
import controller.TuristaController;
import dao.RezervacijaDao;
import dao.TuristaDao;
import model.Rezervacija;
import view.logIn.LogIn;


public class RezervacijaTabelaTuristi extends AbstractTableModel {
	private String[] columnNames = { "ID", "Broj putnika", "Datum kreacije", "Turista", "Aranžman", "Agent", "Status", "Cena"};
	private ArrayList<Rezervacija> rezervacije;
	private RezervacijaController rezervacijaController;
	public TuristaController turistaController;

	public RezervacijaTabelaTuristi() {
		super();
		rezervacijaController = new RezervacijaController(RezervacijaDao.getInstance());
		turistaController = new TuristaController(TuristaDao.getInstance());
		rezervacije = new ArrayList<>();
		
		for(Rezervacija r : rezervacijaController.getAll())
		{
			if(!r.isDeleted() && r.getKupac().getId() == LogIn.ulogovanKorisnik.getId())
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
			if(!r.isDeleted() && r.getKupac().getId() == LogIn.ulogovanKorisnik.getId())
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
		case 7:
			return rezervacija.getAranzman().getCijena();
		}

		return null;
	}

}
