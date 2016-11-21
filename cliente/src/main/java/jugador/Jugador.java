package jugador;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.Socket;

import javax.swing.ImageIcon;

import castas.Brujo;
import castas.Guerrero;
import castas.Paladin;
import mapa.Punto;
import personaje.Personaje;
import razas.Humano;

public class Jugador {
	private Personaje personaje;
	private String Nombre;
	private Socket conexionServer;
	private String estado;
	private String casta;
	private String raza;
	private Punto posicion;
	private String animacion;
	private int desplazamientoX;
	private int desplazamientoY;
	private Image imagen;

	// Constructores
	/*public Jugador(String nombre, String raza, String casta) {
		Nombre = nombre;
		this.casta = casta;
		this.raza = raza;
		posicion = new Punto(40, 60);
		desplazamientoX = 0;
		desplazamientoY = 0;
		switch (raza) {
		case "Humano": {
			switch (casta) {
			case "Brujo":
				personaje = new Humano(new Brujo(), casta, casta);
				break;
			case "Guerrero":
				personaje = new Humano(new Guerrero(), casta, casta);
				break;
			case "Paladin":
				personaje = new Humano(new Paladin(), casta, casta);
				break;
			}
			break;
		}
		case "Elfo": {
			switch (casta) {
			case "Brujo":
				personaje = new Humano(new Brujo(), casta, casta);
				break;
			case "Guerrero":
				personaje = new Humano(new Guerrero(), casta, casta);
				break;
			case "Paladin":
				personaje = new Humano(new Paladin(), casta, casta);
				break;
			}
			break;
		}
		case "Orco": {
			switch (casta) {
			case "Brujo":
				personaje = new Humano(new Brujo());
				break;
			case "Guerrero":
				personaje = new Humano(new Guerrero());
				break;
			case "Paladin":
				personaje = new Humano(new Paladin());
				break;
			}
			break;
		}
		}
		ImageIcon img = new ImageIcon(this.getClass().getResource("/imagenes/ElfoGuerrero.gif"));
		imagen = img.getImage();
	}*/

	// Getters and Setters
	public Punto getPosicion() {
		return posicion;
	}

	public void setPosicion(Punto posicion) {
		this.posicion = posicion;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getCasta() {
		return casta;
	}

	public void setCasta(String casta) {
		this.casta = casta;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	// Metodos Adicionales
	public void mover() {
		posicion.desplazar(desplazamientoX, desplazamientoY);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)
			desplazamientoX -= 1;
		if (key == KeyEvent.VK_RIGHT)
			desplazamientoX += 1;
		if (key == KeyEvent.VK_UP)
			desplazamientoY -= 1;
		if (key == KeyEvent.VK_DOWN)
			desplazamientoY += 1;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)
			desplazamientoX = 0;
		if (key == KeyEvent.VK_RIGHT)
			desplazamientoX = 0;
		if (key == KeyEvent.VK_UP)
			desplazamientoY = 0;
		if (key == KeyEvent.VK_DOWN)
			desplazamientoY = 0;
	}
}