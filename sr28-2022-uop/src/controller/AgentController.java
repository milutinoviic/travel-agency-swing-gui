package controller;

import java.util.ArrayList;

import dao.AgentDAO;
import model.Administrator;
import model.Agent;
import model.Turista;

public class AgentController {
	private AgentDAO agentDAO;
	
	public AgentController(AgentDAO agentDao)
	{
		this.agentDAO = agentDao;
	}
	
	public Agent get(int id)
	{
		return agentDAO.get(id);
	}
	public Agent update(Agent entity) {
		
		return agentDAO.update(entity);	
	}
	public Agent create(Agent entity)
	{
		return agentDAO.create(entity);
	}
	public Agent delete(Agent entity)
	{
		return agentDAO.delete(entity);
	}
	public Agent prijaviSe(String korisnickoIme, String sifra) {
		return agentDAO.prijaviSe(korisnickoIme, sifra);
	}
	public ArrayList<Agent> getAll()
	{
		return agentDAO.getAll();
	}
	
	public Agent getByKorisnickoIme(String ime)
	{
		return agentDAO.getByKorisnickoIme(ime);
	}

}
