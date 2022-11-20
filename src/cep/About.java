package cep;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.net.URI;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class About extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About dialog = new About();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public About() {
		setModal(true);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/img/home.png")));
		setResizable(false);
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Find Address --V01");
		lblNewLabel.setBounds(28, 27, 165, 16);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("@Autor Pedro Campelo");
		lblNewLabel_1.setBounds(28, 73, 215, 16);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Web Service:");
		lblNewLabel_2.setBounds(28, 222, 88, 16);
		getContentPane().add(lblNewLabel_2);

		JLabel lblWebService = new JLabel("republicavirtual.com.br");
		lblWebService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://www.republicavirtual.com.br/"); // !
			}
		});
		lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblWebService.setForeground(Color.BLUE);
		lblWebService.setBounds(117, 222, 157, 16);
		getContentPane().add(lblWebService);

	} // End of constructor

	// Link Method
	private void link(String website) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI(website);
			desktop.browse(uri);
		} catch (Exception e) {
			System.out.println(e); 
		}
	}

}
