package agentView;

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
import model.Aranzman;
import model.Korisnik;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import view.logIn.LogIn;

public class AzurirajAranzmanAgent extends JDialog {
	
	private JFileChooser fileChooser = new JFileChooser();
	private AranzmanController aranzmanController;
	private FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png",	"gif");
	private JButton btnBrowse = new JButton("Browse");
	private JLabel labelPicture = new JLabel();
	private JLabel labelPicturePath = new JLabel();
	private model.Aranzman izabrani;
	
	public AzurirajAranzmanAgent(JFrame parent, model.Aranzman a) {
		super(parent, true);
		
		izabrani = a;
		aranzmanController = new AranzmanController(AranzmanDao.getInstance());
		setTitle("Izmjeni aranžman");
		setSize(400, 400);
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

		UtilDateModel dateModel = new UtilDateModel();

		JDatePanelImpl datePanel;

		JDatePickerImpl datePicker;

		dateModel.setDate(izabrani.getDostupanDatum().getYear(), izabrani.getDostupanDatum().getMonthValue() , izabrani.getDostupanDatum().getDayOfYear());

		datePanel = new JDatePanelImpl(dateModel);

		datePicker = new JDatePickerImpl(datePanel);

		pandate.add(lbldate);

		pandate.add(datePicker);

		JPanel pandays = new JPanel(new FlowLayout(FlowLayout.LEFT));

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

		panCenter.add(pandate);

		panCenter.add(pandays);

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
				double sale = Double.parseDouble(txtsale.getText());
				Korisnik owner = LogIn.ulogovanKorisnik;
				izabrani.setCijena(price);
				izabrani.setDostupanDatum(date);
				izabrani.setKapacitet(capacity);
				izabrani.setSajamskiPopust(sale);
				izabrani.setTipSmjestaja(tipSmestaja);
				izabrani.setTipAranzmana((TipAranzmana)arrangmentTypes.getSelectedItem());
				izabrani.setSlika(putanja);
				aranzmanController.update(izabrani);

				dispose();
			}

		});

		btnBrowse.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				int result = fileChooser.showOpenDialog(AzurirajAranzmanAgent.this);

				if (result == JFileChooser.APPROVE_OPTION) {

					File selectedFile = fileChooser.getSelectedFile();

					Path filePath = Paths.get(selectedFile.getAbsolutePath());

					Path basePath = Paths.get(System.getProperty("user.dir"));

					String putanja = basePath.relativize(filePath).toString();

					ImageIcon imageIcon = new ImageIcon(putanja);

					Image image = imageIcon.getImage(); // transform it

					Image newimg = image.getScaledInstance(90, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth
																									// way

					imageIcon = new ImageIcon(newimg); // transform it back

					labelPicture.setIcon(imageIcon);

					labelPicturePath.setText(putanja);


				}

			}

		});

	}


}
