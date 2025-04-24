package model;


/**
 * 
 */
public class Administrator extends Korisnik {

    /**
     * Default constructor
     */
    public Administrator() {
    }

	public Administrator(int id, String ime, String prezime, String jmbg, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka) {
		super(id, ime, prezime, jmbg, adresa, brojTelefona, korisnickoIme, lozinka);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa="
				+ adresa + ", brojTelefona=" + brojTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka="
				+ lozinka + "]";
	}
	public String fileLine()
	{
		return Integer.toString(id) + ";" + ime + ";" + prezime + ";" + jmbg + ";" + adresa
				+ ";" + brojTelefona + ";" + korisnickoIme + ";" + lozinka + ";" + pol.toString() + ";" +uloga.toString()+";"+Boolean.toString(deleted)+"\n";
	}
    
    

}