package RezervacijaAgent;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.AranzmanController;
import controller.RezervacijaController;

import dao.AranzmanDao;
import dao.RezervacijaDao;
import enums.Status;
import model.Aranzman;
import model.Rezervacija;


public class RezervacijaAgent extends JFrame {
	public AranzmanController aranzmanController;

	public RezervacijaController rezervacijaController;
	private RezervacijaAgentTabela tableModel;
	private JTable table;
	
	
	public RezervacijaAgent() {
		rezervacijaController = new RezervacijaController(RezervacijaDao.getInstance());

		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		setSize(500, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Rezervacije");
		
		JPanel panelButtons = new JPanel(new FlowLayout());
		JButton buttonCreate = new JButton("Odobri");
		JButton buttonOtkazi = new JButton("Odbiti");
		panelButtons.add(buttonCreate);
		panelButtons.add(buttonOtkazi);
		
		add(panelButtons, BorderLayout.NORTH);
		
		tableModel = new RezervacijaAgentTabela();
		table = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		
		validate();
		
		buttonCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if(table.getSelectedRow() != -1)
				{
					Rezervacija r = tableModel.getRezervacija(table.getSelectedRow());
					if(r.getStatus() != Status.KREIRANA)
					{
						JOptionPane.showMessageDialog(RezervacijaAgent.this, "Rezervacija nije u statusu napravljena, nije moguce odobriti. ", "Upozorenje", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					r.setStatus(Status.ZAVRSENA);
					rezervacijaController.update(r);
					
					r.setCena(rezervacijaController.calcCena(r));
					rezervacijaController.update(r);
					
					tableModel.refresh();
				}
			
			}
		});
		
		buttonOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(table.getSelectedRow() != -1)
				{
					Rezervacija r = tableModel.getRezervacija(table.getSelectedRow());
					if(r.getStatus() != Status.KREIRANA)
					{
						JOptionPane.showMessageDialog(RezervacijaAgent.this, "Rezervacija nije u statusu napravljena, nije moguce otkazati. ", "Upozorenje", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					Aranzman a = r.getAranzman();
					a.setKapacitet(a.getKapacitet() + r.getBrojPutnika());
					aranzmanController.update(a);
					
					r.setStatus(Status.OTKAZANA);
					rezervacijaController.update(r);

					tableModel.refresh();
				}
			
				
			}
		});

	}

	

}
