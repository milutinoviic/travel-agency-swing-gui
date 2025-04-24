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
import java.time.LocalDate;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import controller.AranzmanController;
import dao.AranzmanDao;
import enums.TipAranzmana;
import enums.TipSmjestaja;
import model.Korisnik;
import view.logIn.LogIn;

public class AzurirajAranzman extends JDialog{
	private JFileChooser fileChooser = new JFileChooser();
	private AranzmanController aranzmanController;
	private FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png",	"gif");
	private JButton btnBrowse = new JButton("Pregledaj");
	private JLabel labelPicture = new JLabel();
	private JLabel labelPicturePath = new JLabel();
	private model.Aranzman izabrani;
	
	public AzurirajAranzman (JFrame parent, model.Aranzman a) {
		super(parent, true);
		
		izabrani = a;
		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		setTitle("Izmeni aranžman");
		setSize(500, 500);
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
		arrangmentTypes.setSelectedItem(izabrani.getTipAranzmana());
		
		arrangmentTypes.setPreferredSize(dim);
		panArrangmentType.add(lblArrangmentType);
		panArrangmentType.add(arrangmentTypes);
		JPanel panpicture = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel lblpicture = new JLabel("Slika:");

		

		panpicture.add(lblpicture);
		labelPicture.setText(izabrani.getSlika());
		panpicture.add(labelPicture);
		JPanel panpicturePath = new JPanel(new FlowLayout(FlowLayout.LEFT));


		JLabel lblpicture1 = new JLabel();
		panpicturePath.add(lblpicture1);
	    labelPicturePath.setText(izabrani.getSlika());
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
		txtcapacity.setText(Integer.toString(izabrani.getKapacitet()));
		txtcapacity.setPreferredSize(dim);
		pancapacity.add(lblcapacity);
		pancapacity.add(txtcapacity);

		JPanel panAccommodationType = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblAccommodationType = new JLabel("Tip smeštaja:");
		lblAccommodationType.setPreferredSize(dim);
		TipSmjestaja allAccommodationType[] = TipSmjestaja.values();
		JComboBox<TipSmjestaja> accommodationTypes = new JComboBox<TipSmjestaja>(allAccommodationType);
		accommodationTypes.setSelectedItem(izabrani.getTipSmjestaja());
		accommodationTypes.setPreferredSize(dim);
		panAccommodationType.add(lblAccommodationType);
		panAccommodationType.add(accommodationTypes);

		JPanel panprice = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblprice = new JLabel("Cena:");
		lblprice.setPreferredSize(dim);
		JTextField txtprice = new JTextField();
		txtprice.setText(Double.toString(izabrani.getCijena()));	
		txtprice.setPreferredSize(dim);
		

		panprice.add(lblprice);
		panprice.add(txtprice);
		

		JPanel pansale = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblsale = new JLabel("Popust:");
		lblsale.setPreferredSize(dim);

		JTextField txtsale = new JTextField();
		txtsale.setText(Double.toString(izabrani.getSajamskiPopust()));	
		txtsale.setPreferredSize(dim);
		pansale.add(lblsale);
		pansale.add(txtsale);

		JPanel pandate = new JPanel(new FlowLayout(FlowLayout.LEFT));	
		JLabel lbldate = new JLabel("Datum:");
		lbldate.setPreferredSize(dim);

		
		
		File selectedFile = new File(izabrani.getSlika());
		fileChooser.setSelectedFile(selectedFile);

		panCenter.add(panArrangmentType);
		panCenter.add(panpicture);
		panCenter.add(panpicturePath);
		panCenter.add(panBrowse);
		panCenter.add(pancapacity);
		panCenter.add(panAccommodationType);
		panCenter.add(panprice);
		panCenter.add(pansale);


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
	
				File selectedFile = fileChooser.getSelectedFile();
				Path filePath = Paths.get(selectedFile.getAbsolutePath());
				Path basePath = Paths.get(System.getProperty("user.dir"));
				String putanja = basePath.relativize(filePath).toString();
				
				String picture = putanja;
				int capacity = Integer.parseInt(txtcapacity.getText());
				TipSmjestaja tipSmestaja = (TipSmjestaja) accommodationTypes.getSelectedItem();
				double price = Double.parseDouble(txtprice.getText());
				double sale = Double.parseDouble(txtsale.getText());
				Korisnik owner = LogIn.ulogovanKorisnik;
				izabrani.setCijena(price);
				izabrani.setDostupanDatum(LocalDate.now());
				izabrani.setKapacitet(capacity);
				izabrani.setSajamskiPopust(sale);
				izabrani.setTipSmjestaja(tipSmestaja);
				izabrani.setSlika(putanja);
				izabrani.setTipAranzmana(tipAranzmana);
				aranzmanController.update(izabrani);
				dispose();
			}

		});

		String putanja = izabrani.getSlika();
		ImageIcon imageIcon = new ImageIcon(putanja);
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(90, 60, java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg); 
		labelPicture.setIcon(imageIcon);
		labelPicturePath.setText(putanja);

		
		btnBrowse.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				int result = fileChooser.showOpenDialog(AzurirajAranzman.this);

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
					labelPicture.setText(putanja);
			
				}

			}

		});

	}


}
