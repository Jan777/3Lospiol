package gui;

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
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import cliente.Mensaje;
import castas.Brujo;
import castas.Guerrero;
import castas.Paladin;

public class CrearPersonaje extends JFrame {

	private JPanel elegirRaza;
	private Personaje personaje;
	private String raza;
	private String casta;
	private boolean eligio = false;
	private String nombrePersonaje;
	private Gson gson;
	private PersonajeDibujable personajeDibujable;
	private Mensaje mensaje = new Mensaje("", "");
	private String entrada;
	private boolean seCerro = false;
	private Login login;
	private String nombreImagen;

	public CrearPersonaje(final String nombrePersonaje, Socket cliente, final Login login) {
		this.gson = new Gson();
		this.nombrePersonaje = nombrePersonaje;
		this.login = login;
		setTitle("Warlords");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		elegirRaza = new JPanel();
		elegirRaza.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(elegirRaza);
		elegirRaza.setLayout(null);

		final JButton btnOrco = new JButton("Orco");
		final JButton btnElfo = new JButton("Elfo");
		final JButton btnHumano = new JButton("Humano");

		btnElfo.setBounds(155, 134, 89, 23);
		elegirRaza.add(btnElfo);

		btnHumano.setBounds(155, 179, 89, 23);
		elegirRaza.add(btnHumano);

		btnOrco.setBounds(155, 87, 89, 23);
		elegirRaza.add(btnOrco);

		btnOrco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raza = "orco";
				eligio = true;
				btnHumano.setEnabled(false);
				btnElfo.setEnabled(false);
				btnOrco.setEnabled(false);
			}
		});

		btnElfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raza = "elfo";
				eligio = true;
				btnHumano.setEnabled(false);
				btnElfo.setEnabled(false);
				btnOrco.setEnabled(false);
			}
		});

		btnHumano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raza = "humano";
				eligio = true;
				btnHumano.setEnabled(false);
				btnElfo.setEnabled(false);
				btnOrco.setEnabled(false);
			}
		});

		final JButton btnElegir = new JButton("Elegir");
		btnElegir.setBounds(155, 213, 89, 23);
		elegirRaza.add(btnElegir);

		final JButton btnPaladin = new JButton("Paladin");
		btnPaladin.setBounds(155, 121, 89, 23);
		elegirRaza.add(btnPaladin);
		btnPaladin.setVisible(false);

		final JButton btnGuerrero = new JButton("Guerrero");
		btnGuerrero.setBounds(155, 155, 89, 23);
		elegirRaza.add(btnGuerrero);
		btnGuerrero.setVisible(false);

		final JButton btnBrujo = new JButton("Brujo");
		btnBrujo.setBounds(155, 87, 89, 23);
		elegirRaza.add(btnBrujo);
		btnBrujo.setVisible(false);

		final JButton elegirCasta = new JButton("Elegir");
		elegirCasta.setBounds(155, 213, 89, 23);
		elegirRaza.add(elegirCasta);
		elegirCasta.setVisible(false);

		final JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(121, 189, 200, 14);
		elegirRaza.add(lblNombre);
		lblNombre.setVisible(false);

		final JLabel lblEligeUnaRaza = new JLabel("Elige una raza");
		lblEligeUnaRaza.setBounds(155, 11, 142, 14);
		elegirRaza.add(lblEligeUnaRaza);

		final JLabel lblEligeUnaCasta = new JLabel("Elige una casta");
		lblEligeUnaCasta.setBounds(155, 36, 162, 14);
		elegirRaza.add(lblEligeUnaCasta);
		lblEligeUnaCasta.setVisible(false);

		final JButton btnDeshacerRaza = new JButton("Deshacer");
		btnDeshacerRaza.setBounds(155, 240, 89, 23);
		elegirRaza.add(btnDeshacerRaza);

		btnDeshacerRaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eligio = false;
				btnHumano.setEnabled(true);
				btnElfo.setEnabled(true);
				btnOrco.setEnabled(true);
			}
		});

		final JButton btnDeshacerCasta = new JButton("Deshacer");
		btnDeshacerCasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eligio = false;
				btnPaladin.setEnabled(true);
				btnBrujo.setEnabled(true);
				btnGuerrero.setEnabled(true);
			}
		});
		btnDeshacerCasta.setBounds(155, 247, 89, 23);
		elegirRaza.add(btnDeshacerCasta);
		btnDeshacerCasta.setVisible(false);

		btnElegir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!eligio)
					return;
				// else
				// abrirJuego();

				btnElegir.setVisible(false);
				btnHumano.setVisible(false);
				btnElfo.setVisible(false);
				btnOrco.setVisible(false);
				lblEligeUnaRaza.setVisible(false);
				btnDeshacerRaza.setVisible(false);

				btnDeshacerCasta.setVisible(true);
				lblNombre.setVisible(true);

				lblNombre.setText("Nombre de personaje: " + nombrePersonaje);

				if (raza.compareTo("orco") != 0)
					btnBrujo.setVisible(true);
				btnGuerrero.setVisible(true);
				btnPaladin.setVisible(true);
				elegirCasta.setVisible(true);
				lblEligeUnaCasta.setVisible(true);
			}
		});

		btnPaladin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casta = "paladin";
				btnPaladin.setEnabled(false);
				btnGuerrero.setEnabled(false);
				btnBrujo.setEnabled(false);
			}
		});

		btnGuerrero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casta = "guerrero";
				btnPaladin.setEnabled(false);
				btnGuerrero.setEnabled(false);
				btnBrujo.setEnabled(false);
			}
		});

		btnBrujo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casta = "brujo";
				btnPaladin.setEnabled(false);
				btnGuerrero.setEnabled(false);
				btnBrujo.setEnabled(false);
			}
		});

		elegirCasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (raza) {
				case "humano":
					switch (casta) {
					case "paladin":
						personaje = new Humano(new Paladin(), nombrePersonaje, "humanoP");
						personajeDibujable = new PersonajeDibujable(nombrePersonaje, "humanoP");
						nombreImagen = "humanoP";
						break;
					case "guerrero":
						personaje = new Humano(new Guerrero(), nombrePersonaje, "humanoG");
						personajeDibujable = new PersonajeDibujable(nombrePersonaje, "humanoG");
						nombreImagen = "humanoG";
						break;
					case "brujo":
						personaje = new Humano(new Brujo(), nombrePersonaje, "humanoB");
						personajeDibujable = new PersonajeDibujable(nombrePersonaje, "humanoB");
						nombreImagen = "humanoB";
						break;
					default:
						break;
					}
					break;
				case "orco":
					switch (casta) {
					case "paladin":
						personaje = new Orco(new Paladin(), nombrePersonaje, "orcoP");
						personajeDibujable = new PersonajeDibujable(nombrePersonaje, "orcoP");
						nombreImagen = "orcoP";
						break;
					case "guerrero":
						personaje = new Orco(new Guerrero(), nombrePersonaje, "orcoG");
						personajeDibujable = new PersonajeDibujable(nombrePersonaje, "orcoG");
						nombreImagen = "orcoG";
						break;
					default:
						break;
					}
					break;
				case "elfo":
					switch (casta) {
					case "paladin":
						personaje = new Elfo(new Paladin(), nombrePersonaje, "elfoP");
						personajeDibujable = new PersonajeDibujable(nombrePersonaje, "elfoP");
						nombreImagen = "elfoP";
						break;
					case "guerrero":
						personaje = new Elfo(new Guerrero(), nombrePersonaje, "elfoG");
						personajeDibujable = new PersonajeDibujable(nombrePersonaje, "elfoG");
						nombreImagen = "elfoG";
						break;
					case "brujo":
						personaje = new Elfo(new Brujo(), nombrePersonaje, "elfoB");
						personajeDibujable = new PersonajeDibujable(nombrePersonaje, "elfoB");
						nombreImagen = "elfoB";
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
				try {
					enviarMensaje("guardarPersonaje");
					leerRespuesta();
					enviarMensaje("guardarPersonajeDibujable");
					leerRespuesta();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al guardar personaje en la BD", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				login.setPersonaje(login.obtenerPersonaje());
				login.setPersonajeDibujable(login.obtenerPersonajeDibujable());
				login.abrirJuego(nombrePersonaje);
			}
		});
	}

	public boolean seCerro() {
		return seCerro;
	}

	public Personaje obtenerPersonaje() {
		return personaje;
	}

	public PersonajeDibujable obtenerPersDibujable() {
		return personajeDibujable;
	}

	// metodos para solicitar registro de los personajes del jugador

	public void leerRespuesta() throws IOException {
		String entrada;
		entrada = login.getDataInputStream().readUTF();
		mensaje = gson.fromJson(entrada, Mensaje.class);
	}

	public void enviar(Mensaje mensj) throws IOException {
		String msg = gson.toJson(mensj);
		login.getDataOutPutStream().writeUTF(msg);
		login.getDataOutPutStream().flush();
	}

	public void enviarMensaje(String nombreMensaje) throws IOException {
		if (nombreMensaje.equals("guardarPersonajeDibujable")) {
			String json = gson.toJson(personajeDibujable);
			mensaje.cambiarMensaje(nombreMensaje, json);
			enviar(mensaje);
		}

		if (nombreMensaje.equals("guardarPersonaje")) {
			ArrayList<String> personaje = new ArrayList<>();
			personaje.add(this.personaje.getID());
			personaje.add("" + this.personaje.getIdRaza());
			personaje.add("" + this.personaje.getCasta().getIdCasta());
			personaje.add("" + this.personaje.getNivel());
			personaje.add("" + this.personaje.getExp());
			personaje.add("" + this.personaje.getSalud());
			personaje.add("" + this.personaje.getEnergia());
			personaje.add("" + this.personaje.getAtaque());
			personaje.add("" + this.personaje.getDefensa());
			personaje.add("" + this.personaje.getMana());
			String json = gson.toJson(personaje);
			mensaje.cambiarMensaje(nombreMensaje, json);
			enviar(mensaje);
		}
	}
}
