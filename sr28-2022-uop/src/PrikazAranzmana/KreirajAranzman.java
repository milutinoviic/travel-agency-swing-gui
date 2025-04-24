package PrikazAranzmana;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.AgentController;
import controller.AranzmanController;
import dao.AgentDAO;
import dao.AranzmanDao;
import enums.TipAranzmana;
import enums.TipSmjestaja;
import model.Agent;
import model.Aranzman;
import model.Korisnik;
import model.Rezervacija;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import view.logIn.LogIn;

public class KreirajAranzman extends JDialog {
	private JFileChooser fileChooser = new JFileChooser();
	private AranzmanController aranzmanController;
	public AgentController agentController;

	private FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png",

			"gif");

	private JButton btnBrowse = new JButton("Pregledaj");

	private JLabel labelPicture = new JLabel();

	private JLabel labelPicturePath = new JLabel();

	public KreirajAranzman(JFrame parent) {
		super(parent, true);
		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		agentController = new AgentController(AgentDAO.getInstance());
		
		setTitle("Dodaj aranžman");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel panCenter = new JPanel();
		BoxLayout boxCenter = new BoxLayout(panCenter, BoxLayout.Y_AXIS);
		panCenter.setLayout(boxCenter);
		Dimension dim = new Dimension(150, 20);
		JPanel panArrangmentType = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblArrangmentType = new JLabel("Tip aranžmana:");
		lblArrangmentType.setPreferredSize(dim);
		TipAranzmana allArrangmentTypes[] = TipAranzmana.values();
		JComboBox<TipAranzmana> arrangmentTypes = new JComboBox<TipAranzmana>(allArrangmentTypes);
		arrangmentTypes.setPreferredSize(dim);
		panArrangmentType.add(lblArrangmentType);
		panArrangmentType.add(arrangmentTypes);
		
		JPanel panpicture = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblpicture = new JLabel("Slika:");
		panpicture.add(lblpicture);
		panpicture.add(labelPicture);
		JPanel panpicturePath = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblpicture1 = new JLabel("Slika:");
		panpicturePath.add(lblpicture1);

		panpicturePath.add(labelPicturePath);
		JPanel panBrowse = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblBrowse = new JLabel();
		lblBrowse.setPreferredSize(dim);
		
		
		panBrowse.add(lblBrowse);
		panBrowse.add(btnBrowse);
		JPanel pancapacity = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblcapacity = new JLabel("Kapacitet:");
		lblcapacity.setPreferredSize(dim);
		JTextField txtcapacity = new JTextField();
		txtcapacity.setPreferredSize(dim);
		pancapacity.add(lblcapacity);
		pancapacity.add(txtcapacity);
		JPanel panAccommodationType = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblAccommodationType = new JLabel("Tip smještaja:");
		lblAccommodationType.setPreferredSize(dim);
		TipSmjestaja allAccommodationType[] = TipSmjestaja.values();
		JComboBox<TipSmjestaja> accommodationTypes = new JComboBox<TipSmjestaja>(allAccommodationType);
		accommodationTypes.setPreferredSize(dim);
		panAccommodationType.add(lblAccommodationType);
		panAccommodationType.add(accommodationTypes);
		JPanel panprice = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel lblprice = new JLabel("Cijena:");
		lblprice.setPreferredSize(dim);
		JTextField txtprice = new JTextField();
		txtprice.setPreferredSize(dim);
		panprice.add(lblprice);
		panprice.add(txtprice);
		JPanel pansale = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblsale = new JLabel("Popust:");
		lblsale.setPreferredSize(dim);
		JTextField txtsale = new JTextField();
		txtsale.setPreferredSize(dim);
		pansale.add(lblsale);
		pansale.add(txtsale);
		JPanel pandate = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lbldate = new JLabel("Datum :");
		lbldate.setPreferredSize(dim);
		UtilDateModel dateModel = new UtilDateModel();
		JDatePanelImpl datePanel;
		JDatePickerImpl datePicker;
		dateModel.setDate(2023, 4, 22);
		datePanel = new JDatePanelImpl(dateModel);
		datePicker = new JDatePickerImpl(datePanel);
		pandate.add(lbldate);
		pandate.add(datePicker);
		JPanel pandays = new JPanel(new FlowLayout(FlowLayout.LEFT));
	

		
		JPanel panBrojDana = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel lblBrojDana = new JLabel("Broj dana:");

		lblBrojDana.setPreferredSize(dim);

		JTextField txtBrojDana = new JTextField();

		txtBrojDana.setPreferredSize(dim);

		panBrojDana.add(lblBrojDana);

		panBrojDana.add(txtBrojDana);
		
		
	
		panCenter.add(panArrangmentType);
		panCenter.add(panpicture);
		panCenter.add(panpicturePath);
		panCenter.add(panBrowse);
		panCenter.add(pancapacity);
		panCenter.add(panAccommodationType);
		panCenter.add(panprice);
		panCenter.add(pansale);
		panCenter.add(pandate);
		panCenter.add(pandays);
		panCenter.add(panBrojDana);
		panCenter.add(Box.createVerticalStrut(25));

		add(panCenter, BorderLayout.CENTER);

		JPanel panBottom = new JPanel();
		BoxLayout box = new BoxLayout(panBottom, BoxLayout.X_AXIS);
		panBottom.setLayout(box);
		JButton btnOk = new JButton("OK");
		btnOk.setPreferredSize(new Dimension(75, 25));
		dispose();
		panBottom.add(Box.createGlue());
		panBottom.add(btnOk);
		panBottom.add(Box.createHorizontalStrut(10));
		dispose();
		add(panBottom, BorderLayout.SOUTH);
		pack();
		validate();
		repaint();

		btnOk.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				TipAranzmana tipAranzmana = (TipAranzmana) arrangmentTypes.getSelectedItem();

				LocalDate date = null;

				try {

					SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");

					String datum = format.format(datePicker.getModel().getValue());

					date = util.DateHelper.stringToDate(datum);

				} catch (Exception e1) {

					JOptionPane.showMessageDialog(KreirajAranzman.this, "Pogrešan format datuma , trebalo bi dd.mm.gggg.",
							"Uzbuna", JOptionPane.ERROR_MESSAGE);

					return;

				}

				File selectedFile = fileChooser.getSelectedFile();

				Path filePath = Paths.get(selectedFile.getAbsolutePath());

				Path basePath = Paths.get(System.getProperty("user.dir"));

				String putanja = basePath.relativize(filePath).toString();


				String picture = putanja;

				int capacity = Integer.parseInt(txtcapacity.getText());

				TipSmjestaja tipSmestaja = (TipSmjestaja) accommodationTypes.getSelectedItem();

				double price = Double.parseDouble(txtprice.getText());

				int sale = Integer.parseInt(txtsale.getText());

				Korisnik owner = agentController.get(0);

				
				Aranzman arrangment = new Aranzman();
				arrangment.setId(-1);
				
				arrangment.setKapacitet(capacity);
				arrangment.setProdavac((Agent)owner);
                arrangment.setDostupanDatum(date);
                arrangment.setCijena(price);
                arrangment.setSajamskiPopust(sale);
                arrangment.getBrojDana();
                arrangment.setSlika(picture);
                arrangment.setTipAranzmana(tipAranzmana);
                arrangment.setTipSmjestaja(tipSmestaja);
				
				aranzmanController.create(arrangment);

				dispose();

			}

		});

		btnBrowse.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				int result = fileChooser.showOpenDialog(KreirajAranzman.this);

				if (result == JFileChooser.APPROVE_OPTION) {

					File selectedFile = fileChooser.getSelectedFile();

					Path filePath = Paths.get(selectedFile.getAbsolutePath());

					Path basePath = Paths.get(System.getProperty("user.dir"));

					String putanja = basePath.relativize(filePath).toString();

					ImageIcon imageIcon = new ImageIcon(putanja);

					Image image = imageIcon.getImage(); 

					Image newimg = image.getScaledInstance(90, 60, java.awt.Image.SCALE_SMOOTH); 

					imageIcon = new ImageIcon(newimg); 

					labelPicture.setIcon(imageIcon);

					labelPicturePath.setText(putanja);

					KreirajAranzman.this.pack();

				}

			}

		});

	}

}
