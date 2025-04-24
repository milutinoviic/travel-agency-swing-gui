package controller;

import java.util.ArrayList;

import dao.TuristaDao;

import model.Turista;

public class TuristaController {
private TuristaDao turistaDao;
	
	public TuristaController(TuristaDao turistaDao)
	{
		this.turistaDao = turistaDao;
	}
	
	public Turista get(int id)
	{
		return turistaDao.get(id);
	}
	public Turista update(Turista entity) {
		
		return turistaDao.update(entity);	
	}
	public Turista create(Turista entity)
	{
		return turistaDao.create(entity);
	}
	public Turista delete(Turista entity)
	{
		return turistaDao.delete(entity);
	}
	
	public Turista prijaviSe(String korisnickoIme, String sifra)
	{
		return turistaDao.prijaviSe(korisnickoIme, sifra);
	}
	public ArrayList<Turista> getAll()
	{
		return turistaDao.getAll();
	}

	public Turista getByKorisnickoIme(String ime)
	{
		return turistaDao.getByKorisnickoIme(ime);
	}
	
}
