package RezervacijaAgent;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.AgentController;
import controller.AranzmanController;
import controller.RezervacijaController;
import dao.AgentDAO;
import dao.AranzmanDao;
import dao.RezervacijaDao;

import model.Aranzman;
import model.Rezervacija;
import view.logIn.LogIn;


public class RezervacijaAgentTabela extends AbstractTableModel {
	private String[] columnNames = { "ID", "Broj putnika", "Datum kreiranja", "Turista", "Aranžman", "Agent", "Status", "Cijena"};
	private ArrayList<Rezervacija> rezervacije;
	
	public AranzmanController aranzmanController;
	public AgentController agentController;
	public RezervacijaController rezervacijaController;


	public RezervacijaAgentTabela() {
		rezervacijaController = new RezervacijaController(RezervacijaDao.getInstance());
		agentController = new AgentController(AgentDAO.getInstance());
		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		
		
		rezervacijaController = rezervacijaController;
//		System.out.println(rezervacijaController);
		rezervacije = new ArrayList<>();
		
		for(Rezervacija r : rezervacijaController.getAll())
		{
			if(!r.isDeleted() && proveri(r))
			{
				rezervacije.add(r);
			}
		}

	}

	private boolean proveri(Rezervacija r)
	{
		for(Aranzman a : aranzmanController.getAll())
		{
			if(a.getProdavac().getId() == LogIn.ulogovanKorisnik.getId())
			{
				if(r.getAranzman().getId() == a.getId())
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void refresh()
	{
		rezervacije.clear();
		for(Rezervacija r : rezervacijaController.getAll())
		{
			if(!r.isDeleted() && proveri(r))
			{
				
				rezervacije.add(r);
			}
		}
			
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
