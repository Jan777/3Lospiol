package login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private Login login;

	/**
	 * Create the frame.
	 */
	public void run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setBounds(39, 26, 62, 31);
		contentPane.add(lblUsuario);

		JLabel PASSWORD = new JLabel("PASSWORD:");
		PASSWORD.setBounds(22, 73, 79, 31);
		contentPane.add(PASSWORD);

		JLabel lblEmail = new JLabel("E-MAIL:");
		lblEmail.setBounds(50, 115, 51, 31);
		contentPane.add(lblEmail);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(125, 31, 257, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(125, 120, 257, 20);
		contentPane.add(textFieldEmail);

		passwordField = new JPasswordField();
		passwordField.setBounds(125, 78, 257, 20);
		contentPane.add(passwordField);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escribirUsuario();
				dispose();
			}
		});
		btnAceptar.setBounds(211, 182, 89, 23);
		contentPane.add(btnAceptar);
	}

	public void escribirUsuario() {
		login.completarUsuario(textFieldUsuario.getText());
	}

	public Registro(Login login) {
		this.login = login;
		run();
	}
}
