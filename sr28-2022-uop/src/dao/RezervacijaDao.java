package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.StringTokenizer;

import enums.Status;
import enums.TipAranzmana;
import model.Administrator;
import model.Agent;
import model.Aranzman;
import model.Rezervacija;
import model.Turista;
import util.DateHelper;


public class RezervacijaDao implements ICRUD<Rezervacija>{
	private ArrayList<Rezervacija> rezervacije;
	private static String contextPath = "C:\\\\Users\\\\HP\\\\Desktop\\\\objektno programiranje workspace\\\\Projekat1\\\\src\\\\podaci\\\\";
    private static RezervacijaDao instance = null;
    
	
	
	public static RezervacijaDao getInstance()
	{
		if(instance == null)
		{
			instance = new RezervacijaDao();
		}
		return instance;
	}
	public RezervacijaDao()
	{
		rezervacije = new ArrayList<>();
	}

	public void BindTurist(TuristaDao turistDao)
	{
		for(Rezervacija r : rezervacije)
		{
			r.setKupac(turistDao.get(r.getKupac().getId()));
		}
	}
	public void BindAgent(AgentDAO agentDao)
	{
		for(Rezervacija r : rezervacije)
		{
			r.setProdavac(agentDao.get(r.getProdavac().getId()));
		}
	}
	public void BindAranzman(AranzmanDao aranzmanDao)
	{
		for(Rezervacija r : rezervacije)
		{
			r.setAranzman(aranzmanDao.get(r.getAranzman().getId()));
		}
	}
	
	
	public void load() {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "rezervacija.txt");
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
					int idKupca = Integer.parseInt(st.nextToken().trim());
					int idProdavca = Integer.parseInt(st.nextToken().trim());
					int brojPutnika = Integer.parseInt(st.nextToken().trim());
					int idAranzmana = Integer.parseInt(st.nextToken().trim());
					Status status = Status.valueOf(st.nextToken().trim());
					LocalDate datum = DateHelper.stringToDate(st.nextToken());
					double cena = Double.parseDouble(st.nextToken());
					
					Turista k = new Turista();
					k.setId(idKupca);
					
					Agent a = new Agent();
					a.setId(idProdavca);
					
					Aranzman araz=new Aranzman();
					araz.setId(idAranzmana);
					
					
					rezervacije.add(new Rezervacija(id,k,a,brojPutnika,araz,status,datum ));
					
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
	private int nextId()
	{
		int id = 0;
		for(Rezervacija rezervacija : rezervacije)
		{
			if(rezervacija.getId() > id)
			{
				id = (int) rezervacija.getId();
			}
		}
		return id + 1;
	}
	
	@Override
	public Rezervacija create(Rezervacija entity) {
		entity.setId(nextId());
		rezervacije.add(entity);
		saveToFile();
		return entity;
	}
	
	@Override
	public Rezervacija delete(Rezervacija entity) {
		Rezervacija r = null;
		r = get(entity.getId());
		if(r == null)
		{
			return null;
		}
		r.setDeleted(true);
		saveToFile();
		return entity;
	}
	
	@Override
	public Rezervacija get(int id) {
		Rezervacija r = null;
		for(Rezervacija rez : rezervacije)
		{
			if(rez.getId() == id)
			{
				r = rez;
			}
		}
		return r;
	}
	@Override
	public ArrayList<Rezervacija> getAll() {
		// TODO Auto-generated method stub
		return rezervacije;
	}
	@Override
	public Rezervacija update(Rezervacija entity) {
		Rezervacija oldEntity;
		for(Rezervacija rez : rezervacije)
		{
			if(rez.getId() == entity.getId())
			{
				oldEntity = rez;
			}
		}
		oldEntity = entity;
		saveToFile();
		return oldEntity;
	}
	public void saveToFile() {
		try {
		      FileWriter file = new FileWriter(contextPath + "rezervacija.txt");

		      BufferedWriter output = new BufferedWriter(file);

		      for(Rezervacija r : rezervacije)
		      {
		    	  output.write(r.fileToLine());
		      }
		      
		      output.close();
		      
		}     
		catch (Exception e) {
		      e.getStackTrace();
		    }
	}

	public double calcCena(Rezervacija r)
	{
		return r.getBrojPutnika() * r.getAranzman().getCijena() * r.getAranzman().getBrojDana() * r.getAranzman().getSajamskiPopust() / 100;
	}
	
}
