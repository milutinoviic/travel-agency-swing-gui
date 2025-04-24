package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import enums.Pol;
import enums.Uloga;
import model.Administrator;
import model.Agent;
import model.Turista;

public class TuristaDao implements ICRUD<Turista> {
	private ArrayList<Turista> turisti;
	private static String contextPath = "C:\\\\Users\\\\HP\\\\Desktop\\\\objektno programiranje workspace\\\\Projekat1\\\\src\\\\podaci\\\\";
    private static TuristaDao instance = null;
    
	
	
	public static TuristaDao getInstance()
	{
		if(instance == null)
		{
			instance = new TuristaDao();
		}
		return instance;
	}
	public TuristaDao()
	{
		turisti = new ArrayList<>();
	}

	
	@Override
	public Turista create(Turista entity) {
		entity.setId(nextId());
		turisti.add(entity);
		saveToFile();
		return entity;
	}
	private int nextId()
	{
		int id = -1;
		for(Turista turist : turisti)
		{
			if(turist.getId() > id)
			{
				id = turist.getId();
			}
		}
		return id+1;
	}

	@Override
	public Turista delete(Turista entity) {
		Turista tur = null;
		tur = get(entity.getId());
		if(tur == null)
		{
			return null;
		}
		tur.setDeleted(true);
		saveToFile();
		return entity;
	}

	@Override
	public Turista get(int id) {
		Turista a = null;
		for(Turista turist : turisti)
		{
			if(turist.getId() == id)
			{
				a = turist;
			}
		}
		return a;
	}

	@Override
	public ArrayList<Turista> getAll() {
		return turisti;
	}

	@Override
	public Turista update(Turista entity) {
		Turista oldEntity;
		for(Turista turist : turisti)
		{
			if(turist.getId() == entity.getId())
			{
				oldEntity = turist;
			}
		}
		oldEntity = entity;
		saveToFile();
		return oldEntity;
	}
	
	
	public void load()
	{
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "turisti.txt");
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
					
					Turista tur = new Turista(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka);
					tur.setPol(pol);
					tur.setUloga(uloga);
					tur.setDeleted(deleted);
					
					turisti.add(tur);
					
					
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

	public void saveToFile() {
		try {
		      FileWriter file = new FileWriter(contextPath + "turisti.txt");

		      BufferedWriter output = new BufferedWriter(file);

		      for(Turista t : turisti)
		      {
		    	  output.write(t.fileLine());
		      }
		      
		      output.close();
		      
		}     
		catch (Exception e) {
		      e.getStackTrace();
		    }
	}
	public Turista prijaviSe(String korisnickoIme, String sifra)
	{
		Turista turist = null;
		for(Turista t : turisti)
		{
			if(t.getKorisnickoIme().trim().equals(korisnickoIme) && t.getLozinka().trim().equals(sifra))
			{
				turist = t;
			}
		}
		return turist;
	}
	
	public Turista getByKorisnickoIme(String ime)
	{
		for(Turista t : turisti)
		{
			if(t.getKorisnickoIme().equals(ime))
			{
				return t;
			}
		}
		return null;
	}
	
}
