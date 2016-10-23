package hechizosDeBrujo;

import personaje.HechizoOHab;
import personaje.Personaje;

public class DisminuirAtaque extends HechizoOHab {
	@Override
	public void afectar(Personaje personaje) {
		personaje.disminuirAtaque(4+personaje.obtenerPuntosDeHechizos()-1);
	}
}
