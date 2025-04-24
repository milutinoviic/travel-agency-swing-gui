package model;

import java.util.*;

/**
 * 
 */
public class Turista extends Korisnik {

    /**
     * Default constructor
     */
    public Turista() {
    	super();
    }

    /**
     * 
     */
    protected ArrayList<Rezervacija> listaRezervacija;

   
    
	public Turista(int id, String ime, String prezime, String jmbg, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka) {
		super(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka);
	}

	public ArrayList<Rezervacija> getListaRezervacija() {
		return listaRezervacija;
	}

	public void setListaRezervacija(ArrayList<Rezervacija> listaRezervacija) {
		this.listaRezervacija = listaRezervacija;
	}

	@Override
	public String toString() {
		return "Turista [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa=" + adresa
				+ ", brojTelefona=" + brojTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + "]";
	}
	
	public String fileLine()
	{
		return Integer.toString(id) + ";" + ime + ";" + prezime + ";" + jmbg + ";" + adresa
				+ ";" + brojTelefona + ";" + korisnickoIme + ";" + lozinka + ";" + pol.toString() + ";" +uloga.toString()+";"+Boolean.toString(deleted)+"\n";
	}
    
    

}