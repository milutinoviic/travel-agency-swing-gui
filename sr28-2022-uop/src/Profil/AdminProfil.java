package Profil;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import KorisnikPregled.KorisnikPregled;
import PrikazAranzmana.PrikazAranzmana;
import RezervacijaAdmin.RezervacijaAdminView;
import view.logIn.LogIn;




public class AdminProfil extends JFrame {
	private JButton arrangmentButton;
	private JButton reservationButton;
	private JButton usersButton;
	private JButton logOut;

	public AdminProfil() {
		setSize(275, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Administrator početna strana");
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);

		Dimension dim = new Dimension(200, 30);

		arrangmentButton = new JButton("Pregled aranžmana");
		arrangmentButton.setPreferredSize(dim);
		reservationButton = new JButton("Pregled rezervacija");
		reservationButton.setPreferredSize(dim);
		usersButton = new JButton("Pregled korisnika ");
		usersButton.setPreferredSize(dim);
		logOut = new JButton ("Odjavi se");

		
		
		panel.add(new JLabel("  "));
		panel.add(arrangmentButton);
		panel.add(new JLabel("  "));
		panel.add(reservationButton);
		panel.add(new JLabel("  "));
		panel.add(usersButton);
		panel.add(new JLabel("  "));
		panel.add(logOut);

		add(panel, BorderLayout.EAST);
		
		arrangmentButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazAranzmana arrangmentView = new PrikazAranzmana();
				arrangmentView.setVisible(true);
				
			}
		});
		
		reservationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RezervacijaAdminView raz = new RezervacijaAdminView();
				raz.setVisible(true);
				
			}
		});
		

        usersButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KorisnikPregled userView = new KorisnikPregled();
				userView.setVisible(true);
				
			}
		});
        
        logOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				LogIn.ulogovanKorisnik = null;
				dispose();
				LogIn prijaviSe = new LogIn();
				prijaviSe.setVisible(true);
			}
		});
        
	}


}
