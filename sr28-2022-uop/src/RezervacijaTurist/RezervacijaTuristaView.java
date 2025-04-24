package RezervacijaTurist;

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


public class RezervacijaTuristaView extends JFrame {
	public TuristaController turistaController;
	public AranzmanController aranzmanController;
	public AgentController agentController;
	public RezervacijaController rezervacijaController;
	private Rezervacija rezervacija;
	public static RezervacijaTabelaTuristi tableModel;
	private JTable table;
	
	
	public RezervacijaTuristaView() {
		rezervacijaController = new RezervacijaController(RezervacijaDao.getInstance());
		agentController = new AgentController(AgentDAO.getInstance());
		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		turistaController = new TuristaController(TuristaDao.getInstance());

		setSize(500, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Rezervacije");
		
		JPanel panelButtons = new JPanel(new FlowLayout());
		JButton buttonCreate = new JButton("Dodaj");
		JButton buttonUpdate = new JButton("Izmjeni");
		JButton buttonDelete = new JButton("Obriši");
		JButton buttonOtkazan = new JButton("Otkazan");
		panelButtons.add(buttonCreate);
		panelButtons.add(buttonUpdate);
		panelButtons.add(buttonDelete);
		panelButtons.add(buttonOtkazan);
		
		add(panelButtons, BorderLayout.NORTH);
		
		tableModel = new RezervacijaTabelaTuristi();
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
					if(r.getStatus() != Status.KREIRANA )
					{
						JOptionPane.showMessageDialog(RezervacijaTuristaView.this, "Rezervacija nije u statusu napravljena, nije moguce menjati je. ", "Upozorenje", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					System.out.println(r.getId());
					AzurirajRezTurist up = new AzurirajRezTurist(r);
							
					
					tableModel.refresh();
				}
				
			}
		});
		
		buttonCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KreiraRezTurist cr = new KreiraRezTurist();
				cr.setVisible(true);
				
				tableModel.refresh();
			}
		});
		
		buttonOtkazan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() != -1)
				{
				
					Rezervacija r = tableModel.getRezervacija(table.getSelectedRow());
					if(r.getStatus() != Status.KREIRANA)
					{
						return;
					}
					
					JOptionPane.showMessageDialog(RezervacijaTuristaView.this, "Rezervacija je otkazana", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
					
					r.getAranzman().setKapacitet(r.getAranzman().getKapacitet() + r.getBrojPutnika());
					aranzmanController.update(r.getAranzman());
					
					r.setStatus(Status.OTKAZANA);
					rezervacijaController.update(r);
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
					
					JOptionPane.showMessageDialog(RezervacijaTuristaView.this, "Rezervacija je obrisana.", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
					
					tableModel.refresh();
				}
				
			}
		});

	}


}
