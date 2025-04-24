package TuristView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class AranzmanTuristiView extends JFrame {
	private AranzmanTuristTabelaModel tableModel;
	private JTable table;
	
	
	public AranzmanTuristiView() {
		setSize(500, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		
		JPanel panelButtons = new JPanel(new FlowLayout());
		
		add(panelButtons, BorderLayout.NORTH);
		
		tableModel = new AranzmanTuristTabelaModel();
		table = new JTable(tableModel);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		
		
		validate();
		
		
	
	}

}
