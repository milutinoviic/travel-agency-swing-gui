package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import enums.Pol;
import enums.Uloga;
import model.Administrator;
import model.Agent;
import model.Aranzman;

public class AdministratorDao implements ICRUD<Administrator> {
	private ArrayList<Administrator> administratori;
	private static String contextPath = "C:\\Users\\HP\\Desktop\\objektno programiranje workspace\\Projekat1\\src\\podaci\\";
	private static AdministratorDao instance = null;
	
	
	public static AdministratorDao getInstance()
	{
		if(instance == null)
		{
			instance = new AdministratorDao();
		}
		return instance;
	}
	
	private AdministratorDao()
	{
		administratori = new ArrayList<>();
	}
	
	public void load() {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "administrator.txt");
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
					
					Administrator admin = new Administrator(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka);
					admin.setPol(pol);
					admin.setUloga(uloga);
					admin.setDeleted(deleted);
					administratori.add(admin);
					
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
	public Administrator create(Administrator entity) {
		entity.setId(nextId());
		administratori.add(entity);
		saveToFile();
		return entity;
	}
	
	private int nextId()
	{
		int id = -1;
		for(Administrator admin : administratori)
		{
			if(admin.getId() > id)
			{
				id = admin.getId();
			}
		}
		return id + 1;
	}
	
	@Override
	public Administrator delete(Administrator entity) {
		Administrator admin = null;
		admin = get(entity.getId());
		if(admin == null)
		{
			return null;
		}
		admin.setDeleted(true);
		saveToFile();
		return entity;
	}
	@Override
	public Administrator get(int id) {
		Administrator a = null;
		for(Administrator admin : administratori)
		{
			if(admin.getId() == id)
			{
				a = admin;
				return a;
			}
		}
		return a;
	}
	@Override
	public ArrayList<Administrator> getAll() {
		return administratori;
	}
	
	@Override
	public Administrator update(Administrator entity) {
		Administrator oldEntity = get(entity.getId());
		if(oldEntity == null)
		{
			return null;
		}
		oldEntity = entity;
		saveToFile();
		return oldEntity;
	}
	public void saveToFile() {
		try {
		      FileWriter file = new FileWriter(contextPath + "administrator.txt");

		      BufferedWriter output = new BufferedWriter(file);

		      for(Administrator a : administratori)
		      {
		    	  output.write(a.fileLine());
		      }
		      
		      output.close();
		      
		}     
		catch (Exception e) {
		      e.getStackTrace();
		    }
	}
	
	public Administrator prijaviSe(String korisnickoIme, String sifra)
	{
		Administrator admin = null;
		for(Administrator a : administratori)
		{
			if(a.getKorisnickoIme().trim().equals(korisnickoIme) && a.getLozinka().trim().equals(sifra))
			{
				admin = a;
			}
		}
		return admin;
	}
	
}
