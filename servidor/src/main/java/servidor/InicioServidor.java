package servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class InicioServidor extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIP;
	private JTextField textFieldPuerto;
	private int puerto;
	private boolean seCerro=false;
	private JLabel lblDireccionIp;
	private JLabel lblPuertoDeConexin;
	private JLabel lblServidorEnLinea;
	private JButton buttonConectar;
	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioServidor frame = new InicioServidor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnknownHostException 
	 */
	public InicioServidor() throws UnknownHostException {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldIP = new JTextField();
		textFieldIP.setEditable(false);
		textFieldIP.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldIP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldIP.setBounds(105, 64, 215, 31);
		contentPane.add(textFieldIP);
		textFieldIP.setColumns(10);
		textFieldIP.setText(InetAddress.getLocalHost().getHostAddress());
		
		lblDireccionIp = new JLabel("Direccion IP");
		lblDireccionIp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDireccionIp.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccionIp.setBounds(105, 11, 215, 31);
		contentPane.add(lblDireccionIp);
		
		lblPuertoDeConexin = new JLabel("Puerto de conexi\u00F3n");
		lblPuertoDeConexin.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuertoDeConexin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPuertoDeConexin.setBounds(105, 116, 215, 31);
		contentPane.add(lblPuertoDeConexin);
		
		textFieldPuerto = new JTextField();
		textFieldPuerto.setBounds(105, 158, 215, 31);
		contentPane.add(textFieldPuerto);
		textFieldPuerto.setColumns(10);
		
		buttonConectar = new JButton("Conectar");
		buttonConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				puerto=Integer.parseInt(textFieldPuerto.getText());
				seCerro=true;
				servidorEnLinea();
			}
		});
		buttonConectar.setBounds(164, 200, 89, 31);
		contentPane.add(buttonConectar);
		
		lblServidorEnLinea = new JLabel("Servidor en Linea");
		lblServidorEnLinea.setBackground(Color.BLACK);
		lblServidorEnLinea.setForeground(Color.GREEN);
		lblServidorEnLinea.setHorizontalAlignment(SwingConstants.CENTER);
		lblServidorEnLinea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblServidorEnLinea.setBounds(105, 200, 215, 31);
		lblServidorEnLinea.setVisible(false);
		contentPane.add(lblServidorEnLinea);
		
		setVisible(true);
	}

	public boolean isSeCerro() {
		return seCerro;
	}

	public void setSeCerro(boolean seCerro) {
		this.seCerro = seCerro;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public void servidorEnLinea() {
		lblServidorEnLinea.setVisible(true);
		textFieldPuerto.setEditable(false);
		buttonConectar.setVisible(false);
		
	}
}
