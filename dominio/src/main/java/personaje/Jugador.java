package personaje;

import java.net.Socket;

public class Jugador {
	private Personaje personaje;
	private String Nombre;
	private Socket conexionServer;
	private String estado;
	
	public Personaje getPersonaje(){
		return personaje;
	}
}
