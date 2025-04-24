package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import enums.Pol;
import enums.Uloga;
import model.Administrator;
import model.Agent;
import model.Turista;

public class AgentDAO implements ICRUD<Agent>{
	private ArrayList<Agent> agenti;
	private static String contextPath = "C:\\Users\\HP\\Desktop\\objektno programiranje workspace\\Projekat1\\src\\podaci\\";
	private static AgentDAO instance = null;
	
	
	public static AgentDAO  getInstance()
	{
		if(instance == null)
		{
			instance = new AgentDAO();
		}
		return instance;
	}
	public AgentDAO()
	{
		agenti = new ArrayList<>();
	}
	
		
	
	//public Korisnik(int id, String ime, String prezime, String jmbg, String adresa, String brojTelefona,
		//public Korisnik(int id, String ime, String prezime, String jmbg, String adresa, String brojTelefona,
	public void load() {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "agent.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					int id = Integer.parseInt(st.nextToken().trim());	
					
					String ime = st.nextToken().trim();
					String prezime = st.nextToken().trim();
					String jmbg = st.nextToken().trim();
					String adresa = st.nextToken().trim();
					String brojTelefona = st.nextToken().trim();
					String korisnickoIme = st.nextToken().trim();
					String lozinka = st.nextToken().trim();
					Pol pol = Pol.valueOf(st.nextToken().trim());
					Uloga uloga=Uloga.valueOf(st.nextToken().trim());
					boolean deleted = Boolean.getBoolean(st.nextToken().trim());
					
					Agent agen = new Agent(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka);
					agen.setPol(pol);
					agen.setUloga(uloga);
					agen.setDeleted(deleted);
					
					agenti.add(agen);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}

	}

	
	@Override
	public Agent create(Agent entity) {
		entity.setId(nextId());
		agenti.add(entity);
		saveToFile();
		return entity;
	}

	private int nextId()
	{
		int id = -1;
		for(Agent agent : agenti)
		{
			if(agent.getId() > id)
			{
				id = agent.getId();
			}
		}
		return id + 1;
	}
	
	@Override
	public Agent delete(Agent entity) {
		Agent agent = null;
		agent = get(entity.getId());
		if(agent == null)
		{
			return null;
		}
		agent.setDeleted(true);
		saveToFile();
		return entity;
	}

	@Override
	public Agent get(int id) {
		Agent a = null;
		for(Agent agent : agenti)
		{
			if(agent.getId() == id)
			{
				a = agent;
			}
		}
		return a;
	}

	@Override
	public ArrayList<Agent> getAll() {
		return agenti;
	}

	@Override
	public Agent update(Agent entity)
	{
		Agent oldEntity;
		for(Agent agent : agenti)
		{
			if(agent.getId() == entity.getId())
			{
				oldEntity = agent;
			}
		}
		oldEntity = entity;
		saveToFile();
		return oldEntity;
	}
	public void saveToFile() {
		try {
		      FileWriter file = new FileWriter(contextPath + "agent.txt");

		      BufferedWriter output = new BufferedWriter(file);

		      for(Agent a : agenti)
		      {
		    	  output.write(a.fileLine());
		      }
		      
		      output.close();
		      
		}     
		catch (Exception e) {
		      e.getStackTrace();
		    }
	}
	public Agent prijaviSe(String korisnickoIme, String sifra)
	{
		Agent agent = null;
		for(Agent a : agenti)
		{
			if(a.getKorisnickoIme().trim().equals(korisnickoIme.trim()) && a.getLozinka().trim().equals(sifra.trim()))
			{
				agent = a;
			}
		}
		return agent;
	}
	

	public Agent getByKorisnickoIme(String ime)
	{
		for(Agent a : agenti)
		{
			if(a.getKorisnickoIme().equals(ime))
			{
				return a;
			}
		}
		return null;
	}

	
}
