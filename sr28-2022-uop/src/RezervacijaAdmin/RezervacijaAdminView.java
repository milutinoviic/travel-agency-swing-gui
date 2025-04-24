package RezervacijaAdmin;

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

import RezervacijaView.AzurirajRezervaciju;
import controller.AgentController;
import controller.AranzmanController;
import controller.RezervacijaController;
import controller.TuristaController;
import dao.AgentDAO;
import dao.AranzmanDao;
import dao.RezervacijaDao;
import dao.TuristaDao;
import enums.Status;

import model.Rezervacija;


public class RezervacijaAdminView extends JFrame{
	public TuristaController turistaController;
	public AranzmanController aranzmanController;
	public AgentController agentController;
	public RezervacijaController rezervacijaController;
	private Rezervacija rezervacija;
	public static RezervacijaAdminTabela tableModel;
	private JTable table;
	private JButton btnStorniraj;

	
	public RezervacijaAdminView() {
		rezervacijaController = new RezervacijaController(RezervacijaDao.getInstance());
		agentController = new AgentController(AgentDAO.getInstance());
		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		turistaController = new TuristaController(TuristaDao.getInstance());

		setSize(500, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Rezervacije");
		
		btnStorniraj = new JButton("Storniraj");
		JPanel panelButtons = new JPanel(new FlowLayout());
		JButton buttonUpdate = new JButton("Izmjeni");
		JButton buttonDelete = new JButton("Obriši");
		panelButtons.add(buttonUpdate);
		panelButtons.add(buttonDelete);
		panelButtons.add(btnStorniraj);
		
		add(panelButtons, BorderLayout.NORTH);
		
		
		
		tableModel = new RezervacijaAdminTabela();
		table = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		
		validate();
		
		buttonUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1)
				{
			
					Rezervacija r = tableModel.getRezervacija(table.getSelectedRow());
					if(r.getStatus() != Status.KREIRANA)
					{
						JOptionPane.showMessageDialog(RezervacijaAdminView.this, "Rezervacija nije u statusu napravljena, nije moguce menjati je. ", "Upozorenje", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					System.out.println(r.getId());
					AzurirajRezervaciju up = new AzurirajRezervaciju(r);
							
					
					tableModel.refresh();
				}
				
			}
		});
		

		buttonDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(table.getSelectedRow());
				if(table.getSelectedRow() != -1)
				{
					Rezervacija r = tableModel.getRezervacija(table.getSelectedRow());
					System.out.println(r.getId());
					rezervacijaController.delete(r);
					
					JOptionPane.showMessageDialog(RezervacijaAdminView.this, "Rezervacija je obrisana", "Obavjestenje", JOptionPane.INFORMATION_MESSAGE);
					
					tableModel.refresh();
				}
				
			}
		});
		
		btnStorniraj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				if(table.getSelectedRow() != -1)
				{
					Rezervacija r = tableModel.getRezervacija(table.getSelectedRow());
					if(r.getStatus()==Status.OTKAZANA) {
						JOptionPane.showMessageDialog(RezervacijaAdminView.this, "Rezervacija je OTKAZANA ", "Obavjestenje", JOptionPane.INFORMATION_MESSAGE);
						
					}
					else {
						r.setStatus(Status.NEUSPJESNA);
						rezervacijaController.update(r);
						tableModel.refresh();
					}
					

				}
				
			}
		});

	}
	

}
