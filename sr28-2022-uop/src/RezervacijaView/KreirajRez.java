package RezervacijaView;

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
import model.Agent;
import model.Aranzman;
import model.Rezervacija;
import model.Turista;


public class KreirajRez extends JFrame {
	public TuristaController turistaController;
	public AranzmanController aranzmanController;
	public AgentController agentController;
	public RezervacijaController rezervacijaController;
	
	JLabel l1, l2, l3, l4, l5, l6;
	JButton napravi;
	JTextField tf1;
	
	JComboBox<String> turiste;
	JComboBox<String> arazmani;
	JComboBox<String> agenti;
	JTextField brojPutnika;
	
	
	
	public KreirajRez()
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
        l4 = new JLabel("Turista:");  
        l5 = new JLabel("Aranžman:");  
        l6 = new JLabel("Agent:");  
        napravi = new JButton("Napravi");  
	
        tf1 = new JTextField();
     
        turiste = new JComboBox<String>();
        for(Turista t : turistaController.getAll())
        {
        	turiste.addItem(t.getKorisnickoIme());;
        }
        
        
        arazmani = new JComboBox<String>();
        for(Aranzman a : aranzmanController.getAll())
        {
        	arazmani.addItem(Integer.toString(a.getId()));
        }
        
        
        agenti = new JComboBox<String>();
        for(Agent ag : agentController.getAll())
        {
        	agenti.addItem(ag.getKorisnickoIme());
        }
     
        
        
		l1.setBounds(80, 30, 400, 30);  
		l2.setBounds(80, 70, 200, 30);  
		l4.setBounds(80, 170, 200, 30);  
		l5.setBounds(80, 210, 200, 30);  
		l6.setBounds(80, 250, 200, 30); 
        tf1.setBounds(300, 70, 200, 30); 
        turiste.setBounds(300, 170, 200, 30);  
        arazmani.setBounds(300, 210, 200, 30);  
        agenti.setBounds(300, 250, 200, 30);  
       
   
		
        
        
        napravi.setBounds(50, 450, 100, 30);  
		
		add(l1);  
        add(l2);      
        add(l4);  
        add(l5);  
        add(l6); 
        add(tf1);
        add(turiste);
        add(arazmani);
        add(agenti);
        
        
        add(napravi);  
		
        napravi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				Rezervacija r = new Rezervacija();
				r.setProdavac(agentController.getByKorisnickoIme((String)agenti.getSelectedItem()));
				Aranzman a = aranzmanController.get(Integer.parseInt((String) arazmani.getSelectedItem()));
				r.setAranzman(a);
				r.setKupac(turistaController.getByKorisnickoIme((String)turiste.getSelectedItem()));
				r.setBrojPutnika(Integer.parseInt(tf1.getText()));
				r.setDatumKreiranja(LocalDate.now());
				r.setStatus(Status.KREIRANA);
				
				
				rezervacijaController.create(r);
				JOptionPane.showMessageDialog(KreirajRez.this, "Rezervacija je napravljena.", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			
			
			}
		});
        
        
	}
  

}
