package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;

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

import Atxy2k.CustomTextField.RestrictedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Cep extends JFrame {

	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtAddress;
	private JTextField txtDistrict;
	private JTextField txtCity;

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
		txtAddress.setBounds(106, 91, 210, 26);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);

		txtDistrict = new JTextField();
		txtDistrict.setBounds(86, 136, 151, 26);
		contentPane.add(txtDistrict);
		txtDistrict.setColumns(10);

		txtCity = new JTextField();
		txtCity.setBounds(96, 184, 141, 26);
		contentPane.add(txtCity);
		txtCity.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("UF");
		lblNewLabel_4.setBounds(276, 189, 23, 16);
		contentPane.add(lblNewLabel_4);

		JComboBox cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(303, 185, 73, 27);
		contentPane.add(cboUf);

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
				}
			}
		});

		btnCep.setBounds(197, 28, 94, 29);
		contentPane.add(btnCep);

		JButton btnClear = new JButton("Limpar");
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
		valid.setOnlyNums(true);
		valid.setLimit(8);

	} // End Constructor
}
