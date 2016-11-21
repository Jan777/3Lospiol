package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import personaje.Personaje;
import personaje.PersonajeDibujable;
import razas.Humano;
import razas.Orco;
import razas.Elfo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;

import castas.Brujo;
import castas.Guerrero;
import castas.Paladin;

public class CrearPersonaje extends JFrame {

	private JPanel elegirRaza;
	private Personaje personaje;
	private String raza;
	private String casta;
	private boolean eligio=false;
	private String nombrePersonaje;
	private JTextField nombre;
	private PersonajeDibujable dibujoPersonaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPersonaje frame = new CrearPersonaje();
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
	public CrearPersonaje() {
		setTitle("Warlords");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		elegirRaza = new JPanel();
		elegirRaza.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(elegirRaza);
		elegirRaza.setLayout(null);
		
		final JButton btnOrco = new JButton("Orco");
		final JButton btnElfo = new JButton("Elfo");
		final JButton btnHumano = new JButton("Humano");
		
		btnElfo.setBounds(384, 320, 89, 23);
		elegirRaza.add(btnElfo);
		
		btnHumano.setBounds(384, 365, 89, 23);
		elegirRaza.add(btnHumano);
		
		btnOrco.setBounds(384, 273, 89, 23);
		elegirRaza.add(btnOrco);
		
		btnOrco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raza="orco";
				eligio=true;
				btnHumano.setEnabled(false);
				btnElfo.setEnabled(false);
				btnOrco.setEnabled(false);
			}
		});

		
		btnElfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raza="elfo";
				eligio=true;
				btnHumano.setEnabled(false);
				btnElfo.setEnabled(false);
				btnOrco.setEnabled(false);
			}
		});

		
		btnHumano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raza="humano";
				eligio=true;
				btnHumano.setEnabled(false);
				btnElfo.setEnabled(false);
				btnOrco.setEnabled(false);
			}
		});
		
		
		final JButton btnElegir = new JButton("Elegir");
		btnElegir.setBounds(384, 426, 89, 23);
		elegirRaza.add(btnElegir);
		
		final JButton btnPaladin = new JButton("Paladin");
		btnPaladin.setBounds(384, 307, 89, 23);
		elegirRaza.add(btnPaladin);
		btnPaladin.setVisible(false);
		
		final JButton btnGuerrero = new JButton("Guerrero");
		btnGuerrero.setBounds(384, 341, 89, 23);
		elegirRaza.add(btnGuerrero);
		btnGuerrero.setVisible(false);
		
		final JButton btnBrujo = new JButton("Brujo");
		btnBrujo.setBounds(384, 273, 89, 23);
		elegirRaza.add(btnBrujo);
		btnBrujo.setVisible(false);
		
		nombre = new JTextField();
		nombre.setBounds(342, 395, 173, 20);
		elegirRaza.add(nombre);
		nombre.setColumns(10);
		nombre.setVisible(false);
		
		final JButton elegirCasta = new JButton("Elegir");
		elegirCasta.setBounds(384, 426, 89, 23);
		elegirRaza.add(elegirCasta);
		elegirCasta.setVisible(false);
		
		final JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(384, 375, 99, 14);
		elegirRaza.add(lblNombre);
		lblNombre.setVisible(false);
		
		final JLabel lblEligeUnaRaza = new JLabel("Elige una raza");
		lblEligeUnaRaza.setBounds(384, 197, 142, 14);
		elegirRaza.add(lblEligeUnaRaza);
		
		final JLabel lblEligeUnaCasta = new JLabel("Elige una casta");
		lblEligeUnaCasta.setBounds(384, 222, 162, 14);
		elegirRaza.add(lblEligeUnaCasta);
		lblEligeUnaCasta.setVisible(false);
		
		
		final JButton btnDeshacerRaza = new JButton("Deshacer");
		btnDeshacerRaza.setBounds(384, 460, 89, 23);
		elegirRaza.add(btnDeshacerRaza);
		
		btnDeshacerRaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eligio=false;
				btnHumano.setEnabled(true);
				btnElfo.setEnabled(true);
				btnOrco.setEnabled(true);
			}
		});
		
		final JButton btnDeshacerCasta = new JButton("Deshacer");
		btnDeshacerCasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eligio=false;
				btnPaladin.setEnabled(true);
				btnBrujo.setEnabled(true);
				btnGuerrero.setEnabled(true);
			}
		});
		btnDeshacerCasta.setBounds(384, 460, 89, 23);
		elegirRaza.add(btnDeshacerCasta);
		btnDeshacerCasta.setVisible(false);
		
		btnElegir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!eligio)
					return;

				btnElegir.setVisible(false);
				btnHumano.setVisible(false);
				btnElfo.setVisible(false);
				btnOrco.setVisible(false);
				lblEligeUnaRaza.setVisible(false);
				btnDeshacerRaza.setVisible(false);
				
				btnDeshacerCasta.setVisible(true);
				lblNombre.setVisible(true);
				nombre.setVisible(true);
				if(raza.compareTo("orco")!=0)
					btnBrujo.setVisible(true);
				btnGuerrero.setVisible(true);
				btnPaladin.setVisible(true);
				elegirCasta.setVisible(true);
				lblEligeUnaCasta.setVisible(true);
			}
		});
		
		btnPaladin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casta="paladin";
				btnPaladin.setEnabled(false);
				btnGuerrero.setEnabled(false);
				btnBrujo.setEnabled(false);
			}
		});
		
		btnGuerrero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casta="guerrero";
				btnPaladin.setEnabled(false);
				btnGuerrero.setEnabled(false);
				btnBrujo.setEnabled(false);
			}
		});
		
		btnBrujo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casta="brujo";
				btnPaladin.setEnabled(false);
				btnGuerrero.setEnabled(false);
				btnBrujo.setEnabled(false);
			}
		});
		
		elegirCasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do{
					nombrePersonaje=nombre.getText();
				}while(nombre.getText().compareTo("")==0);// para verificar que haya un nombre valido
				//FALTA VALIDAR CON BASE DE DATOS SI EL NOMBRE EXISTE.
				switch(raza){
					case "Humano":
						switch(casta){
							case "paladin":
								personaje= new Humano(new Paladin());
								dibujoPersonaje= new PersonajeDibujable(nombrePersonaje,"humanoP");
								break;
							case "guerrero":
								personaje= new Humano(new Guerrero());
								dibujoPersonaje= new PersonajeDibujable(nombrePersonaje,"humanoG");
								break;
							case "brujo":
								personaje= new Humano(new Brujo());
								dibujoPersonaje= new PersonajeDibujable(nombrePersonaje,"humanoB");
								break;	
							default:
								break;		
						}
						break;
					case "Orco":
						switch(casta){
							case "paladin":
							personaje= new Orco(new Paladin());
							dibujoPersonaje= new PersonajeDibujable(nombrePersonaje,"orcoP");
								break;
							case "guerrero":
								personaje= new Orco(new Guerrero());
								dibujoPersonaje= new PersonajeDibujable(nombrePersonaje,"orcoP");
								break;
							default:
								break;		
						}
						break;
					case "Elfo":
						switch(casta){
							
							case "paladin":
								personaje= new Elfo(new Paladin());
								dibujoPersonaje= new PersonajeDibujable(nombrePersonaje,"elfoP");
								break;
							case "guerrero":
								personaje= new Elfo(new Guerrero());
								dibujoPersonaje= new PersonajeDibujable(nombrePersonaje,"elfoG");
								break;
							case "brujo":
								personaje= new Elfo(new Brujo());
								dibujoPersonaje= new PersonajeDibujable(nombrePersonaje,"elfoB");
								break;
							default:
								break;		
						}
						break;
					default:
						break;
				}
				
			}
			
		});
	}
	
	public Personaje obtenerPersonaje(){
		return personaje;
	}
	
	public String obtenerNombre(){
		return nombrePersonaje;
	}
	
	public PersonajeDibujable obtenerPersDibujable(){
		return dibujoPersonaje;
	}
}
