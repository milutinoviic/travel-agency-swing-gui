package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import enums.Status;
import util.DateHelper;

/**
 * 
 */
public class Rezervacija {

    /**
     * Default constructor
     */
    

    /**
     * 
     */
    protected int id;

    /**
     * 
     */
    protected Turista kupac;
    
    protected Agent prodavac;
    
    protected int brojPutnika;
    protected Aranzman aranzman;
    protected Status status;

    protected LocalDate datumKreiranja;
    protected boolean deleted;
    protected double cena;
    
    protected void ukupnaCijena() {
        // TODO implement here
    }

	public Rezervacija(int id, Turista kupac, Agent prodavac, int brojPutnika, Aranzman aranzman, Status status,
			LocalDate datumKreiranja) {
		super();
		this.id = id;
		this.kupac = kupac;
		this.prodavac = prodavac;
		this.brojPutnika = brojPutnika;
		this.aranzman = aranzman;
		this.status = status;
		this.datumKreiranja = datumKreiranja;
	}
	
	
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String fileToLine()
	{
		return Integer.toString(id) + ";" + 
						Integer.toString(kupac.getId()) + ";" + 
				Integer.toString(prodavac.getId()) + ";" + Integer.toString(brojPutnika) + ";" +
			Integer.toString(aranzman.getId()) + ";" + status.toString() + ";" +
				DateHelper.dateToString(datumKreiranja) + ";" + Double.toString(cena) + "\n";
	}

	public Rezervacija() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Turista getKupac() {
		return kupac;
	}

	public void setKupac(Turista kupac) {
		this.kupac = kupac;
	}

	public Agent getProdavac() {
		return prodavac;
	}

	public void setProdavac(Agent prodavac) {
		this.prodavac = prodavac;
	}

	public int getBrojPutnika() {
		return brojPutnika;
	}

	public void setBrojPutnika(int brojPutnika) {
		this.brojPutnika = brojPutnika;
	}

	public Aranzman getAranzman() {
		return aranzman;
	}

	public void setAranzman(Aranzman aranzman) {
		this.aranzman = aranzman;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(LocalDate datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	@Override
	public String toString() {
		return "Rezervacija [id=" + id + ", kupac=" + kupac + ", prodavac=" + prodavac + ", brojPutnika=" + brojPutnika
				+ ", aranzman=" + aranzman + ", status=" + status + ", datumKreiranja=" + datumKreiranja + "]";
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
    
   
    
    
	

	
    
    

}