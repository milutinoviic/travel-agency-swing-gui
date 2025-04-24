package agentView;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.AranzmanController;
import view.logIn.LogIn;
import dao.AranzmanDao;
import model.Aranzman;



public class AranzmanAgentTableModel extends AbstractTableModel {
	private String[] columnNames = { "ID", "Slika", "Datum", "Kapacitet", "Cena", "Popust", "Aranzman", "Smestaj"};
	private ArrayList<model.Aranzman> aranzmani;
	private AranzmanController aranzmanController;

	public AranzmanAgentTableModel() {
		super();
		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		aranzmani = new ArrayList<>();
		
		for(model.Aranzman a  : aranzmanController.getAll())
		{
			if(!a.isDeleted() && a.getProdavac().getId() == LogIn.ulogovanKorisnik.getId())
			{
				aranzmani.add(a);
			}
		}
	}
	
	public Aranzman getAranzman(int row)
	{
		return aranzmani.get(row);
	}
	
	public void refresh()
	{
		aranzmani.clear();
		for(Aranzman a : aranzmanController.getAll())
		{
			if(!a.isDeleted() && a.getProdavac().getId() == LogIn.ulogovanKorisnik.getId())
			{
				aranzmani.add(a);
			}
		}
		fireTableDataChanged();
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return aranzmani.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Aranzman aranzman = aranzmani.get(row);

		switch (col) {
		case 0:
			return aranzman.getId();
		case 1:
			return aranzman.getSlika();
		case 2:
			return aranzman.getDostupanDatum();
		case 3: 
			return aranzman.getKapacitet();
		case 4:
			return aranzman.getCijena();
		case 5:
			return aranzman.getSajamskiPopust();
		case 6:
			return aranzman.getTipAranzmana();
		case 7:
			return aranzman.getTipSmjestaja();
		}

		return null;
	}

}
