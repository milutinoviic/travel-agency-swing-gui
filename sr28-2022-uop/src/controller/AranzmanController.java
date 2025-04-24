package controller;

import java.util.ArrayList;

import dao.AranzmanDao;
import model.Aranzman;

public class AranzmanController {
private AranzmanDao aranzmanDao;
	
	public AranzmanController(AranzmanDao aranzmanDao)
	{
		this.aranzmanDao = aranzmanDao;
	}
	
	public ArrayList<Aranzman> getAll()
	{
		return aranzmanDao.getAll();
	}
	
	public ArrayList<Aranzman> getAllActive(){
		return aranzmanDao.getAllActive();
	}
	
	public Aranzman get(int id)
	{
		return aranzmanDao.get(id);
	}
	public Aranzman update(Aranzman entity) {
		
		return aranzmanDao.update(entity);	
	}
	public Aranzman create(Aranzman entity)
	{
		return aranzmanDao.create(entity);
	}
	public Aranzman delete(Aranzman entity)
	{
		return aranzmanDao.delete(entity);
	}


}
