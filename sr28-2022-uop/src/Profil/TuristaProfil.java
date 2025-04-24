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

import RezervacijaAdmin.RezervacijaAdminView;
import RezervacijaTurist.RezervacijaTuristaView;
import TuristView.AranzmanTuristiView;
import view.logIn.LogIn;



public class TuristaProfil extends JFrame {
	private JButton arrangmentButton;
	private JButton reservationButton;
	private JButton logOut;
	

	public TuristaProfil() {
		setSize(275, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tursita početna strana");


		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);

		Dimension dim = new Dimension(200, 50);

		arrangmentButton = new JButton("Pregled aranžmana");
		arrangmentButton.setPreferredSize(dim);
		reservationButton = new JButton("Pregled rezervacija");
		reservationButton.setPreferredSize(dim);
		logOut = new JButton ("Odjavi se");
		
		panel.add(new JLabel("  "));
		panel.add(new JLabel("  "));
		panel.add(arrangmentButton);
        panel.add(new JLabel("  "));
		panel.add(reservationButton);
		panel.add(new JLabel("  "));
//		panel.add(logOut,BorderLayout.AFTER_LAST_LINE);
		panel.add(logOut);
	

		add(panel, BorderLayout.EAST);
		
		arrangmentButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AranzmanTuristiView atv = new AranzmanTuristiView();
				atv.setVisible(true);
			}
		});
		
		reservationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RezervacijaTuristaView reservationView = new RezervacijaTuristaView();
				reservationView.setVisible(true);
				
			}
		});
        
        logOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				LogIn.ulogovanKorisnik = null;
				dispose();
				LogIn ulogujSe = new LogIn();
				ulogujSe.setVisible(true);
			}
		});
	}


}
