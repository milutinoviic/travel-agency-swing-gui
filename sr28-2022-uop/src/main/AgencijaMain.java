package main;


import dao.AdministratorDao;
import dao.AgentDAO;
import dao.AranzmanDao;
import dao.RezervacijaDao;
import dao.TuristaDao;
import model.Agent;
import view.logIn.LogIn;

public class AgencijaMain {


	public static void main(String[] args)
	{		
		AdministratorDao.getInstance().load();
		AgentDAO.getInstance().load();
		TuristaDao.getInstance().load();
		AranzmanDao.getInstance().load();
		RezervacijaDao.getInstance().load();
		

		
		RezervacijaDao.getInstance().BindAgent(AgentDAO.getInstance());
		AranzmanDao.getInstance().BindAgent(AgentDAO.getInstance());
		RezervacijaDao.getInstance().BindAranzman(AranzmanDao.getInstance());
		RezervacijaDao.getInstance().BindTurist(TuristaDao.getInstance());
		
		LogIn ulogujSe = new LogIn();
	}
	
	
}
