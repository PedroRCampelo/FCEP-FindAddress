package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

@SuppressWarnings("serial")
public class Cep extends JFrame {

	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtAddress;
	private JTextField txtDistrict;
	private JTextField txtCity;
	private JTextField txtUf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cep() {
		setResizable(false);
		setTitle("Buscar endereço");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CEP");
		lblNewLabel.setBounds(26, 33, 29, 16);
		contentPane.add(lblNewLabel);

		txtCep = new JTextField();
		txtCep.setBounds(55, 28, 130, 26);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Endereço");
		lblNewLabel_1.setBounds(42, 96, 61, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Bairro");
		lblNewLabel_2.setBounds(42, 141, 61, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Cidade");
		lblNewLabel_3.setBounds(42, 189, 61, 16);
		contentPane.add(lblNewLabel_3);

		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		txtAddress.setBounds(106, 91, 252, 26);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);

		txtDistrict = new JTextField();
		txtDistrict.setEditable(false);
		txtDistrict.setBounds(86, 136, 151, 26);
		contentPane.add(txtDistrict);
		txtDistrict.setColumns(10);

		txtCity = new JTextField();
		txtCity.setEditable(false);
		txtCity.setBounds(96, 184, 141, 26);
		contentPane.add(txtCity);
		txtCity.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("UF");
		lblNewLabel_4.setBounds(276, 189, 23, 16);
		contentPane.add(lblNewLabel_4);

		// Click event (Button buscar)
		JButton btnCep = new JButton("Buscar");
		btnCep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txtCep.requestFocus();
				} else if (txtCep.getText().length() < 8) {
					JOptionPane.showMessageDialog(null, "Cep incompleto");
					txtCep.requestFocus();
				} else {
					// Find address
					findCep();
				}
			}
		});

		btnCep.setBounds(197, 28, 94, 29);
		contentPane.add(btnCep);

		JButton btnClear = new JButton("Limpar");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCep.setText("");
				txtCity.setText("");
				txtAddress.setText("");
				txtUf.setText("");
				txtDistrict.setText("");
			}
		});
		btnClear.setBounds(26, 226, 109, 29);
		contentPane.add(btnClear);

		// Click event (Button Sobre)
		JButton btnAbout = new JButton("Sobre");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setVisible(true);
			}
		});
		btnAbout.setBorder(null);
		btnAbout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAbout.setBackground(SystemColor.control);
		btnAbout.setIcon(new ImageIcon(Cep.class.getResource("/img/about.png")));
		btnAbout.setBounds(327, 17, 117, 51);
		contentPane.add(btnAbout);

		// Validation fields
		// Command + Shift + o TO IMPORT the class

		// ATXY2K
		RestrictedTextField valid = new RestrictedTextField(txtCep);

		txtUf = new JTextField();
		txtUf.setEditable(false);
		txtUf.setBounds(302, 184, 42, 26);
		contentPane.add(txtUf);
		txtUf.setColumns(10);
		valid.setOnlyNums(true);
		valid.setLimit(8);

	} // End Constructor

	private void findCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null; // Check status
		String cep = txtCep.getText(); // User choice
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader(); // Model xml
			Document document = xml.read(url);
			Element root = document.getRootElement();

			// For - to scan all XML
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) { // When found
					txtCity.setText(element.getText()); // Do this
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtDistrict.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					txtUf.setText(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
			}
			// set address
			txtAddress.setText(tipoLogradouro + " " + logradouro);

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
