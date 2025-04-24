package model;

import enums.Pol;
import enums.Uloga;

/**
 * 
 */
public abstract class Korisnik {

    /**
     * Default constructor
     */
    public Korisnik() {
    	deleted = false;
    }

    /**
     * 
     */
    protected int id;

    /**
     * 
     */
    protected String ime;

    /**
     * 
     */
    protected String prezime;

    /**
     * 
     */
    protected String jmbg;

    /**
     * 
     */
    protected String adresa;

    /**
     * 
     */
    protected String brojTelefona;

    /**
     * 
     */
    protected String korisnickoIme;

    /**
     * 
     */
    protected String lozinka;
    protected Boolean deleted;
    protected Uloga uloga;
    public Uloga getUloga()
    {
    	return uloga;
    }
    public void setUloga(Uloga uloga)
    {
    	this.uloga = uloga;
    }

    protected Pol pol;
    public Pol getPol()
    {
    	return pol;
    }
    public void setPol(Pol pol)
    {
    	this.pol = pol;
    }
    
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Korisnik(int id, String ime, String prezime, String jmbg, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		deleted = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa=" + adresa
				+ ", brojTelefona=" + brojTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + "]";
	}
    
    

}