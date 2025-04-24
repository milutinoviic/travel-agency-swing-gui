package controller;

import java.util.ArrayList;

import dao.RezervacijaDao;
import model.Rezervacija;

public class RezervacijaController {
 private RezervacijaDao rezervacijaDao;
	
	public RezervacijaController(RezervacijaDao rezervacijaDao)
	{
		this.rezervacijaDao = rezervacijaDao;
	}
	
	public Rezervacija get(int id)
	{
		return rezervacijaDao.get(id);
	}
	public Rezervacija update(Rezervacija entity) {
		
		return rezervacijaDao.update(entity);	
	}
	public Rezervacija create(Rezervacija entity)
	{
		return rezervacijaDao.create(entity);
	}
	public Rezervacija delete(Rezervacija entity)
	{
		return rezervacijaDao.delete(entity);
	}

	public ArrayList<Rezervacija> getAll()
	{
		return rezervacijaDao.getAll();
	}
	
	public double calcCena(Rezervacija r)
	{
		return rezervacijaDao.calcCena(r);
	}
	
}
