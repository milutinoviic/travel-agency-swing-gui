package KorisnikPregled;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Korisnik;
import view.logIn.RegistrujSe;


public class KorisnikPregled extends JFrame {
	private KorisnikTabela tableModel;
	private JTable table;
	
	
	public KorisnikPregled() {
		setSize(500,400);
		setVisible(true);
		setLocationRelativeTo(null);
		
		JPanel panelButtons = new JPanel(new FlowLayout());
		JButton buttonCreate = new JButton("Dodaj");
		JButton buttonUpdate = new JButton("Izmjeni");
		JButton buttonDelete = new JButton("Obriši");
		panelButtons.add(buttonCreate);
		panelButtons.add(buttonUpdate);
		panelButtons.add(buttonDelete);
		
		add(panelButtons, BorderLayout.NORTH);
		
		tableModel = new KorisnikTabela();
		table = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		
		//pack();
		validate();
		
		buttonCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegistrujSe reg = new RegistrujSe();
				updateUsers();
			}
		});
		
		buttonUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = table.getSelectedRow();
				if(red == -1)
				{
					return;
				}
				Korisnik kor = tableModel.getUser(red);
				
				AzurirajKorinsika user = new AzurirajKorinsika(kor);
				
			}
		});
		
		
		buttonDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				tableModel.izbaci(tableModel.getUser(red));
				updateUsers();
			}
		});
		
		
		
	}

	public void updateUsers()
	{
		KorisnikTabela model = (KorisnikTabela)table.getModel();
		
		model.updateUsers();
		model.fireTableDataChanged();
		validate();
	}
	
	


}
