package personaje;

import java.net.Socket;

import castas.Brujo;
import castas.Guerrero;
import castas.Paladin;
import mapa.Punto;
import razas.Humano;

public class Jugador {
	private Personaje personaje;
	private String Nombre;
	private Socket conexionServer;
	private String estado;
	private String casta;
	private String raza;
	private Punto posicion;

	// Constructores
	public Jugador(String nombre, String raza, String casta) {
		Nombre = nombre;
		this.casta = casta;
		this.raza = raza;
		switch (raza) {
		case "Humano": {
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
		case "Elfo": {
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
	}

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
}
