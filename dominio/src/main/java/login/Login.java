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

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private Registro registro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setBounds(29, 47, 69, 26);
		contentPane.add(lblUsuario);

		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setBounds(10, 95, 88, 26);
		contentPane.add(lblPassword);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(108, 47, 238, 26);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(108, 95, 238, 26);
		contentPane.add(passwordField);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAceptar.setBounds(124, 164, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnRegistro = new JButton("Registro");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaRegistro();
			}
		});
		btnRegistro.setBounds(240, 164, 89, 23);
		contentPane.add(btnRegistro);
	}

	public void abrirVentanaRegistro() {
		registro = new Registro(this);
		registro.setVisible(true);
		setEnabled(false);
	}

	public void completarUsuario(String texto) {
		setEnabled(true);
		textFieldUsuario.setText(texto);
	}
}
