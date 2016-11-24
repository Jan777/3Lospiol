package castas;

import habDeGuerrero.AumentarAtaque;
import habDeGuerrero.Desgarrar;
import habDeGuerrero.Ejecutar;

public class Guerrero extends Casta {
	
	public Guerrero() {
		this.casta="Guerrero";
		this.idCasta = 1;
		this.agregarHechizo("Aumentar ataque", new AumentarAtaque());
		this.agregarHechizo("Desgarrar", new Desgarrar());
		this.agregarHechizo("Ejecutar", new Ejecutar());
	}
}
