package KorisnikPregled;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.AdministratorController;
import controller.AgentController;
import controller.AranzmanController;
import controller.RezervacijaController;
import controller.TuristaController;
import dao.AdministratorDao;
import dao.AgentDAO;
import dao.AranzmanDao;
import dao.RezervacijaDao;
import dao.TuristaDao;
import model.Administrator;
import model.Agent;
import model.Korisnik;
import model.Turista;

public class KorisnikTabela extends AbstractTableModel {
	public TuristaController turistaController;
	public AranzmanController aranzmanController;
	public AgentController agentController;
	public RezervacijaController rezervacijaController;
	public AdministratorController administratorController;
	
	private String[] columnNames = { "ID", "Ime", "Prezime", "JMBG", "Adresa", "Telefon", "Korisničko ime", "Lozinka", "Pol", "Uloga" };
	private ArrayList<Korisnik> korisnici;

	public KorisnikTabela() {

		rezervacijaController = new RezervacijaController(RezervacijaDao.getInstance());
		agentController = new AgentController(AgentDAO.getInstance());
		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		turistaController = new TuristaController(TuristaDao.getInstance());
		administratorController=new AdministratorController(AdministratorDao.getInstance());
		korisnici = new ArrayList<>(administratorController.getAll());
		korisnici.addAll(turistaController.getAll());
		korisnici.addAll(agentController.getAll());
		
		ArrayList<Korisnik> izbaci = new ArrayList<>();
		for(Korisnik k : korisnici)
		{
			if(k.getDeleted())
			{
				izbaci.add(k);
			}
		}
		korisnici.removeAll(izbaci);
		
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public void updateUsers()
	{
		korisnici = new ArrayList<>(administratorController.getAll());
		korisnici.addAll(turistaController.getAll());
		korisnici.addAll(agentController.getAll());
		
		ArrayList<Korisnik> izbaci = new ArrayList<>();
		for(Korisnik k : korisnici)
		{
			if(k.getDeleted())
			{
				izbaci.add(k);
			}
		}
		korisnici.removeAll(izbaci);
	}
	
	public int getRowCount() {
		return korisnici.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Korisnik korisnik = korisnici.get(row);

		switch (col) {
		case 0:
			return korisnik.getId();
		case 1:
			return korisnik.getIme();
		case 2:
			return korisnik.getPrezime();
		case 3:
			return korisnik.getJmbg();
		case 4: 
			return korisnik.getAdresa();
		case 5:
			return korisnik.getBrojTelefona();
		case 6:
			return korisnik.getKorisnickoIme();
		case 7:
			return korisnik.getLozinka();
		case 8:
			return korisnik.getPol();
		case 9:
			return korisnik.getUloga();
		}

		return new String();
	}
	
	public Korisnik getUser(int row)
	{
		return korisnici.get(row);
	}
	
	public void izbaci(Korisnik k)
	{
		korisnici.remove(k);
		if(k instanceof Administrator)
		{
			administratorController.delete((Administrator)k);
		}
		else if(k instanceof Agent)
		{
			agentController.delete((Agent)k);
		}
		else
		{
			turistaController.delete((Turista)k);
		}
	
		
	}
	

}
