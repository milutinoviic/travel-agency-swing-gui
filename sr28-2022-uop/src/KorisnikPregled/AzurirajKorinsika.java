package KorisnikPregled;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.AdministratorController;
import controller.AgentController;
import controller.AranzmanController;
import controller.RezervacijaController;
import controller.TuristaController;
import dao.AdministratorDao;
import dao.AgentDAO;
import dao.AranzmanDao;
import dao.RezervacijaDao;
import dao.TuristaDao;
import enums.Pol;
import enums.Uloga;
import model.Rezervacija;
import model.Turista;
import model.Administrator;
import model.Agent;


public class AzurirajKorinsika extends JFrame {
	public TuristaController turistaController;
	public AranzmanController aranzmanController;
	public AgentController agentController;
	public RezervacijaController rezervacijaController;
	public AdministratorController administratorController;
	private Rezervacija rezervacija;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;  
    JTextField tf1, tf2, tf5, tf6, tf7;   
    JButton izmeni;  
    JPasswordField p2;
    JComboBox<Pol> polovi;
	JComboBox<Uloga> uloge;   
	JTextField p1;
	model.Korisnik selektovanKorisnik;
	
	public AzurirajKorinsika(model.Korisnik kor)
	{
		rezervacijaController = new RezervacijaController(RezervacijaDao.getInstance());
		agentController = new AgentController(AgentDAO.getInstance());
		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		turistaController = new TuristaController(TuristaDao.getInstance());
		administratorController=new AdministratorController(AdministratorDao.getInstance());
		selektovanKorisnik = kor;
		setVisible(true);  
        setSize(700, 700);  
        setLayout(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setTitle("Izmjena korisnika");  
        l1 = new JLabel("Izmeni korisnika:");  
        l1.setForeground(Color.blue);  
        l1.setFont(new Font("Serif", Font.BOLD, 20));  
        l2 = new JLabel("Ime:");  
        l3 = new JLabel("Prezime:");  
        l4 = new JLabel("Adresa:");  
        l5 = new JLabel("JMBG:");  
        l6 = new JLabel("Broj telefona:");  
        l7 = new JLabel("Korisničko ime:");  
        l8 = new JLabel("Lozinka:");   
        l9 = new JLabel("Pol:");   
        l10 = new JLabel("Uloga:");   
        tf1 = new JTextField();  
        tf2 = new JTextField();  
        p1 = new JTextField();  
        p2 = new JPasswordField();  
        tf5 = new JTextField();  
        tf6 = new JTextField();  
        tf7 = new JTextField();  
        izmeni = new JButton("Izmjeni");  
  
        tf1.setText(selektovanKorisnik.getIme());
        tf2.setText(selektovanKorisnik.getPrezime());
        tf7.setText(selektovanKorisnik.getJmbg());
        p1.setText(selektovanKorisnik.getAdresa());
        tf5.setText(selektovanKorisnik.getBrojTelefona());
        tf6.setText(selektovanKorisnik.getKorisnickoIme());
        p2.setText(selektovanKorisnik.getLozinka());
        
        
        polovi = new JComboBox<Pol>(Pol.values());
        uloge = new JComboBox<Uloga>(Uloga.values());

        l1.setBounds(100, 30, 400, 30);  
        l2.setBounds(80, 70, 200, 30);  
        l3.setBounds(80, 110, 200, 30);  
        l4.setBounds(80, 150, 200, 30);  
        l5.setBounds(80, 190, 200, 30);  
        l6.setBounds(80, 230, 200, 30);  
        l7.setBounds(80, 270, 200, 30);  
        l8.setBounds(80, 310, 200, 30); 
        l9.setBounds(80, 350, 200, 30); 
        l10.setBounds(80, 390, 200, 30); 

        tf1.setBounds(300, 70, 200, 30);  
        tf2.setBounds(300, 110, 200, 30);  
        p1.setBounds(300, 150, 200, 30);  
        tf7.setBounds(300, 190, 200, 30);  
        tf5.setBounds(300, 230, 200, 30);  
        tf6.setBounds(300, 270, 200, 30);  
        p2.setBounds(300, 310, 200, 30);  
        polovi.setBounds(300, 350, 200, 30);
        uloge.setBounds(300, 390, 200, 30);
        
        polovi.setSelectedItem(selektovanKorisnik.getPol());
        uloge.setSelectedItem(selektovanKorisnik.getUloga());
        
        izmeni.setBounds(50, 450, 100, 30);  
        
        add(l1);  
        add(l2);  
        add(tf1);  
        add(l3);  
        add(tf2);  
        add(l4);  
        add(p1);  
        add(l5);  
        add(tf7);  
        add(l6);  
        add(tf5);  
        add(l7);  
        add(tf6);  
        add(l8);  
        add(p2);  
        add(polovi);
        add(uloge);
        
        add(izmeni);  
		
        izmeni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kor.setIme(tf1.getText());
				kor.setPrezime(tf2.getText());
				kor.setJmbg(tf7.getText());
				kor.setAdresa(p1.getText());
				kor.setBrojTelefona(tf5.getText());
				kor.setKorisnickoIme(tf6.getText());
				kor.setLozinka(p2.getText());
				kor.setPol((Pol)polovi.getSelectedItem());
				kor.setUloga((Uloga)uloge.getSelectedItem());
				
				if((Uloga)uloge.getSelectedItem() == Uloga.ADMINISTRATOR)
				{
					administratorController.update((Administrator)kor);
				}
				else if((Uloga)uloge.getSelectedItem() == Uloga.AGENT)
				{
						agentController.update((Agent)kor);
				}
				else
				{
						turistaController.update((Turista)kor);
				}
				dispose();
				
				KorisnikPregled view = new KorisnikPregled();
		}
        
	});
	
}
	

}
