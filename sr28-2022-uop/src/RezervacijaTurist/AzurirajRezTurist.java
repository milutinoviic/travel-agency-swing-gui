package RezervacijaTurist;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.time.LocalDate;
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
import model.Rezervacija;

public class AzurirajRezTurist extends JFrame {
	public TuristaController turistaController;
	public AranzmanController aranzmanController;
	public AgentController agentController;
	public RezervacijaController rezervacijaController;
	private Rezervacija rezervacija;
	JLabel l1, l2, l3, l4, l5, l6;
	JTextField tf1, tf2, tf5, tf6, tf7;   
	JButton izmeni; 
	JPasswordField p2; 
	JTextField p1;
	
	public AzurirajRezTurist(Rezervacija rez)
	{
		rezervacijaController = new RezervacijaController(RezervacijaDao.getInstance());

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
 
        napravi = new JButton("Izmjeni");  
	
        tf1 = new JTextField();
     
        
        
		l1.setBounds(80, 30, 400, 30);  
		l2.setBounds(80, 70, 200, 30);  
		
        tf1.setBounds(300, 70, 200, 30);
        tf1.setText(Integer.toString(rez.getBrojPutnika()));
       
       
		
        napravi.setBounds(50, 450, 100, 30);  
		
        add(tf1);
        add(l1);  
        add(l2);      
        
       add(napravi);  
	
       napravi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rez.setBrojPutnika(Integer.parseInt(tf1.getText()));
				rez.setDatumKreiranja(LocalDate.now());
				
				
				
				rezervacijaController.update(rez);
				JOptionPane.showMessageDialog(AzurirajRezTurist.this, "Rezervacija je izmenjena.", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				RezervacijaTuristaView.tableModel.refresh();
			}
		});
       setVisible(true);  
	}

	

	JButton napravi;

	
	JComboBox<String> turiste;
	JComboBox<String> arazmani;
	JComboBox<String> agenti;
	

}
