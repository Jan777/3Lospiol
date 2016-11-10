package gui;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import cliente.Mensaje;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
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
		setTitle("WarLords - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			leerConfiguracion();
			this.cliente = new Socket(this.ip, this.puerto);
			this.dataOutputStream = new DataOutputStream(cliente.getOutputStream());
			this.dataInputStream = new DataInputStream(cliente.getInputStream());
			this.gson = new Gson();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
				dispose();
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

		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 434, 261);
		contentPane.add(lblFondo);

		// ImageIcon icono = new
		// ImageIcon(getClass().getResource("/Imagenes/fondoInicio.jpg"));
		// Image imagen = icono.getImage();
		// ImageIcon iconoEscalonado = new ImageIcon(
		// imagen.getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(),
		// Image.SCALE_DEFAULT));
		//
		// lblFondo.setIcon(iconoEscalonado);
		// this.repaint();
	}

	public void completarUsuario(String texto) {
		setEnabled(true);
		textFieldUsuario.setText(texto);
	}

	public void abrirRegistro() {
		registro = new Registro(this, this.cliente);
		// this.setEnabled(false);
		registro.setVisible(true);
		// this.setEnabled(true);

	}

	public void encriptarDesencriptarContraseña() {
		if (this.mostrarContraseñaCheckBox.isSelected())
			this.passwordField.setEchoChar((char) 0);
		else
			this.passwordField.setEchoChar('*');
	}

	public String leerRespuestaDeServidor() {

		try {
			this.mensaje = gson.fromJson(this.dataInputStream.readUTF(), Mensaje.class);
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		return this.mensaje.getMensaje();

	}

	public void enviarMensajeAlServidor(Mensaje mensaje) {
		try {
			this.dataOutputStream.writeUTF(gson.toJson(mensaje));
			this.dataOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void leerConfiguracion() throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("src/main/resources/App.config"));

		this.ip = scanner.nextLine().split(":")[1];
		this.puerto = Integer.parseInt(scanner.nextLine().split(":")[1]);
		scanner.close();

	}
}
