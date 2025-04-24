package controller;

import java.util.ArrayList;

import dao.AdministratorDao;
import model.Administrator;


public class AdministratorController {
	private AdministratorDao AdministratorDao;
	
	public AdministratorController(AdministratorDao AdministratorDao)
	{
		this.AdministratorDao = AdministratorDao;
	}
	
	public Administrator get(int id)
	{
		return AdministratorDao.get(id);
	}
	public Administrator update(Administrator entity) {
		
		return AdministratorDao.update(entity);	
	}
	public Administrator create(Administrator entity)
	{
		return AdministratorDao.create(entity);
	}
	public Administrator delete(Administrator entity)
	{
		return AdministratorDao.delete(entity);
	}
	
	public Administrator prijaviSe(String korisnickoIme, String sifra)
	{
		return AdministratorDao.prijaviSe(korisnickoIme, sifra);
	}
	public ArrayList<Administrator> getAll()
	{
		return AdministratorDao.getAll();
	}

}
