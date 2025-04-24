package view.logIn;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.AdministratorController;
import controller.AgentController;
import controller.TuristaController;
import dao.AdministratorDao;
import dao.AgentDAO;
import dao.TuristaDao;
import model.Administrator;
import model.Agent;
import model.Korisnik;
import model.Turista;
import enums.Pol;
import enums.Uloga;


public class RegistrujSe extends JFrame {
	private AdministratorController administratorController;
	private AgentController agentController;
	private TuristaController turistController;
	

	public RegistrujSe()
	{
		administratorController = new AdministratorController(AdministratorDao.getInstance());
		turistController = new TuristaController(TuristaDao.getInstance());
		agentController = new AgentController(AgentDAO.getInstance());
		/*public Korisnik(int id, String ime, String prezime, String jmbg, String adresa, String brojTelefona,
			String korisnickoIme, String lozinka) 
		 */
		 setVisible(true);  
	        setSize(700, 700);  
	        setLayout(null);  
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        setTitle("Registration Form in Java");  
	        JLabel l1 = new JLabel("Registration Form in Windows Form:");  
	        l1.setForeground(Color.blue);  
	        l1.setFont(new Font("Serif", Font.BOLD, 20));  
	        JLabel l2 = new JLabel("Ime:");  
	        JLabel l3 = new JLabel("Prezime:");  
	        JLabel l4 = new JLabel("JMBG:");  
	        JLabel l5 = new JLabel("Adresa:");  
	        JLabel l6 = new JLabel("Broj telefona:");  
	        JLabel l7 = new JLabel("Korisnicko ime");  
	        JLabel l8 = new JLabel("Pol:");
	        JLabel l9 = new JLabel("Uloga:");
	        JLabel l10 = new JLabel("Lozinka:");
	        JTextField tf1 = new JTextField();   //ime  
	        JTextField tf2 = new JTextField();  //prezime
	        JPasswordField p1 = new JPasswordField();   //jmbg
	        JPasswordField p2 = new JPasswordField();  	//sifra
	        JTextField tf5 = new JTextField();  		//br fona
	        JTextField tf6 = new JTextField();  		//kor ime
	        JTextField tf7 = new JTextField();  		//adresa
	        JButton btn1 = new JButton("Submit");  
	        JButton btn2 = new JButton("Clear");  
	
	        JComboBox<enums.Pol> polcb = new JComboBox<enums.Pol>(enums.Pol.values());
	        JComboBox<enums.Uloga> poluloga = new JComboBox<enums.Uloga>(enums.Uloga.values());
	       
	        l1.setBounds(100, 30, 400, 30);  
	        l2.setBounds(80, 70, 200, 30);  
	        l3.setBounds(80, 110, 200, 30);  
	        l4.setBounds(80, 150, 200, 30);  
	        l5.setBounds(80, 190, 200, 30);  
	        l6.setBounds(80, 230, 200, 30);  
	        l7.setBounds(80, 270, 200, 30);  
	        l8.setBounds(80, 350, 200, 30);
	        l9.setBounds(80, 390, 200, 30);  
	        l10.setBounds(80, 310, 200, 30);
	        tf1.setBounds(300, 70, 200, 30);  
	        tf2.setBounds(300, 110, 200, 30);  
	        p1.setBounds(300, 150, 200, 30);  
	        p2.setBounds(300, 190, 200, 30);  
	        tf5.setBounds(300, 230, 200, 30);  
	        tf6.setBounds(300, 270, 200, 30);  
	        tf7.setBounds(300, 310, 200, 30);  
	        polcb.setBounds(300, 350, 200, 30);  
	        poluloga.setBounds(300, 390, 200, 30);    
		         
	        btn1.setBounds(50, 450, 100, 30);  
	        btn2.setBounds(170, 450, 100, 30);  
	       
	        add(l1);  
	        add(l2);  
	        add(tf1);  
	        add(l3);  
	        add(tf2);  
	        add(l4);  
	        add(p1);  
	        add(l5);  
	        add(p2);  
	        add(l6);  
	        add(tf5);  
	        add(l7);  
	        add(tf6);  
	        add(l8);  
	        add(tf7);  
	        add(l9);
	        add(l10);
	        add(polcb);
	        add(poluloga);
	        add(btn1);  
	        add(btn2); 
	        
	        btn1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if((Uloga)poluloga.getSelectedItem() == Uloga.ADMINISTRATOR)
					{
						Administrator a = new Administrator();
						a.setIme(tf1.getText());
						a.setPrezime(tf2.getText());
						a.setJmbg(p1.getText());
						a.setAdresa(tf7.getText());
						a.setBrojTelefona(tf5.getText());
						a.setKorisnickoIme(tf6.getText());
						a.setLozinka(p2.getText());
						a.setPol((Pol)(polcb.getSelectedItem()));
						a.setUloga((Uloga)poluloga.getSelectedItem());
						
						administratorController.create(a);
					}
					else if((Uloga)poluloga.getSelectedItem() == Uloga.AGENT)
					{
						Agent a = new Agent();
						a.setIme(tf1.getText());
						a.setPrezime(tf2.getText());
						a.setJmbg(p1.getText());
						a.setAdresa(tf7.getText());
						a.setBrojTelefona(tf5.getText());
						a.setKorisnickoIme(tf6.getText());
						a.setLozinka(p2.getText());
						a.setPol((Pol)(polcb.getSelectedItem()));
						a.setUloga((Uloga)poluloga.getSelectedItem());
						
						agentController.create(a);
						
					}
					else
					{
						Turista t = new Turista();
						t.setIme(tf1.getText());
						t.setPrezime(tf2.getText());
						t.setJmbg(p1.getText());
						t.setAdresa(tf7.getText());
						t.setBrojTelefona(tf5.getText());
						t.setKorisnickoIme(tf6.getText());
						t.setLozinka(p2.getText());
						t.setPol((Pol)(polcb.getSelectedItem()));
						t.setUloga((Uloga)poluloga.getSelectedItem());
						
						turistController.create(t);
						
					}
				}
			});
		
	}


}
