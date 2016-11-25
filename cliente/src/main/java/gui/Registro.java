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

import cliente.Mensaje;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private JPasswordField repetirPasswordField;
	private Login login;
	private JTextField textFieldIP;
	private JTextField textFieldPuerto;

	/**
	 * Create the frame.
	 */
	public void run() {
		login.setMensaje(new Mensaje("", ""));
		setTitle("WarLords - Registro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(81, 11, 62, 14);
		contentPane.add(lblUsuario);

		JLabel lblContraseña = new JLabel("Contrase\u00F1a:");
		lblContraseña.setBounds(81, 67, 79, 14);
		contentPane.add(lblContraseña);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(91, 36, 257, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(91, 92, 257, 20);
		contentPane.add(passwordField);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					if (validarPuertoIP()) {
						textFieldUsuario.setText(textFieldUsuario.getText().toUpperCase());
						if (validarDatosCompletos() && validarContraseñas()) {
							try {
								String respuestaValidacion = validarNombreDeUsuario();
								if (respuestaValidacion.equals("false")) {
									if (registrarUsuario(textFieldUsuario.getText(),
											passwordField.getText()) == "false") {
										JOptionPane.showMessageDialog(null, "Error al registrar el usuario", "Error",
												JOptionPane.ERROR_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(null, "Registro exitoso!", null,
												JOptionPane.ERROR_MESSAGE);
										login.completarUsuario(textFieldUsuario.getText());
										dispose();
									}

								} else {
									JOptionPane.showMessageDialog(null, "Usuario existente", "Error",
											JOptionPane.ERROR_MESSAGE);
								}
							} catch (IOException e1) {

								JOptionPane.showMessageDialog(null, "Error en el servidor", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

						}
					}
				}
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldUsuario.setText(textFieldUsuario.getText().toUpperCase());
				if (validarPuertoIP()) {
					if (validarDatosCompletos() && validarContraseñas()) {
						try {
							String respuestaValidacion = validarNombreDeUsuario();
							if (respuestaValidacion.equals("false")) {
								if (registrarUsuario(textFieldUsuario.getText(), passwordField.getText()) == "false") {
									JOptionPane.showMessageDialog(null, "Error al registrar el usuario", "Error",
											JOptionPane.ERROR_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(null, "Registro exitoso!", null,
											JOptionPane.ERROR_MESSAGE);
									login.completarIPYPuerto(textFieldIP.getText(), textFieldPuerto.getText());
									login.completarUsuario(textFieldUsuario.getText());
									dispose();
								}

							} else {
								JOptionPane.showMessageDialog(null, "Usuario existente", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (IOException e1) {

							JOptionPane.showMessageDialog(null, "Error en el servidor", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			}
		});
		btnAceptar.setBounds(115, 227, 89, 23);
		contentPane.add(btnAceptar);

		repetirPasswordField = new JPasswordField();
		repetirPasswordField.setBounds(91, 146, 257, 20);
		contentPane.add(repetirPasswordField);

		JLabel lblRepetirContraseña = new JLabel("Repetir contrase\u00F1a:");
		lblRepetirContraseña.setBounds(81, 123, 96, 14);
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

		JLabel labelIP = new JLabel("IP");
		labelIP.setBounds(91, 182, 46, 14);
		contentPane.add(labelIP);

		textFieldIP = new JTextField();
		textFieldIP.setColumns(10);
		textFieldIP.setBounds(91, 196, 86, 20);
		contentPane.add(textFieldIP);

		JLabel labelPuerto = new JLabel("Puerto");
		labelPuerto.setBounds(248, 182, 46, 14);
		contentPane.add(labelPuerto);

		textFieldPuerto = new JTextField();
		textFieldPuerto.setColumns(10);
		textFieldPuerto.setBounds(248, 196, 86, 20);
		contentPane.add(textFieldPuerto);
	}

	public void escribirUsuario() {
		login.completarUsuario(textFieldUsuario.getText());
	}

	public Registro(Login login) {
		setResizable(false);
		this.login = login;
		run();
	}

	private String validarNombreDeUsuario() throws IOException {
		login.getMensaje().cambiarMensaje("validarUsuario", this.textFieldUsuario.getText());
		this.login.enviarMensaje(login.getMensaje());
		this.login.leerRespuesta();
		return login.getGson().fromJson(login.getMensaje().getJson(), String.class);
	}

	private String registrarUsuario(String usuario, String contraseña) throws IOException {
		String datosUsuario = usuario + ":" + contraseña;
		login.getMensaje().cambiarMensaje("registrarUsuario", datosUsuario);
		this.login.enviarMensaje(login.getMensaje());
		this.login.leerRespuesta();
		return login.getGson().fromJson(login.getMensaje().getJson(), String.class);
	}

	@SuppressWarnings("deprecation")
	public boolean validarContraseñas() {
		if (!this.passwordField.getText().equals(this.repetirPasswordField.getText())) {
			JOptionPane.showMessageDialog(null, "No coinciden las contraseñas", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public boolean validarDatosCompletos() {
		if (this.passwordField.getText().length() > 0 && this.repetirPasswordField.getText().length() > 0
				&& this.textFieldUsuario.getText().length() > 0) {
			return true;
		}
		JOptionPane.showMessageDialog(null, "Faltan ingresar datos", "Error", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public void cancelarRegistro() {
		this.dispose();
		login.setVisible(true);
	}

	protected boolean validarPuertoIP() {
		if (this.textFieldIP.getText().length() > 0 && this.textFieldPuerto.getText().length() > 0) {
			login.setIP(this.textFieldIP.getText());
			login.setPuerto(Integer.parseInt(this.textFieldPuerto.getText()));
			login.conectarCliente();
			return true;
		}
		JOptionPane.showMessageDialog(null, "Debe ingresar el puerto y la IP", "Error", JOptionPane.ERROR_MESSAGE);
		return false;
	}
}
