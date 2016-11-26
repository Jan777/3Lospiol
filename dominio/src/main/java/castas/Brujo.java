package castas;

import hechizosDeBrujo.BolaDeLaOscuridad;
import hechizosDeBrujo.DisminuirAtaque;
import hechizosDeBrujo.LatigazoMortal;

public class Brujo extends Casta {

	public Brujo() {
		this.casta = "Brujo";
		this.idCasta = 2;
		this.setHabilidadCasta("Poder de Hechizo");
		this.poderDeHabilidad = 4;
		this.agregarHechizo("Bola de la oscuridad", new BolaDeLaOscuridad());
		this.agregarHechizo("Disminuir ataque", new DisminuirAtaque());
		this.agregarHechizo("Latigaso mortal", new LatigazoMortal());
	}
}
