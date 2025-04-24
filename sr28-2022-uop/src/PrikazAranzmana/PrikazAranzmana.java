package PrikazAranzmana;

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
import dao.AranzmanDao;
import model.Aranzman;

public class PrikazAranzmana extends JFrame {
	private AranzmanTabeleModel tableModel;
	private JTable table;
	private AranzmanController aranzmanController;
	
	
	public PrikazAranzmana() {
		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		
		setSize(600, 500);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Aranžmani");

		
		JPanel panelButtons = new JPanel(new FlowLayout());
		JButton buttonCreate = new JButton("Dodaj");
		JButton buttonUpdate = new JButton("Izmjeni");
		JButton buttonDelete = new JButton("Obriši");
		panelButtons.add(buttonCreate);
		panelButtons.add(buttonUpdate);
		panelButtons.add(buttonDelete);
		
		add(panelButtons, BorderLayout.NORTH);
		
		tableModel = new AranzmanTabeleModel();
		table = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		
		validate();
		
		buttonUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() != -1)
				{
					AzurirajAranzman ua = new AzurirajAranzman(PrikazAranzmana.this, tableModel.getAranzman(table.getSelectedRow()));
					ua.setVisible(true);
					
					tableModel.refresh();
				}
				
			}
		});
		
		buttonCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KreirajAranzman createArrangment = new KreirajAranzman(PrikazAranzmana.this);
				createArrangment.setVisible(true);
				
				
				tableModel.refresh();
			}
		});
	
	buttonDelete.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(table.getSelectedRow());
			if(table.getSelectedRow() != -1)
			{
				Aranzman a = tableModel.getAranzman(table.getSelectedRow());
				System.out.println(a.getId());
				aranzmanController.delete(a);
				
				JOptionPane.showMessageDialog(PrikazAranzmana.this, "Aranžman je obrisan." , "Obavještenje", JOptionPane.INFORMATION_MESSAGE);
				
				tableModel.refresh();
			}
			
		}
	});
	
	}

}
