package model;

import java.util.*;

/**
 * 
 */
public class Agent extends Korisnik {

    /**
     * Default constructor
     */
    public Agent() {
    }

    /**
     * 
     */
    protected ArrayList<Aranzman> listaKreiranihAranzmana;

	public Agent(int id, String ime, String prezime, String jmbg, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka) {
		super(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka);
	}

	public ArrayList<Aranzman> getListaKreiranihAranzmana() {
		return listaKreiranihAranzmana;
	}

	public void setListaKreiranihAranzmana(ArrayList<Aranzman> listaKreiranihAranzmana) {
		this.listaKreiranihAranzmana = listaKreiranihAranzmana;
	}

	@Override
	public String toString() {
		return "Agent [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa=" + adresa
				+ ", brojTelefona=" + brojTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + "]";
	}
	public String fileLine()
	{
		return Integer.toString(id) + ";" + ime + ";" + prezime + ";" + jmbg + ";" + adresa
				+ ";" + brojTelefona + ";" + korisnickoIme + ";" + lozinka + ";" + pol.toString() + ";" +uloga.toString()+";"+Boolean.toString(deleted)+"\n";
	}
    
    

}