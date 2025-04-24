package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import enums.TipAranzmana;
import enums.TipSmjestaja;
import util.DateHelper;

/**
 * 
 */
public class Aranzman {

    /**
     * Default constructor
     */
    

    /**
     * 
     */
    protected ArrayList<Rezervacija> rezervacije;
    protected int id;
    
    protected Agent prodavac;
   

    /**
     * 
     */
    protected String slika;

    /**
     * 
     */
    protected int kapacitet;

    /**
     * 
     */
    protected LocalDate dostupanDatum;

    /**
     * 
     */
    protected double cijena;

    /**
     * 
     */
    protected double sajamskiPopust;

    /**
     * 
     */
    protected int brojDana;
    protected boolean deleted;
    
    protected TipAranzmana tipAranzmana;
    protected TipSmjestaja tipSmjestaja;
	
    public Aranzman()
    {
    	rezervacije = new ArrayList<>();
    }
    
    public Aranzman(int id, Agent prodavac, String slika, int kapacitet,
			LocalDate dostupanDatum, double cijena, double sajamskiPopust, int brojDana, TipAranzmana tipAranzmana,
			TipSmjestaja tipSmjestaja) {
		super();
		this.rezervacije = new ArrayList<>();
		this.id = id;
		this.prodavac = prodavac;
		this.slika = slika;
		this.kapacitet = kapacitet;
		this.dostupanDatum = dostupanDatum;
		this.cijena = cijena;
		this.sajamskiPopust = sajamskiPopust;
		this.brojDana = brojDana;
		this.tipAranzmana = tipAranzmana;
		this.tipSmjestaja = tipSmjestaja;
	}
	
	public String fileToLine()
	{
		return Integer.toString(id) + ";" + 
						Integer.toString(prodavac.getId()) + ";" +
				slika + ";" + Integer.toString(kapacitet) + ";" +
						DateHelper.dateToString(dostupanDatum) + ";" + Double.toString(cijena) +
				";" + Double.toString(sajamskiPopust) + ";" + Integer.toString(brojDana) + ";" + tipAranzmana.toString()
				+ ";" + tipSmjestaja.toString() + ";" + Boolean.toString(deleted) + "\n";	
	}
	
	
	public ArrayList<Rezervacija> getRezervacije() {
		return rezervacije;
	}
	public void setRezervacije(ArrayList<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Agent getProdavac() {
		return prodavac;
	}
	public void setProdavac(Agent prodavac) {
		this.prodavac = prodavac;
	}
	public String getSlika() {
		return slika;
	}
	public void setSlika(String slika) {
		this.slika = slika;
	}
	public int getKapacitet() {
		return kapacitet;
	}
	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}
	public LocalDate getDostupanDatum() {
		return dostupanDatum;
	}
	public void setDostupanDatum(LocalDate dostupanDatum) {
		this.dostupanDatum = dostupanDatum;
	}
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	public double getSajamskiPopust() {
		return sajamskiPopust;
	}
	public void setSajamskiPopust(double sajamskiPopust) {
		this.sajamskiPopust = sajamskiPopust;
	}
	public int getBrojDana() {
		return brojDana;
	}
	public void setBrojDana(int brojDana) {
		this.brojDana = brojDana;
	}
	public TipAranzmana getTipAranzmana() {
		return tipAranzmana;
	}
	public void setTipAranzmana(TipAranzmana tipAranzmana) {
		this.tipAranzmana = tipAranzmana;
	}
	public TipSmjestaja getTipSmjestaja() {
		return tipSmjestaja;
	}
	public void setTipSmjestaja(TipSmjestaja tipSmjestaja) {
		this.tipSmjestaja = tipSmjestaja;
	}
	@Override
	public String toString() {
		return "Aranzman [rezervacije=" + rezervacije + ", id=" + id + ", prodavac=" + prodavac + ", slika=" + slika
				+ ", kapacitet=" + kapacitet + ", dostupanDatum=" + dostupanDatum + ", cijena=" + cijena
				+ ", sajamskiPopust=" + sajamskiPopust + ", brojDana=" + brojDana + ", tipAranzmana=" + tipAranzmana
				+ ", tipSmjestaja=" + tipSmjestaja + "]";
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Aranzman(ArrayList<Rezervacija> rezervacije, int id, Agent prodavac, String slika, int kapacitet,
			TipAranzmana tipAranzmana, TipSmjestaja tipSmjestaja) {
		super();
		this.rezervacije = rezervacije;
		this.id = id;
		this.prodavac = prodavac;
		this.slika = slika;
		this.kapacitet = kapacitet;
		this.dostupanDatum = dostupanDatum;
		this.cijena = cijena;
		this.sajamskiPopust = sajamskiPopust;
		this.brojDana = brojDana;
		this.deleted = deleted;
		this.tipAranzmana = tipAranzmana;
		this.tipSmjestaja = tipSmjestaja;
	}

	public Aranzman(ArrayList<Rezervacija> rezervacije, int id, Agent prodavac, String slika, int kapacitet,
			LocalDate dostupanDatum, double cijena, double sajamskiPopust, int brojDana, boolean deleted,
			TipAranzmana tipAranzmana, TipSmjestaja tipSmjestaja) {
		super();
		this.rezervacije = rezervacije;
		this.id = id;
		this.prodavac = prodavac;
		this.slika = slika;
		this.kapacitet = kapacitet;
		this.dostupanDatum = dostupanDatum;
		this.cijena = cijena;
		this.sajamskiPopust = sajamskiPopust;
		this.brojDana = brojDana;
		this.deleted = deleted;
		this.tipAranzmana = tipAranzmana;
		this.tipSmjestaja = tipSmjestaja;
	}
	
	
	


	
    
    

}