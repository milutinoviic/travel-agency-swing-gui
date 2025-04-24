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
import RezervacijaAgent.RezervacijaAgent;
import agentView.AranzmanAgentView;
import view.logIn.LogIn;



public class AgentProfil extends JFrame {
	private JButton arrangmentButton;
	private JButton reservationButton;
	private JButton theReportButton;
	private JButton logOut;
	

	public AgentProfil() {
		setSize(275, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Agent početna strana");


		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);

		Dimension dim = new Dimension(200, 30);

		arrangmentButton = new JButton("Pregled aranžmana");
		arrangmentButton.setPreferredSize(dim);
		
		reservationButton = new JButton("Pregled rezervacija");
		reservationButton.setPreferredSize(dim);
		theReportButton = new JButton("Izveštaj");
		theReportButton.setPreferredSize(dim);
		logOut = new JButton ("Odjavi se");
		

		panel.add(new JLabel("  "));
		panel.add(arrangmentButton);
		panel.add(new JLabel("  "));
		panel.add(reservationButton);
		panel.add(new JLabel("  "));
		panel.add(theReportButton);
		panel.add(new JLabel("  "));
		panel.add(logOut);

		

		add(panel, BorderLayout.EAST);
		
		arrangmentButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AranzmanAgentView agw = new AranzmanAgentView();
				agw.setVisible(true);
			}
		});
		
		reservationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RezervacijaAgent rez1 = new RezervacijaAgent();
				rez1.setVisible(true);
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
