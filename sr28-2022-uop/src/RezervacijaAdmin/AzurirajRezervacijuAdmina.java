package RezervacijaAdmin;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.AgentController;
import controller.AranzmanController;
import controller.RezervacijaController;
import controller.TuristaController;
import dao.AgentDAO;
import dao.AranzmanDao;
import dao.RezervacijaDao;
import dao.TuristaDao;
import model.Agent;
import model.Aranzman;
import model.Rezervacija;
import model.Turista;


public class AzurirajRezervacijuAdmina extends JFrame{
	JLabel l1, l2, l3, l4, l5, l6;
	JTextField tf1, tf2, tf5, tf6, tf7;   
	JButton izmeni; 
	JPasswordField p2; 
	JTextField p1;
	public TuristaController turistaController;
	public AranzmanController aranzmanController;
	public AgentController agentController;
	public RezervacijaController rezervacijaController;
	private Rezervacija rezervacija;
	
	public AzurirajRezervacijuAdmina(Rezervacija rez)
	{
		rezervacijaController = new RezervacijaController(RezervacijaDao.getInstance());
		agentController = new AgentController(AgentDAO.getInstance());
		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		turistaController = new TuristaController(TuristaDao.getInstance());
		rezervacija = rez;
		
        setSize(700, 700);  
        setLayout(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setTitle("Izmeni rezervaciju");  
        l1 = new JLabel("Izmjeni rezervaciju");  
        l1.setForeground(Color.blue);  
        l1.setFont(new Font("Serif", Font.BOLD, 20));  
        l2 = new JLabel("Broj putnika:");  
        l3 = new JLabel("Datum kreiranja:");  
        l4 = new JLabel("Turista:");  
        l5 = new JLabel("Aranžman:");  
        l6 = new JLabel("Agent:");  
        napravi = new JButton("Izmjeni");  
	
        tf1 = new JTextField();
     
        turiste = new JComboBox<String>();
        for(Turista t : turistaController.getAll())
        {
        	turiste.addItem(t.getKorisnickoIme());
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
		l3.setBounds(80, 110, 200, 30);  
		l4.setBounds(80, 150, 200, 30);  
		l5.setBounds(80, 190, 200, 30);  
		l6.setBounds(80, 230, 200, 30); 
		
        tf1.setBounds(300, 70, 200, 30);
        tf1.setText(Integer.toString(rezervacija.getBrojPutnika()));
        
        
         turiste.setBounds(300, 190, 200, 30); 
         turiste.setSelectedItem(rezervacija.getKupac().getKorisnickoIme());
         arazmani.setBounds(300, 230, 200, 30);  
         arazmani.setSelectedItem(rezervacija.getAranzman().getId());
         agenti.setBounds(300, 270, 200, 30);  
         agenti.setSelectedItem(rezervacija.getProdavac().getKorisnickoIme());
       
		
        napravi.setBounds(50, 450, 100, 30);  
		
        add(tf1);
        add(turiste);
        add(arazmani);
        add(agenti);
        add(l1);  
        add(l2);    
        add(l3);  
        add(l4);  
        add(l5);  
        add(l6);  
        
       add(napravi);  
	
       napravi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rezervacija.setProdavac(agentController.getByKorisnickoIme((String)agenti.getSelectedItem()));
				Aranzman a = aranzmanController.get(Integer.parseInt((String) arazmani.getSelectedItem()));
				rezervacija.setAranzman(a);
				rezervacija.setKupac(turistaController.getByKorisnickoIme((String)turiste.getSelectedItem()));
				rezervacija.setBrojPutnika(Integer.parseInt(tf1.getText()));
				rezervacija.setDatumKreiranja(LocalDate.now());
				
				
				rezervacijaController.update(rezervacija);
				JOptionPane.showMessageDialog(AzurirajRezervacijuAdmina.this, "Rezervacija je izmenjena", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
       setVisible(true);  
	}

	

	JButton napravi;

	
	JComboBox<String> turiste;
	JComboBox<String> arazmani;
	JComboBox<String> agenti;
	

}
