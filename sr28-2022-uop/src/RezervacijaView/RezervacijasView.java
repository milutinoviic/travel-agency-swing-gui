package RezervacijaView;

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
import enums.Status;
import model.Rezervacija;


public class RezervacijasView extends JFrame {
	public static RezervacijaTabela tableModel;
	private JTable table;
	public TuristaController turistaController;
	public AranzmanController aranzmanController;
	public AgentController agentController;
	public RezervacijaController rezervacijaController;
	
	
	public RezervacijasView() {
		setSize(500, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Rezervacije");
		
		JPanel panelButtons = new JPanel(new FlowLayout());
		JButton buttonCreate = new JButton("Dodaj");
		JButton buttonUpdate = new JButton("Izmjeni");
		JButton buttonDelete = new JButton("Obriši");
		panelButtons.add(buttonCreate);
		panelButtons.add(buttonUpdate);
		panelButtons.add(buttonDelete);
		
		add(panelButtons, BorderLayout.NORTH);
		
		tableModel = new RezervacijaTabela();
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
						JOptionPane.showMessageDialog(RezervacijasView.this, "Rezervacija nije u statusu napravljena, nije moguce menjati je. ", "Upozorenje", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					System.out.println(r.getId());
					AzurirajRezervaciju up = new AzurirajRezervaciju(r);
							
					
					tableModel.refresh();
				}
				
			}
		});
		
		buttonCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KreirajRez  cr = new KreirajRez();
				cr.setVisible(true);
				
				System.out.println("Prošao");
				
				tableModel.refresh();
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
					
					JOptionPane.showMessageDialog(RezervacijasView.this, "Rezervacija je obrisana.", "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
					
					tableModel.refresh();
				}
				
			}
		});

	}


}
