package view.logIn;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Profil.AdminProfil;
import Profil.AgentProfil;
import Profil.TuristaProfil;
import controller.AdministratorController;
import controller.AgentController;
import controller.TuristaController;
import dao.AdministratorDao;
import dao.AgentDAO;
import dao.TuristaDao;
import model.Administrator;
import model.Korisnik;



public class LogIn extends JFrame{
	private AdministratorController administratorController;
	private AgentController agentController;
	private TuristaController turistController;
	public static Korisnik ulogovanKorisnik;
	
	public LogIn()
	{
		administratorController = new AdministratorController(AdministratorDao.getInstance());
		turistController = new TuristaController(TuristaDao.getInstance());
		agentController = new AgentController(AgentDAO.getInstance());
		ulogovanKorisnik = null;
		
		setTitle("Uloguj se");
		setSize(1000, 1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		JPanel centar = new JPanel();
		BoxLayout ivica = new BoxLayout(centar, BoxLayout.Y_AXIS);
		centar.setLayout(ivica);
		
		Dimension dim = new Dimension(150,20);
		
		JPanel prozorKorisnickoIme=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel IbIUsername=new JLabel("Korisnicko ime:");
		IbIUsername.setPreferredSize(dim);
		JTextField unosKorisnickoIme=new JTextField();
		unosKorisnickoIme.setPreferredSize(dim);
		prozorKorisnickoIme.add(IbIUsername);
		prozorKorisnickoIme.add(unosKorisnickoIme);
		
		JPanel prozorSifra=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel IbIPassword=new JLabel("Sifra:");
		IbIPassword.setPreferredSize(dim);
		JPasswordField unosSifra=new JPasswordField();
		unosSifra.setPreferredSize(dim);
		prozorSifra.add(IbIPassword);
		prozorSifra.add(unosSifra);
		
		centar.add(prozorKorisnickoIme);
		centar.add(prozorSifra);
		
		
		JPanel dugmad = new JPanel();
		BoxLayout mesto = new BoxLayout(dugmad, ivica.X_AXIS);
		dugmad.setLayout(mesto);
		
		JButton prijaviSe = new JButton("Prijavi se");
		prijaviSe.setPreferredSize(new Dimension(75, 25));
		
		JButton btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(75, 25));

       
        dugmad.add(Box.createGlue());
        dugmad.add(prijaviSe);
        dugmad.add(Box.createHorizontalStrut(10));
        dugmad.add(btnCancel);
        dugmad.add(Box.createHorizontalStrut(10));
      
		
        centar.add(dugmad);
		add(centar, BorderLayout.CENTER);
		pack();
		
		
		prijaviSe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme  = unosKorisnickoIme.getText();
				String sifra = unosSifra.getText();
				
				System.out.println(korisnickoIme + "  " + sifra);
				
				ulogovanKorisnik = administratorController.prijaviSe(korisnickoIme, sifra);
				if(ulogovanKorisnik != null)
				{
					AdminProfil adminView = new AdminProfil();
					adminView.setVisible(true);
					return;
				}
				ulogovanKorisnik = turistController.prijaviSe(korisnickoIme, sifra);
				if(ulogovanKorisnik != null)
				{
					TuristaProfil t = new TuristaProfil();
					t.setVisible(true);
					return;
				}
				ulogovanKorisnik = agentController.prijaviSe(korisnickoIme, sifra);
				if(ulogovanKorisnik != null)
				{
					AgentProfil a = new AgentProfil();
					a.setVisible(true);
					return;
				}
				
				JOptionPane.showMessageDialog(null, "Lozinka ili korisnicko ime nisu tacni");
				
			}
		});
		
		
	}
	

	

}
