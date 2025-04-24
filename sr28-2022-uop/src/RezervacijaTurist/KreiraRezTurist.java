package RezervacijaTurist;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.AgentController;
import controller.AranzmanController;
import controller.RezervacijaController;
import controller.TuristaController;
import dao.AgentDAO;
import dao.AranzmanDao;
import dao.RezervacijaDao;
import dao.TuristaDao;
import enums.Status;

import model.Aranzman;
import model.Rezervacija;
import model.Turista;
import view.logIn.LogIn;


public class KreiraRezTurist extends JFrame {
	public TuristaController turistaController;
	public AranzmanController aranzmanController;
	public AgentController agentController;
	public RezervacijaController rezervacijaController;

	JLabel l1, l2, l3, l4, l5, l6;
	JButton napravi;
	JTextField tf1;
	
	JComboBox<String> arazmani;

	JTextField brojPutnika;
	
	
	
	public KreiraRezTurist()
	{ 	
		rezervacijaController = new RezervacijaController(RezervacijaDao.getInstance());
		agentController = new AgentController(AgentDAO.getInstance());
		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		turistaController = new TuristaController(TuristaDao.getInstance());

        setSize(700, 700);  
        setLayout(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setTitle("Napravi rezervaciju");  
        l1 = new JLabel("Napravi rezervaciju");  
        l1.setForeground(Color.blue);  
        l1.setFont(new Font("Serif", Font.BOLD, 20));  
        l2 = new JLabel("Broj putnika:");  
        l5 = new JLabel("Aranžman:");  
        napravi = new JButton("Napravi");  
	
        tf1 = new JTextField();
     
        
        arazmani = new JComboBox<String>();
        for(Aranzman a : aranzmanController.getAllActive())
        {
        	arazmani.addItem(Integer.toString(a.getId()));
        }
       
        
        
		l1.setBounds(80, 30, 400, 30);  
		l2.setBounds(80, 70, 200, 30);  
		l5.setBounds(80, 210, 200, 30);  
		
        tf1.setBounds(300, 70, 200, 30); 
        arazmani.setBounds(300, 210, 200, 30);  
       
   
		
        napravi.setBounds(50, 450, 100, 30);  
		
		add(l1);  
        add(l2);      
        add(l5);  
        add(tf1);
        add(arazmani);
        
        
        add(napravi);  
		
        napravi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Rezervacija r = new Rezervacija();
				Aranzman a = aranzmanController.get(Integer.parseInt((String) arazmani.getSelectedItem()));
				r.setProdavac(a.getProdavac());
				r.setAranzman(a);
				r.setKupac((Turista)LogIn.ulogovanKorisnik);
				r.setBrojPutnika(Integer.parseInt(tf1.getText()));
				r.setDatumKreiranja(LocalDate.now());
				r.setStatus(Status.KREIRANA);
				
				if(a.getKapacitet() - r.getBrojPutnika() < 0)
				{
					JOptionPane.showMessageDialog(KreiraRezTurist.this, "Nema dovoljno mjesta u aranžmanu.", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				a.setKapacitet(a.getKapacitet() - r.getBrojPutnika());
				aranzmanController.update(a);
				
				
				rezervacijaController.create(r);
				JOptionPane.showMessageDialog(KreiraRezTurist.this, "Rezervacija je napravljena.", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
				
				dispose();
				
				RezervacijaTuristaView.tableModel.refresh();
			}
		});
        
        
	}
  

}
