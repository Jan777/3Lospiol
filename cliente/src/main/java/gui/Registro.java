package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Mensaje;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.Socket;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private JPasswordField repetirPasswordField;
	private Login login;
	private Gson gson;
	private Mensaje mensaje;

	/**
	 * Create the frame.
	 */
	public void run() {
		setTitle("WarLords - Registro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(115, 11, 62, 14);
		contentPane.add(lblUsuario);

		JLabel lblContraseña = new JLabel("Contrase\u00F1a:");
		lblContraseña.setBounds(115, 67, 79, 14);
		contentPane.add(lblContraseña);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(115, 177, 51, 14);
		contentPane.add(lblEmail);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(125, 36, 257, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(125, 202, 257, 20);
		contentPane.add(textFieldEmail);

		passwordField = new JPasswordField();
		passwordField.setBounds(125, 92, 257, 20);
		contentPane.add(passwordField);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					if (validarDatosCompletos() && validarContraseñas()) {
						escribirUsuario();
						dispose();
					}
				}
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarDatosCompletos() && validarContraseñas()) {
					escribirUsuario();
					dispose();
				}
			}
		});
		btnAceptar.setBounds(115, 227, 89, 23);
		contentPane.add(btnAceptar);

		repetirPasswordField = new JPasswordField();
		repetirPasswordField.setBounds(125, 146, 257, 20);
		contentPane.add(repetirPasswordField);

		JLabel lblRepetirContraseña = new JLabel("Repetir contrase\u00F1a:");
		lblRepetirContraseña.setBounds(115, 123, 96, 14);
		contentPane.add(lblRepetirContraseña);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelarRegistro();
			}
		});
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					cancelarRegistro();
				}
			}
		});
		btnCancelar.setBounds(244, 227, 89, 23);
		contentPane.add(btnCancelar);
	}

	public void escribirUsuario() {
		login.completarUsuario(textFieldUsuario.getText());
	}

	public Registro(Login login, Socket cliente) {
		this.login = login;
		run();
	}
	
	private boolean validarNombreDeUsuario(){
		//mensaje.setId("");
		this.mensaje.setMensaje("nombreDeUsuariop");
		this.login.enviarMensajeAlServidor(mensaje);
		String respuesta = this.login.leerRespuestaDeServidor();
		System.out.println(respuesta);
		return true;
	}

	@SuppressWarnings("deprecation")
	public boolean validarContraseñas() {
		if (!this.passwordField.getText().equals(this.repetirPasswordField.getText())) {
			JOptionPane.showMessageDialog(null, "No coinciden las contraseñas", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean validarDatosCompletos() {
		if (this.passwordField.getText().length() > 0 && this.repetirPasswordField.getText().length() > 0
				&& this.textFieldEmail.getText().length() > 0 && this.textFieldUsuario.getText().length() > 0) {
			return true;
		}
		JOptionPane.showMessageDialog(null, "Faltan ingresar datos", "Error", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public void cancelarRegistro() {
		this.dispose();
		login.setVisible(true);
	}
}
