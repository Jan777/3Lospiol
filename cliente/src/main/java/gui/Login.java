package gui;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.Socket;
import java.net.UnknownHostException;

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
import personaje.Personaje;
import personaje.PersonajeDibujable;

import javax.swing.JCheckBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private Registro registro;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;
	private Socket cliente;
	private Gson gson;
	private JCheckBox mostrarContraseñaCheckBox;
	private int puerto;
	private String ip;
	private Mensaje mensaje;
	private String nombreUsuario;
	private boolean seCerro = false;
	private JTextField textFieldPuerto;
	private JTextField textFieldIP;
	private boolean conectado = false;
	private Personaje personaje;
	private PersonajeDibujable personajeDibujable;

	@SuppressWarnings("deprecation")
	public Login() {
		setResizable(false);
		this.mensaje = new Mensaje("", "");
		setVisible(true);
		setTitle("WarLords - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(85, 11, 69, 26);
		contentPane.add(lblUsuario);

		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setBounds(85, 71, 88, 26);
		contentPane.add(lblPassword);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(95, 34, 238, 26);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(95, 97, 238, 26);
		contentPane.add(passwordField);

		JButton btnAceptar = new JButton("Login");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarDatosCompletos()) {
					if (validarPuertoIP()) {
						if (validarCredenciales().equals("false")) {
							JOptionPane.showMessageDialog(null, "Usuario o contraseña inválido", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							nombreUsuario = textFieldUsuario.getText();
							seCerro = true;
							if (!tienePersonaje())
								abrirCrearPersonaje();
							else {
								personaje = obtenerPersonaje();
								abrirJuego(textFieldUsuario.getText());
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "IP o Puerto inválidos.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Faltan ingresar datos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					if (validarDatosCompletos()) {
						if (validarPuertoIP()) {
							if (validarCredenciales().equals("false")) {
								JOptionPane.showMessageDialog(null, "Usuario o contraseña inválido", "Error",
										JOptionPane.ERROR_MESSAGE);
							} else {
								conectarCliente();
								nombreUsuario = textFieldUsuario.getText();
								seCerro = true;
								if (!tienePersonaje())
									abrirCrearPersonaje();
								else {
									personaje = obtenerPersonaje();
									// personajeDibujable =
									// obtenerPersonajeDibujable();
									abrirJuego(textFieldUsuario.getText());
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "IP o Puerto inválidos.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Faltan ingresar datos", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});
		btnAceptar.setBounds(97, 172, 89, 23);
		contentPane.add(btnAceptar);

		mostrarContraseñaCheckBox = new JCheckBox("Mostrar contraseña");
		mostrarContraseñaCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				encriptarDesencriptarContraseña();
			}
		});
		mostrarContraseñaCheckBox.setBounds(235, 130, 119, 23);
		contentPane.add(mostrarContraseñaCheckBox);

		JLabel lblNoTieneCuenta = new JLabel("No tiene cuenta?");
		lblNoTieneCuenta.setBounds(190, 176, 100, 14);
		contentPane.add(lblNoTieneCuenta);

		JLabel lblRegistrese = new JLabel("Registrese");
		lblRegistrese.setCursor(new Cursor(HAND_CURSOR));
		lblRegistrese.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				abrirRegistro();
			}
		});
		setEnabled(true);
		lblRegistrese.setBounds(294, 176, 77, 14);
		contentPane.add(lblRegistrese);

		textFieldIP = new JTextField();
		textFieldIP.setBounds(95, 231, 86, 20);
		contentPane.add(textFieldIP);
		textFieldIP.setColumns(10);

		textFieldPuerto = new JTextField();
		textFieldPuerto.setBounds(252, 231, 86, 20);
		contentPane.add(textFieldPuerto);
		textFieldPuerto.setColumns(10);

		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(95, 217, 46, 14);
		contentPane.add(lblIp);

		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(252, 217, 46, 14);
		contentPane.add(lblPuerto);

	}

	public void abrirCrearPersonaje() {
		CrearPersonaje crearPersonaje = new CrearPersonaje(this.textFieldUsuario.getText(), cliente, this);
		crearPersonaje.setVisible(true);
	}

	private Personaje obtenerPersonaje() {

		this.mensaje.cambiarMensaje("obtenerPersonaje", textFieldUsuario.getText());
		try {
			this.enviarMensaje(this.mensaje);
			this.leerRespuesta();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al obtener el Personaje", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return gson.fromJson(mensaje.getJson(), Personaje.class);
	}

	protected void conectarCliente() {
		if (!this.conectado) {
			try {
				this.cliente = new Socket(this.ip, this.puerto);
				this.dataOutputStream = new DataOutputStream(cliente.getOutputStream());
				this.dataInputStream = new DataInputStream(cliente.getInputStream());
				this.gson = new Gson();
				this.conectado = true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "IP o Puerto Incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public DataOutputStream getDataOutPutStream() {
		return this.dataOutputStream;
	}

	public DataInputStream getDataInputStream() {
		return this.dataInputStream;
	}

	public void setIP(String ip) {
		this.ip = ip;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public boolean validarDatosCompletos() {
		if (this.textFieldUsuario.getText().length() > 0 && this.passwordField.getText().length() > 0
				&& this.textFieldIP.getText().length() > 0 && this.textFieldPuerto.getText().length() > 0) {
			return true;
		}
		return false;
	}

	protected boolean validarPuertoIP() {
		if (this.textFieldIP.getText().length() > 0 && this.textFieldPuerto.getText().length() > 0) {
			this.ip = this.textFieldIP.getText();
			this.puerto = Integer.parseInt(this.textFieldPuerto.getText());
			conectarCliente();
			return true;
		}
		JOptionPane.showMessageDialog(null, "Faltan ingresar datos", "Error", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public void completarUsuario(String texto) {
		setEnabled(true);
		textFieldUsuario.setText(texto);
	}

	public void completarIPYPuerto(String ip, String puerto) {
		textFieldPuerto.setText(puerto);
		textFieldIP.setText(ip);
	}

	public void abrirRegistro() {
		registro = new Registro(this);
		registro.setVisible(true);
	}

	public void encriptarDesencriptarContraseña() {
		if (this.mostrarContraseñaCheckBox.isSelected())
			this.passwordField.setEchoChar((char) 0);
		else
			this.passwordField.setEchoChar('*');
	}

	public void leerRespuesta() throws IOException {
		String entrada;
		entrada = this.dataInputStream.readUTF();
		mensaje = gson.fromJson(entrada, Mensaje.class);

	}

	public String validarCredenciales() {
		String datosUsuario = textFieldUsuario.getText() + ":" + passwordField.getText();
		this.mensaje.cambiarMensaje("validarCredenciales", datosUsuario);
		try {
			this.enviarMensaje(this.mensaje);
			this.leerRespuesta();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al validar el usuario y la contraseña", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return gson.fromJson(mensaje.getJson(), String.class);
	}

	public void enviarMensaje(Mensaje mensaje) throws IOException {
		enviar(mensaje);

	}

	public void enviar(Mensaje mensj) throws IOException {
		String msg = gson.toJson(mensj);
		this.dataOutputStream.writeUTF(msg);
		this.dataOutputStream.flush();
	}

	public Mensaje getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public Gson getGson() {
		return this.gson;
	}

	public String getUsuario() {
		return nombreUsuario;
	}

	public Socket obtenerCliente() {
		return cliente;
	}

	public boolean seCerro() {
		return seCerro;
	}

	public void abrirJuego(String nombreUsuario) {
		//
		try {

			new Juego(cliente, nombreUsuario, personajeDibujable, personaje);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el juego", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean tienePersonaje() {

		this.mensaje.cambiarMensaje("consultarPersonaje", textFieldUsuario.getText());
		try {
			this.enviarMensaje(this.mensaje);
			this.leerRespuesta();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al consultar con la base de datos", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		String respuesta = gson.fromJson(mensaje.getJson(), String.class);
		if (respuesta == "true")
			return true;
		return false;
	}

	/**
	 * Hice el main en el login porque se facilitaba bastante el funcionamiento
	 * de los frames.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Error al ingresar al juego", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

}
