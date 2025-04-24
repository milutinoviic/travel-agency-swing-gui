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

import enums.TipAranzmana;
import enums.TipSmjestaja;
import model.Administrator;
import model.Agent;
import model.Aranzman;
import model.Korisnik;
import model.Rezervacija;
import util.DateHelper;

public class AranzmanDao implements ICRUD<Aranzman> {
	private ArrayList<Aranzman> aranzmani;
	private static String contextPath = "C:\\\\Users\\\\HP\\\\Desktop\\\\objektno programiranje workspace\\\\Projekat1\\\\src\\\\podaci\\\\";
    private static AranzmanDao instance = null;
	
	
	public static AranzmanDao getInstance()
	{
		if(instance == null)
		{
			instance = new AranzmanDao();
		}
		return instance;
	}
	public AranzmanDao()
	{
		aranzmani = new ArrayList<>();
	}
	public void BindAgent(AgentDAO agentDao)
	{
		for(Aranzman a : aranzmani)
		{
			a.setProdavac(agentDao.get(a.getProdavac().getId()));
		}
	}
	
	public void load() {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "aranzman.txt");
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
					int idProdavca = Integer.parseInt(st.nextToken().trim());
					String slika = st.nextToken().trim();
					int kapacitet = Integer.parseInt(st.nextToken().trim());
					LocalDate dostupan = DateHelper.stringToDate(st.nextToken().trim());
					double cena = Double.parseDouble(st.nextToken().trim());
					double sajamskiPopust = Double.parseDouble(st.nextToken().trim());
					int brojDana = Integer.parseInt(st.nextToken().trim());
					TipAranzmana tipAranzmana = TipAranzmana.valueOf(st.nextToken().trim());
					TipSmjestaja tipSmjestaja = TipSmjestaja.valueOf(st.nextToken().trim());
					boolean deleted = Boolean.parseBoolean(st.nextToken().trim());
					
					Agent p = new Agent();
					p.setId(idProdavca);
					Aranzman a = new Aranzman(id,p,slika, kapacitet,dostupan,cena,sajamskiPopust,brojDana, tipAranzmana,tipSmjestaja);
					a.setDeleted(deleted);
					aranzmani.add(a);
					
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
	public Aranzman create(Aranzman entity) {
		entity.setId(nextId());
		aranzmani.add(entity);
		saveToFile();
		return entity;
	}
	private int nextId()
	{
		int id = 0;
		for(Aranzman aranzman : aranzmani)
		{
			if(aranzman.getId() > id)
			{
				id = aranzman.getId();
			}
		}
		return id + 1;
	}
	@Override
	public Aranzman delete(Aranzman entity) {
		Aranzman a = null;
		a = get(entity.getId());
		if(a == null)
		{
			return null;
		}
		a.setDeleted(true);
		saveToFile();
		return entity;
		
	}
	@Override
	public Aranzman get(int id) {
		Aranzman a = null;
		for(Aranzman aranzman : aranzmani)
		{
			if(aranzman.getId() == id)
			{
				a = aranzman;
			}
		}
		return a;
	}
	@Override
	public ArrayList<Aranzman> getAll() {
		// TODO Auto-generated method stub
		return aranzmani;
	}
	
	public ArrayList<Aranzman>  getAllActive(){
		ArrayList<Aranzman> activeAranzmani = new ArrayList<>();
		for(Aranzman a : aranzmani) {
			if(!a.isDeleted()) {
				activeAranzmani.add(a);
			}
		}
		return activeAranzmani;
	}
	@Override
	public Aranzman update(Aranzman entity) {
		Aranzman oldEntity;
		for(Aranzman aranzman : aranzmani)
		{
			if(aranzman.getId() == entity.getId())
			{
				oldEntity = aranzman;
			}
		}
		oldEntity = entity;
		saveToFile();
		return oldEntity;
	}
	public void saveToFile() {
		try {
		      FileWriter file = new FileWriter(contextPath + "aranzman.txt");
		      BufferedWriter output = new BufferedWriter(file);
		      for(Aranzman a : aranzmani)
		      {
		    	  output.write(a.fileToLine());
		      }
		      output.close();
		}     
		catch (Exception e) {
		      e.getStackTrace();
		    }
	}
	

}
